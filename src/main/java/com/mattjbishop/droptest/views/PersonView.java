package com.mattjbishop.droptest.views;

import com.mattjbishop.droptest.api.PersonRepresentation;
import com.mattjbishop.droptest.core.Person;
import io.dropwizard.views.View;

/**
 * Created by matt on 27/07/2014.
 */
public class PersonView extends View {
    private final Person person;

    public PersonView(PersonRepresentation person) {
        super("mustache/person.mustache");
        this.person = person.getPerson();
    }

    public Person getPerson() {
        return person;
    }
}