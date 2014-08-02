package com.mattjbishop.droptest.views;

import com.mattjbishop.droptest.api.PersonStatus;
import com.mattjbishop.droptest.core.Person;
import io.dropwizard.views.View;

/**
 * Created by matt on 27/07/2014.
 */
public class PersonStatusView extends View {
    private final Person person;

    public PersonStatusView(PersonStatus person) {
        super("mustache/person.mustache");
        this.person = person.getPerson();
    }

    public Person getPerson() {
        return person;
    }
}