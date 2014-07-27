package com.mattjbishop.droptest.views;

import io.dropwizard.views.View;
import com.mattjbishop.droptest.core.Person;

/**
 * Created by matt on 27/07/2014.
 */
public class PersonView extends View {
    private final Person person;

    public PersonView(Person person) {
        super("mustache/person.mustache");
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}