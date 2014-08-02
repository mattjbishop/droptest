package com.mattjbishop.droptest.resources;

import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.api.PersonStatus;
import com.mattjbishop.droptest.core.Status;
import com.mattjbishop.droptest.utils.ResourceHelper;
import com.mattjbishop.droptest.views.PersonView;
import com.mattjbishop.droptest.views.PersonStatusView;
import com.mattjbishop.droptest.utils.ResourceHelper.*;
import com.google.common.base.Optional;
import com.codahale.metrics.annotation.Timed;
import org.mongojack.JacksonDBCollection;
import org.mongojack.DBQuery;
import org.mongojack.DBCursor;

import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import java.util.ArrayList;
import java.util.List;

@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
	
	private JacksonDBCollection<Person, String> people;
    private JacksonDBCollection<Status, String> statuses;
	
	public PersonResource(JacksonDBCollection<Person, String> people,
                          JacksonDBCollection<Status, String> statuses) {
		this.people = people;
        this.statuses = statuses;
	}
	
	@GET
    @Timed
	public PersonStatus getPerson(@PathParam("personId") String personId) {

        // replace the ids with HATEOAS links??
        return findPerson(personId);
    }

    @GET
    @Path("/m")
    @Produces(MediaType.TEXT_HTML)
    @Timed
    public PersonStatusView getPersonStatusViewMustache(@PathParam("personId") String personId) {
        PersonStatus person = findPerson(personId);
        return new PersonStatusView(person);
    }

    private PersonStatus findPerson(String personId) {
        List<Status> personStatuses = new ArrayList<>();
        DBCursor<Person> pCursor = people.find(DBQuery.is("name", personId));
        ResourceHelper.notFoundIfNull(pCursor);

        // get the status objects and add to the PersonStatus
        DBCursor<Status> sCursor = statuses.find(DBQuery.is("personId", personId));

        if (sCursor != null) {
            while (sCursor.hasNext()) {
                // the event and person id needs to be converted to a HATEOAS link
                personStatuses.add(sCursor.next());
            }
        }
        return new PersonStatus(pCursor.next(), personStatuses);
    }
}