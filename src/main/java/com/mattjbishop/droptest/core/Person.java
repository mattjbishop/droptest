package com.mattjbishop.droptest.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mattjbishop.droptest.halapino.SelfBuilder;
import org.mongojack.ObjectId;

/**
 * Created by Matt.
 * Ignoring properties (for the status stuff in the DB) while moving to status resource
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person implements SelfBuilder {
    @ObjectId
    @JsonProperty("_id")
	private String id;

    @JsonProperty("name")
	private String fullName;

    @JsonProperty("dob")
	private String dateOfBirth;

    @JsonProperty("gender")
	private String gender;

    @JsonProperty("pncId")
	private String pncId;

    @JsonProperty("croNumber")
	private String croNumber;

    @JsonProperty("driverNumber")
	private String driverNumber;


    public String getId() {
    	return id;
    }

    public void setId(String id) {
    	this.id = id;
    }

    public String getFullName() {
    	return fullName;
    }
    
    public void setFullName(String name) {
    	this.fullName = name;
    }
	
    public String getDateOfBirth() {
    	return dateOfBirth;
    }
    
    public void setDateOfBirth(String dob) {
    	this.dateOfBirth = dob;
    }
	
    public String getGender() {
    	return gender;
    }
    
    public void setGender(String gender) {
    	this.gender = gender;
    }
	
    public String getPncId() {
    	return pncId;
    }
    
    public void setPncId(String pncId) {
    	this.pncId = pncId;
    }
	
    public String getCroNumber() {
    	return croNumber;
    }
    
    public void setCroNumber(String croNumber) {
    	this.croNumber = croNumber;
    }
	
    public String getDriverNumber() {
    	return driverNumber;
    }
    
    public void setDriverNumber(String driverNumber) {
    	this.driverNumber = driverNumber;
    }
}