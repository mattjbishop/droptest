package com.mattjbishop.droptest.core;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by matt on 01/08/2014.
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
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
}
