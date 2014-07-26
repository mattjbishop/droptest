package com.mattjbishop.droptest.health;

import com.mongodb.MongoClient;
import com.codahale.metrics.health.HealthCheck;

public class MongoHealthCheck extends HealthCheck {

    private MongoClient mongoClient;

    public MongoHealthCheck(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    @Override
    protected Result check() throws Exception {
        mongoClient.getConnectPoint();
        return Result.healthy();
    }
	
}