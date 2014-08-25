package com.mattjbishop.droptest.utils;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.mongojack.DBCursor;


/**
 * Created by matt on 28/07/2014.
 */
public class ResourceHelper {

    public static void notFoundIfNull(DBCursor<?> cursor) {
        if (!cursor.hasNext()) {
            // http://stackoverflow.com/questions/15111134/return-jersey-exceptions-in-json

/*            {"developerMessage" : "Verbose, plain language description of
                the problem for the app developer with hints about how to fix
                it.", "userMessage":"Pass this message on to the app user if
                needed.", "errorCode" : 12345, "more info":
                "http://dev.teachdogrest.com/errors/12345"}*/

            throw new WebApplicationException(Status.NOT_FOUND);
        }
    }

    public static void notFoundIfNull(Object obj) {
        errorIfNull(obj, Status.NOT_FOUND);
    }

    public static void errorIfNull(Object obj, Status status) {
        if (obj == null) {
            throw new WebApplicationException(status);
        }
    }

    public static Response ok(Object representation) {
        return Response.ok(representation).build();
    }

}