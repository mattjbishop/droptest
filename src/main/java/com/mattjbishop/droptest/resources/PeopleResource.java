package com.mattjbishop.droptest.resources;

import com.mattjbishop.droptest.core.Person;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;


@Path("/people")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {
    
	private JacksonDBCollection<Person, String> people;
		
	public PeopleResource(JacksonDBCollection<Person, String> people) {
		this.people = people;
	}
	
	@GET
    @Timed
    public Person getPerson(@QueryParam("name") Optional<String> name) {
    	return new Person();
    }
	
	@POST
	@Timed
	public Response createPerson(@Valid Person person) {
		DBCursor<Person> cursor = people.find(DBQuery.is("name", person.getFullName()));
	    if (cursor.hasNext()) {
	    	return Response.status(Response.Status.CONFLICT).build();
	    }

	    people.save(person);

	    return Response.noContent().build();
	}
}