package com.mattjbishop.droptest;

import com.mattjbishop.droptest.api.PersonRepresentation;
import com.mattjbishop.droptest.core.Person;
import com.mattjbishop.droptest.core.Status;
import com.mattjbishop.halapino.HALFactory;
import com.mattjbishop.droptest.resources.StatusResource;
import io.dropwizard.Application;
import io.dropwizard.views.ViewBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mattjbishop.droptest.resources.DropTestResource;
import com.mattjbishop.droptest.resources.PeopleResource;
import com.mattjbishop.droptest.resources.PersonResource;
import com.mattjbishop.droptest.core.MongoManaged;
import com.mattjbishop.droptest.health.MongoHealthCheck;
import com.mattjbishop.droptest.health.TemplateHealthCheck;

import javax.ws.rs.core.UriBuilder;

public class DropTestApplication extends Application<DropTestConfiguration> {
    public static void main(String[] args) throws Exception {
        new DropTestApplication().run(args);
    }

    @Override
    public String getName() {
        return "droptest";
    }

    @Override
    public void initialize(Bootstrap<DropTestConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle());
        // bootstrap.addBundle(new AssetsBundle("/app/", "/"));  // !!! need to add in the angular app //

        // add in any default links to the HALFactory
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
		MongoClientURI uri = new MongoClientURI(configuration.getMongoUri());
		MongoClient mongo = new MongoClient(uri);
		DB db = mongo.getDB(uri.getDatabase());

        MongoManaged mongoManaged = new MongoManaged(mongo);

		final PeopleResource peopleResource = new PeopleResource(db);
		final PersonResource personResource = new PersonResource(db);
        final StatusResource statusResource = new StatusResource(db);

        // register the template UriBuilders
        HALFactory halFactory = HALFactory.getFactory();

        UriBuilder builder = UriBuilder.fromResource(PersonResource.class);
        halFactory.register(Person.class, builder);
        halFactory.register(PersonRepresentation.class, builder);

       // builder = UriBuilder.fromResource(StatusResource.class);
       // builder.path(StatusResource.class, "getStatus");
       // halFactory.register(Status.class, builder);

        halFactory.addCurie("bar", "/bar/{rel}");

		final MongoHealthCheck mongoHealthCheck = 
			new MongoHealthCheck(mongo);
		
		environment.healthChecks().register("template", healthCheck);
		environment.healthChecks().register("mongo", mongoHealthCheck);
		
		environment.jersey().register(resource);
		environment.jersey().register(peopleResource);
		environment.jersey().register(personResource);
        environment.jersey().register(statusResource);
		environment.lifecycle().manage(mongoManaged);
    }
}