package com.mattjbishop.droptest.hal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import static com.google.common.base.Preconditions.*;
import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;


/**
 * Created by matt on 08/08/2014.
 * Creates the fully formed HAL representation for passing back to clients
 */
public class HALComposer {

    final static Logger logger = LoggerFactory.getLogger(HALComposer.class);

    protected void compose(Object from, HALRepresentation to) {
        logger.info("composing");

        to.setResource(from);

        Field[] fields = HALReflectionHelper.getDeclaredFields(from.getClass());

        try
        {
            for (Field f : fields)
            {
                if (HALReflectionHelper.isIncluded(f))
                {
                    read(to, from, f);
                }
            }
        }
        catch (IllegalAccessException e)
        {
            logger.info("Illegal Access - resource exception should be thrown here");
            //throw new ResourceException(e);
        }
        catch (NullPointerException e)
        {
            logger.info("Null Pointer - resource exception should be thrown here");
        }
    }

    private void read(HALRepresentation to, Object from, Field field)
            throws IllegalAccessException, NullPointerException {

        logger.info("found property: {}", field.getName());

        if (HALReflectionHelper.isLink(field)) {
            readLink(to, from, field);
        }

        if (HALReflectionHelper.isResource(field)) {
            readResource(to, from, field);
        }
    }

    private void readLink(HALRepresentation to, Object from, Field field)
            throws IllegalAccessException, NullPointerException {

        logger.info("{} is a link", field.getName());
        field.setAccessible(true);
        Object value = field.get(from);

        Link link = HALReflectionHelper.getLink(field);

        checkNotNull(link, "Field is not a link");

        to.addLink(link);
    }

    private void readResource(HALRepresentation to, Object from, Field field)
            throws IllegalAccessException, NullPointerException {

        logger.info("{} is an embedded resource", field.getName());
        field.setAccessible(true);
        Object value = field.get(from);


        if (value instanceof Collection) {
            logger.info("is collection");

            logger.info("{} objects in collection", ((Collection) value).size());

            // cycle through, read them all in
            for (Object o : (Collection)value) {
                readResource(to, o, field.getName());
            }
        } else {
            readResource(to, value, field.getName());
        }

    }

    private void readResource(HALRepresentation to, Object from, String name)
    {
        logger.info("reading resource {}", name);
        HALRepresentation embeddedResource = new HALRepresentation();
        compose(from, embeddedResource);
        to.addEmbedded(name, embeddedResource);

        logger.info("resource class is {}", from.getClass().toString());
    }

}
