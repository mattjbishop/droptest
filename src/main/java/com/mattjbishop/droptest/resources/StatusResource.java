package com.mattjbishop.droptest.resources;

import com.codahale.metrics.annotation.Timed;
import com.mattjbishop.droptest.core.Status;
import com.mattjbishop.droptest.utils.ResourceHelper;
import com.mongodb.DB;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by matt on 25/08/2014.
 */
@Path("/status")
@Produces(MediaType.APPLICATION_JSON)
public class StatusResource {

    private JacksonDBCollection<Status, String> status;

    public StatusResource(DB db) {
        this.status = JacksonDBCollection.wrap(db.getCollection("status"), Status.class, String.class);
    }

    @Path("{id}")
    @GET
    @Timed
    public Response getStatus(@PathParam("id") String id) {
        DBCursor<Status> cursor = status.find(DBQuery.is("_id", id));
        ResourceHelper.notFoundIfNull(cursor);

        return Response.ok(cursor.next()).build();
    }

    @POST
    @Timed
    public Response createStatus(@Valid Status s) {
        /*DBCursor<Person> cursor = status.find(DBQuery.is("name", person.getFullName()));
        if (cursor.hasNext()) {
            return Response.status(Response.Status.CONFLICT).build();
        }*/

        status.save(s);

        return Response.noContent().build();
    }
}
