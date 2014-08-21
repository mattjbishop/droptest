package com.mattjbishop.droptest.hal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by matt on 07/08/2014.
 */
public class HALFactory {

    private static HALFactory factoryInstance;
    final static Logger logger = LoggerFactory.getLogger(HALFactory.class);

    private HALFactory() {
    }

    public static HALFactory getFactory() {
        if (null == factoryInstance) {
            factoryInstance = new HALFactory(); // not thread safe !!!
            logger.info("factory created");
        }

        return factoryInstance;
    }

    // factory methods here

    public HALRepresentation getHALRepresentation(Object resource, UriInfo uriInfo) {
        logger.info("creating a new representation");

        // this needs to get the context and pass it on to the composer

        HALRepresentation representation = new HALRepresentation();
        HALComposer composer = new HALComposer(uriInfo); // !!! this should be injected

        composer.compose(resource,representation);

        // build any default links

        return representation;
    }
}
