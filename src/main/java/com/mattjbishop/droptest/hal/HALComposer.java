package com.mattjbishop.droptest.hal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.*;
import java.lang.reflect.Field;


/**
 * Created by matt on 08/08/2014.
 * Creates the fully formed HAL representation for passing back to clients
 */
public class HALComposer {

    final static Logger logger = LoggerFactory.getLogger(HALFactory.class);


    protected void compose(Object from, HALRepresentation to) {
        logger.info("composing");

        to.setResource(from); // give the representation the base resource

        Field[] fields = HALReflectionHelper.getDeclaredFields(from.getClass());

        try
        {
            for (Field f : fields)
            {
                if (HALReflectionHelper.isIncluded(f))
                {
                    add(to, from, f);
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

    private void add(HALRepresentation to, Object from, Field field)
            throws IllegalAccessException, NullPointerException {

        field.setAccessible(true);
        Object value = field.get(from);

        // checkNotNull(value, "field has no value - which is weird.");

        logger.info("found property: {}", field.getName());

        // if a link, add it
        if (HALReflectionHelper.isLink(field)) {
            logger.info("{} is a link", field.getName());
        }

        // if an embedded resource, add it
        if (HALReflectionHelper.isResource(field)) {
            logger.info("{} is an embedded resource", field.getName());
            // get the value and compose it - adding the result to "to"
            HALRepresentation embeddedResource = new HALRepresentation();
            compose(value, embeddedResource);
            to.addEmbedded("key", embeddedResource);
        }
    }
}
