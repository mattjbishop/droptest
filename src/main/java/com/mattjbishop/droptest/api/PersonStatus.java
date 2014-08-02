package com.mattjbishop.droptest.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.core.Status;

import java.util.List;

/**
 * Created by matt on 01/08/2014.
 */
public class PersonStatus {

    // Person
    private Person person;

    // Person URI - for HATEOAS

    // status objects
    private List<Status> statuses;

    public PersonStatus(Person person, List<Status> statuses) {
        this.person = person;
        this.statuses = statuses;
    }

    @JsonUnwrapped
    public Person getPerson() {
        return person;
    }

    @JsonUnwrapped
    public void setPerson(Person person) {
        this.person = person;
    }

    @JsonProperty("status")
    public List<Status> getStatuses() {
        return statuses;
    }

    @JsonProperty("status")
    public void setStatuses(List<Status> statuses) {
        this.statuses = statuses;
    }
}
