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
	private String mongoUri = "localhost:27017/yourdb";

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
    public String getMongoUri() {
        return mongoUri;
    }

    @JsonProperty
    public void setMongoUri(String mongoUri) {
        this.mongoUri = mongoUri;
    }
}