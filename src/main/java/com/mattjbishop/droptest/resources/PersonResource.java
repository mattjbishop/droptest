package com.mattjbishop.droptest.resources;

import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.views.PersonView;
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

@Path("/people/{personId}")
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {
	
	private JacksonDBCollection<Person, String> people;
	
	public PersonResource(JacksonDBCollection<Person, String> people) {
		this.people = people;
	}
	
	@GET
    @Timed
	public Person getPerson(@PathParam("personId") String personId) {
	
		DBCursor<Person> cursor = people.find(DBQuery.is("name", personId));
		// need to handle the case that the person is not found		
		return cursor.next();
    }

    @GET
    @Path("/m")
    @Produces(MediaType.TEXT_HTML)
    public PersonView getPersonViewMustache(@PathParam("personId") String personId) {
        DBCursor<Person> cursor = people.find(DBQuery.is("name", personId));
        // need to handle the case that the person is not found
        return new PersonView(cursor.next());
    }
	
}