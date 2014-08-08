package com.mattjbishop.droptest.hal;

import com.mattjbishop.droptest.hal.annotations.HALEmbedded;
import com.mattjbishop.droptest.hal.annotations.HALLink;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * Created by matt on 08/08/2014.
 * This is based on the reflectionHelper class in Halarious
 */
public class HALReflectionHelper {

    public static final int IGNORED_FIELD_MODIFIERS = Modifier.FINAL | Modifier.STATIC | Modifier.VOLATILE;

    final static Logger logger = LoggerFactory.getLogger(HALReflectionHelper.class);

    public static boolean isResource(Field f){
        return isResource(new FieldAttributes(f));
    }

    public static boolean isResource(FieldAttributes field) {
        HALEmbedded annotation = field.getAnnotation(HALEmbedded.class);

        if (annotation != null) {
            logger.info("field.getName() = {}", field.getName());
            logger.info("annotation.name() = {}", annotation.name());
            return true;
        }
        return false;
    }

    public static boolean getResourceName(Field f) {
        return false;
    }


    public static boolean isLink(Field f) {
        return isLink(new FieldAttributes(f));
    }

    public static boolean isLink(FieldAttributes field){
        HALLink annotation = field.getAnnotation(HALLink.class);
        return (annotation != null);
    }

    /**
     * Answers whether the Field should be copied. By default it ignores (answers 'false' to)
     * static, final, volatile and transient fields.
     *
     * @param f a Field of a Class.
     * @return true if the field should be copied into the resource. Otherwise, false.
     */
    public static boolean isIncluded(Field f)
    {
        /*if ((inclusionAnnotations == null && exclusionAnnotations == null)
                || f.getAnnotations().length == 0)
        {
            return ((f.getModifiers() & IGNORED_FIELD_MODIFIERS) == 0);
        }

        for (Annotation annotation : f.getAnnotations())
        {
            Class<? extends Annotation> type = annotation.annotationType();

            if (inclusionAnnotations != null && inclusionAnnotations.contains(type))
            {
                return true;
            }

            if (exclusionAnnotations != null && exclusionAnnotations.contains(type))
            {
                return false;
            }
        }
*/
        return ((f.getModifiers() & IGNORED_FIELD_MODIFIERS) == 0);
    }

    public static Field[] getDeclaredFields(Class<?> type)
    {
        return type.getDeclaredFields();
    }
}
