package com.mattjbishop.droptest.hal.annotations;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = {ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonUnwrapped    // Unwrapped needs to be here for the custom serializer to work properly!
public @interface HALResource {}