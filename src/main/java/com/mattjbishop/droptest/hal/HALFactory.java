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

    // any default links

    // paths for self links
    //// http://stackoverflow.com/questions/13484350/find-a-list-of-all-jersey-resource-methods-in-my-app
    //// 


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
        logger.info("baseuri is {}", uriInfo.getBaseUri());

        HALRepresentation representation = new HALRepresentation();
        HALComposer composer = new HALComposer(); // !!! this should be injected

        composer.compose(resource,representation);

        representation.setSelfLink(uriInfo.getRequestUri().toASCIIString());

        // add in any other global links, namespaces, etc

        // go through each of the links and update with the base path??

        return representation;
    }


}