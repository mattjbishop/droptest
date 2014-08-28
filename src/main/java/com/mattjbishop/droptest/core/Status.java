package com.mattjbishop.droptest.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mattjbishop.droptest.hal.SelfBuilder;
import org.hibernate.validator.constraints.Length;
import org.mongojack.ObjectId;
import java.util.Date;

/**
 * Created by matt on 01/08/2014.
 */
public class Status implements SelfBuilder {

    // id - this could be redundant if this is refactored to be a nested document
    @ObjectId
    @JsonProperty("_id")
    private String id;

    @JsonProperty("personId")
    private String personId;

    @JsonProperty("statusType")
    private StatusType statusType;

    @JsonProperty("createdOn")
    private Date createdOn;

    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("reason")
    private String reason;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public StatusType getStatusType() {
        return statusType;
    }

    public void setStatusType(StatusType statusType) {
        this.statusType = statusType;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
