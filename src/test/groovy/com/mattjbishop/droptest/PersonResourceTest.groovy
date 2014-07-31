package com.mattjbishop.droptest

import com.mattjbishop.droptest.core.Person
import com.mattjbishop.droptest.resources.PersonResource
import com.mongodb.MongoClient
import org.mongojack.JacksonDBCollection
import spock.lang.Specification

import javax.ws.rs.WebApplicationException


/**
 * Created by matt on 31/07/2014.
 */
class PersonResourceTest extends Specification {
    void setup() {

    }

    void cleanup() {

    }

    def "should give me an existing user"() {
        given:
        def mongoClient = new MongoClient()
        def database = mongoClient.getDB("foo")
        def people = JacksonDBCollection.wrap(database.getCollection("person"), Person.class, String.class);
        def person = new PersonResource(people)

        when:
        def matt = person.getPerson("matt")

        then:
        matt.fullName == "matt"

        cleanup:
        mongoClient.close()
    }

    def "should throw a 404 if the order is not found"() {
        given:
        def mongoClient = new MongoClient()
        def database = mongoClient.getDB("foo")
        def people = JacksonDBCollection.wrap(database.getCollection("person"), Person.class, String.class);
        def person = new PersonResource(people)

        when:
        person.getPerson("this person does not exist")

        then:
        def e = thrown(WebApplicationException)
        e.response.status == 404

        cleanup:
        mongoClient.close()
    }
}
