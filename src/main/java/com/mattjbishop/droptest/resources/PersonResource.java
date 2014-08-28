package com.mattjbishop.droptest.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.mattjbishop.droptest.api.PersonRepresentation;
import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.core.Status;
import com.mattjbishop.droptest.hal.*;
import com.mattjbishop.droptest.utils.ResourceHelper;
import com.mattjbishop.droptest.views.PersonView;
import com.codahale.metrics.annotation.Timed;
import com.mongodb.DB;
import org.mongojack.JacksonDBCollection;
import org.mongojack.DBQuery;
import org.mongojack.DBCursor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.net.URI;

@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    final static Logger logger = LoggerFactory.getLogger(PersonResource.class);

    @Context
    private UriInfo uriInfo;

	private JacksonDBCollection<Person, String> people;
    private JacksonDBCollection<Status, String> statuses;

    public PersonResource(DB db)
    {
        this.people = JacksonDBCollection.wrap(db.getCollection("person"), Person.class, String.class);
        this.statuses = JacksonDBCollection.wrap(db.getCollection("status"), Status.class, String.class);
    }
	
	@GET
    @Timed
    @JsonView(Views.HAL.class) // this uses the jackson JAX-RS features to automagically add in the json view
	public Response getPerson(@PathParam("personId") String personId) {

        PersonRepresentation person = findPerson(personId);

        // no need to pass in the uriInfo here !!!
        HALRepresentation representation = HALFactory.getFactory().getHALRepresentation(person, uriInfo);

       /* UriBuilder builder = HALFactory.getFactory().getSelfBuilder(person.getClass()); */

        UriBuilder templateBuilder = UriBuilder.fromResource(PersonResource.class); // this can be pre-built !!!

        // this should be in setSelfLink - look up the template using the resource class...
        UriBuilder builder = uriInfo.getBaseUriBuilder();
        builder.path(templateBuilder.build(person.getPerson().getId()).toString()); // this should use the representation.resource.id
        // ... end

        representation.setSelfLink(builder.build().toASCIIString());

        // this should all be in the resource setSelfLink ...
        Map<String, List<HALRepresentation>> resources = representation.getEmbedded();

        templateBuilder = UriBuilder.fromResource(StatusResource.class);
        logger.info("testBuilder {}", templateBuilder.build().toASCIIString());

        templateBuilder.path(StatusResource.class, "getStatus");
        logger.info("testBuilder {}", templateBuilder.build().toASCIIString());

        builder = uriInfo.getBaseUriBuilder();

        for (Map.Entry<String, List<HALRepresentation>> entry : resources.entrySet())
        {
            for (HALRepresentation resource : entry.getValue())
            {
                if (resource.getResource() instanceof SelfBuilder)
                {
                    logger.info("building a builder for {}", resource.getResource().getClass());
                    String id = ((SelfBuilder) resource.getResource()).getId();

                    UriBuilder baseBuilder = builder.clone();
                    UriBuilder resourceBuilder = templateBuilder.clone();

                    URI uri = resourceBuilder.build(id);

                    baseBuilder.path(uri.toString());

                    Link link = new Link();
                    link.setHref(baseBuilder.build().toASCIIString());
                    link.setName(entry.getKey());

                    resource.setSelfLink(link.getHref());

                    representation.addLink(link);
                }
            }
        }
        // ... end

        // add in any resource/response specific links,namespaces,etc here

        return Response.ok(representation).build();
    }

    @GET
    @Path("/m")
    @Produces(MediaType.TEXT_HTML)
    @Timed
    public PersonView getPersonStatusViewMustache(@PathParam("personId") String personId) {
        PersonRepresentation person = findPerson(personId);
        return new PersonView(person);
    }

    private PersonRepresentation findPerson(String personId) {
        List<Status> personStatuses = new ArrayList<>();
        DBCursor<Person> pCursor = people.find(DBQuery.is("_id", personId));
        ResourceHelper.notFoundIfNull(pCursor);

        // get the status objects and add to the PersonStatus
        DBCursor<Status> sCursor = statuses.find(DBQuery.is("personId", personId));

        if (sCursor != null) {
            while (sCursor.hasNext()) {
                personStatuses.add(sCursor.next());
            }
        }
        return new PersonRepresentation(pCursor.next(), personStatuses);
    }
}