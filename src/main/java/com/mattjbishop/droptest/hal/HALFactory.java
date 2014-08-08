package com.mattjbishop.droptest.hal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
            factoryInstance = new HALFactory();
            logger.info("factory created");
        }

        return factoryInstance;
    }

    // factory methods here

    public HALRepresentation getHALRepresentation(Object resource) {
        logger.info("creating a new representation");

        HALRepresentation representation = new HALRepresentation();
        HALComposer composer = new HALComposer(); // !!! this should be injected

        composer.compose(resource,representation);

        // build any default links

        return representation;
    }

}
