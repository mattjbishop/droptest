package com.mattjbishop.droptest.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.mongojack.ObjectId;

public class Person {	
	private String id;

	private String fullName;
	
	private String dateOfBirth;
	
	private String gender;
	
	private Boolean wantedMissing;
	
	private Boolean disqualifiedDriver;
	
	private Boolean impendingProsecution;
	
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
	
    @JsonProperty("wantedMissing")
    public Boolean getWantedMissing() {
    	return wantedMissing;
    }
    
    @JsonProperty("wantedMissing")
    public void setWantedMissing(Boolean wm) {
    	this.wantedMissing = wm;
    }
	
    @JsonProperty("disqualifiedDriver")
    public Boolean getDisqualifiedDriver() {
    	return disqualifiedDriver;
    }
    
    @JsonProperty("disqualifiedDriver")
    public void setDisqualifiedDriver(Boolean dd) {
    	this.disqualifiedDriver = dd;
    }
	
    @JsonProperty("impendingProsecution")
    public Boolean getImpendingProsecution() {
    	return impendingProsecution;
    }
    
    @JsonProperty("impendingProsecution")
    public void setImpendingProsecution(Boolean ip) {
    	this.impendingProsecution = ip;
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