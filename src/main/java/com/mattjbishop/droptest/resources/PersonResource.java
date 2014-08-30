package com.mattjbishop.droptest.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.mattjbishop.droptest.api.PersonRepresentation;
import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.core.Status;
import com.mattjbishop.droptest.halapino.*;
import com.mattjbishop.droptest.utils.ResourceHelper;
import com.mattjbishop.droptest.views.PersonView;
import com.codahale.metrics.annotation.Timed;
import com.mongodb.DB;
import org.mongojack.JacksonDBCollection;
import org.mongojack.DBQuery;
import org.mongojack.DBCursor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

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

        HALRepresentation representation = null;
        try {
            representation = HALFactory.getFactory().getHALRepresentation(person, uriInfo);
        } catch (HALException e) {
            //throw exception that the custom mapper can handle
        }

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