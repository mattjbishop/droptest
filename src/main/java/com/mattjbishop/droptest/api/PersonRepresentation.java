package com.mattjbishop.droptest.api;


import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.core.Status;
import com.mattjbishop.droptest.hal.SelfBuilder;
import com.mattjbishop.droptest.hal.annotations.HALEmbedded;
import com.mattjbishop.droptest.hal.annotations.HALLink;
import com.mattjbishop.droptest.hal.annotations.HALResource;

import com.fasterxml.jackson.annotation.JsonProperty;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

/**
 * Created by matt on 01/08/2014.
 */
public class PersonRepresentation implements SelfBuilder {

    @HALLink
    private String self;

    @HALLink(href = "/foo", title = "foo")
    private String foo;

    @HALResource
    private Person person;

    // status objects
    // embedded
    // "_embedded": { "ea:order": [{obj1}, {obj2}] }
    @HALEmbedded(name = "status")
    @JsonProperty("status")
    private List<Status> statuses;

    public PersonRepresentation(Person person, List<Status> statuses) {
        this.person = person;
        this.statuses = statuses;
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

    public List<Status> getStatuses() {
        return statuses;
    }

    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
