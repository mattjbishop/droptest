package com.mattjbishop.droptest.hal.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonView;
import com.mattjbishop.droptest.hal.Views;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Inherited
@JacksonAnnotationsInside
@JsonView(Views.HideLinks.class)
public @interface HALLink {
    String name() default "";
    String title() default "";
}