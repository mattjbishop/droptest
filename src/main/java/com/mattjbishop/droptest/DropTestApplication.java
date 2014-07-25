package com.mattjbishop.droptest;

import java.net.UnknownHostException;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import org.mongojack.JacksonDBCollection;
import com.mattjbishop.droptest.resources.DropTestResource;
import com.mattjbishop.droptest.resources.PeopleResource;
import com.mattjbishop.droptest.resources.PersonResource;
import com.mattjbishop.droptest.core.MongoManaged;
import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.health.MongoHealthCheck;
import com.mattjbishop.droptest.health.TemplateHealthCheck;

public class DropTestApplication extends Application<DropTestConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropTestApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<DropTestConfiguration> bootstrap) {
        // nothing to do yet
    }

	// !!! need to understand how Dropwizard handles exceptions... throws Exception is a blunt tool...
    @Override
    public void run(DropTestConfiguration configuration,
                    Environment environment) throws Exception {
		final DropTestResource resource = new DropTestResource(
			configuration.getTemplate(),
			configuration.getDefaultName()
		);
		
		
		final TemplateHealthCheck healthCheck =
		        new TemplateHealthCheck(configuration.getTemplate());
		
		// db setup
		MongoClient mongo = new MongoClient(configuration.getMongoHost(), configuration.getMongoPort());
		DB db = mongo.getDB(configuration.getMongoDb());
		JacksonDBCollection<Person, String> people = 
			JacksonDBCollection.wrap(db.getCollection("person"), Person.class, String.class);	
			
		MongoManaged mongoManaged = new MongoManaged(mongo);

		final PeopleResource peopleResource = new PeopleResource(people);
		final PersonResource personResource = new PersonResource(people);		

		final MongoHealthCheck mongoHealthCheck = 
			new MongoHealthCheck(mongo);
		
		environment.healthChecks().register("template", healthCheck);
		environment.healthChecks().register("mongo", mongoHealthCheck);
		
		environment.jersey().register(resource);
		environment.jersey().register(peopleResource);
		environment.jersey().register(personResource);
		environment.lifecycle().manage(mongoManaged);
    }
}