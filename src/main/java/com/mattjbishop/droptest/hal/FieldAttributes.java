package com.mattjbishop.droptest.hal;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;

import static com.google.common.base.Preconditions.*;

/**
 * Created by matt on 08/08/2014.
 * This is basically a copy of the Google gson FieldAttributes class, using guava for the preconditions
 */
public final class FieldAttributes {
    private final Field field;

    /**
     * Constructs a Field Attributes object from the {@code f}.
     *
     * @param f the field to pull attributes from
     */
    public FieldAttributes(Field f) {
        checkNotNull(f);
        this.field = f;
    }

    /**
     * @return the declaring class that contains this field
     */
    public Class<?> getDeclaringClass() {
        return field.getDeclaringClass();
    }

    /**
     * @return the name of the field
     */
    public String getName() {
        return field.getName();
    }

    /**
     * <p>For example, assume the following class definition:
     * <pre class="code">
     * public class Foo {
     *   private String bar;
     *   private List&lt;String&gt; red;
     * }
     *
     * Type listParmeterizedType = new TypeToken&lt;List&lt;String&gt;&gt;() {}.getType();
     * </pre>
     *
     * <p>This method would return {@code String.class} for the {@code bar} field and
     * {@code listParameterizedType} for the {@code red} field.
     *
     * @return the specific type declared for this field
     */
    public Type getDeclaredType() {
        return field.getGenericType();
    }

    /**
     * Returns the {@code Class} object that was declared for this field.
     *
     * <p>For example, assume the following class definition:
     * <pre class="code">
     * public class Foo {
     *   private String bar;
     *   private List&lt;String&gt; red;
     * }
     * </pre>
     *
     * <p>This method would return {@code String.class} for the {@code bar} field and
     * {@code List.class} for the {@code red} field.
     *
     * @return the specific class object that was declared for the field
     */
    public Class<?> getDeclaredClass() {
        return field.getType();
    }

    /**
     * Return the {@code T} annotation object from this field if it exist; otherwise returns
     * {@code null}.
     *
     * @param annotation the class of the annotation that will be retrieved
     * @return the annotation instance if it is bound to the field; otherwise {@code null}
     */
    public <T extends Annotation> T getAnnotation(Class<T> annotation) {
        return field.getAnnotation(annotation);
    }

    /**
     * Return the annotations that are present on this field.
     *
     * @return an array of all the annotations set on the field
     * @since 1.4
     */
    public Collection<Annotation> getAnnotations() {
        return Arrays.asList(field.getAnnotations());
    }

    /**
     * Returns {@code true} if the field is defined with the {@code modifier}.
     *
     * <p>This method is meant to be called as:
     * <pre class="code">
     * boolean hasPublicModifier = fieldAttribute.hasModifier(java.lang.reflect.Modifier.PUBLIC);
     * </pre>
     *
     * @see java.lang.reflect.Modifier
     */
    public boolean hasModifier(int modifier) {
        return (field.getModifiers() & modifier) != 0;
    }


    @SuppressWarnings("unchecked")
    private static <T extends Annotation> T getAnnotationFromArray(
            Collection<Annotation> annotations, Class<T> annotation) {
        for (Annotation a : annotations) {
            if (a.annotationType() == annotation) {
                return (T) a;
            }
        }
        return null;
    }
}
