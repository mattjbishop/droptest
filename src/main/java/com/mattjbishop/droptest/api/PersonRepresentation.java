package com.mattjbishop.droptest.api;

import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.core.Status;
import com.mattjbishop.halapino.SelfBuilder;
import com.mattjbishop.halapino.annotations.HALEmbedded;
import com.mattjbishop.halapino.annotations.HALLink;
import com.mattjbishop.halapino.annotations.HALResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

/**
 * Created by matt on 01/08/2014.
 */
public class PersonRepresentation implements SelfBuilder {

    @HALLink
    private String self;

    @HALLink(title = "foo", curie = "bar")
    private String foo = "/foo2";

    @HALResource
    private Person person;

    public PersonRepresentation(Person person) {
        this.person = person;
    }

    public String getId() {
        checkNotNull(person);
        return this.person.getId();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
