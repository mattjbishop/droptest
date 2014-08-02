package com.mattjbishop.droptest.core;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

/**
 * Created by Matt.
 * Ignoring properties while moving to status resource
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {	
	private String id;

	private String fullName;
	
	private String dateOfBirth;
	
	private String gender;

	private String pncId;
	
	private String croNumber;
	
	private String driverNumber;

    @ObjectId
    @JsonProperty("_id")
    public String getId() {
    	return id;
    }
    
	@ObjectId
    @JsonProperty("_id")
    public void setId(String id) {
    	this.id = id;
    }
	
    @JsonProperty("name")
    public String getFullName() {
    	return fullName;
    }
    
    @JsonProperty("name")
    public void setFullName(String name) {
    	this.fullName = name;
    }
	
    @JsonProperty("dob")
    public String getDateOfBirth() {
    	return dateOfBirth;
    }
    
    @JsonProperty("dob")
    public void setDateOfBirth(String dob) {
    	this.dateOfBirth = dob;
    }
	
    @JsonProperty("gender")
    public String getGender() {
    	return gender;
    }
    
    @JsonProperty("gender")
    public void setGender(String gender) {
    	this.gender = gender;
    }
	
    @JsonProperty("pncId")
    public String getPncId() {
    	return pncId;
    }
    
    @JsonProperty("pncId")
    public void setPncId(String pncId) {
    	this.pncId = pncId;
    }
	
    @JsonProperty("croNumber")
    public String getCroNumber() {
    	return croNumber;
    }
    
    @JsonProperty("croNumber")
    public void setCroNumber(String croNumber) {
    	this.croNumber = croNumber;
    }
	
    @JsonProperty("driverNumber")
    public String getDriverNumber() {
    	return driverNumber;
    }
    
    @JsonProperty("driverNumber")
    public void setDriverNumber(String driverNumber) {
    	this.driverNumber = driverNumber;
    }
}