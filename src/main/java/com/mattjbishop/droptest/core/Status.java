package com.mattjbishop.droptest.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.mongojack.ObjectId;
import java.util.Date;

/**
 * Created by matt on 01/08/2014.
 */
public class Status {

    // id - this could be redundant if this is refactored to be a nested document
    private String id;

    // this needs converting to a url??
    private String personId;

    // status
    private StatusType statusType;

    // created date
    private Date createdOn;

    // event_id
    private String eventId;

    // reason string
    private String reason;

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

    @JsonProperty("personId")
    public String getPersonId() {
        return personId;
    }

    @JsonProperty("personId")
    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @JsonProperty("statusType")
    public StatusType getStatusType() {
        return statusType;
    }

    @JsonProperty("statusType")
    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    @JsonProperty("createdOn")
    public Date getCreatedOn() {
        return createdOn;
    }

    @JsonProperty("createdOn")
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    @JsonProperty("eventId")
    public String getEventId() {
        return eventId;
    }

    @JsonProperty("eventId")
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    @JsonProperty("reason")
    public String getReason() {
        return reason;
    }

    @JsonProperty("reason")
    public void setReason(String reason) {
        this.reason = reason;
    }
}
