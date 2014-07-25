package com.mattjbishop.droptest;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.hibernate.validator.constraints.NotEmpty;

public class DropTestConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName = "Stranger";
	
	@NotEmpty
	private String mongoHost = "localhost";
	
    @Min(1)
    @Max(65535)
    private int mongoPort = 27017;

    @NotEmpty
    private String mongoDb = "yourdb";

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }

    @JsonProperty
    public String getDefaultName() {
        return defaultName;
    }

    @JsonProperty
    public void setDefaultName(String name) {
        this.defaultName = name;
    }
	
    @JsonProperty
    public String getMongoHost() {
        return mongoHost;
    }

    @JsonProperty
    public void setMongoHost(String mongoHost) {
        this.mongoHost = mongoHost;
    }
	
    @JsonProperty
    public int getMongoPort() {
        return mongoPort;
    }

    @JsonProperty
    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }
	
    @JsonProperty
    public String getMongoDb() {
        return mongoDb;
    }

    @JsonProperty
    public void setMongoDb(String mongoDb) {
        this.mongoDb = mongoDb;
    }
}