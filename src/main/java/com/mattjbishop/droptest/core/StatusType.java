package com.mattjbishop.droptest.core;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by matt on 01/08/2014.
 */
// @JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum StatusType {
    CONVICTED(1, "Convicted"),
    DISQUALIFIED(2, "Disqualified"),
    FIREARMS_LICENCE (3, "Firearms_Licence"),
    INFORMATION(4, "Information"),
    IMPENDING_PROSECUTION(5, "Impending_Prosecution"),
    MISSING(6, "Missing"),
    OVERSTAYER(7, "Overstayer"),
    WANTED(8,"Wanted");

    private Integer id;

    private String name;

    private StatusType(final Integer id, final String name) {
        this.id = id;
        this.name = name;
    }

    /*@Override
    public String toString() {
        return name;
    }

    @JsonCreator
    public static StatusType create(String value) {
        if(value == null) {
            throw new IllegalArgumentException();
        }
        for(StatusType v : values()) {
            if(value.equals(v.name())) {
                return v;
            }
        }
        throw new IllegalArgumentException();
    }*/
}
