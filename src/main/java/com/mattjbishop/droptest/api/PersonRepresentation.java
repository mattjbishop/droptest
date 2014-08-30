package com.mattjbishop.droptest.api;

import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.core.Status;
import com.mattjbishop.droptest.halapino.SelfBuilder;
import com.mattjbishop.droptest.halapino.annotations.HALEmbedded;
import com.mattjbishop.droptest.halapino.annotations.HALLink;
import com.mattjbishop.droptest.halapino.annotations.HALResource;

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

    @HALEmbedded
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
