package com.mattjbishop.droptest.hal.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.mattjbishop.droptest.hal.Views;

import java.lang.annotation.*;

/**
 * Created by matt on 08/08/2014.
 */
@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@JacksonAnnotationsInside
@JsonView(Views.HideEmbedded.class)
public @interface HALEmbedded {
    String name() default "";
}
