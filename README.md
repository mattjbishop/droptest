#droptest
========

A small project to test Dropwizard and MongoDB.

It uses the following:

* dropwizard
* dropwizard-views
* mustache.java
* mongojack
* mongodb java driver
* mongodb
* maven
* spock (with groovy)

The project includes a ProcFile so that you can run the service using [foreman](https://github.com/ddollar/foreman).
note: to run using foreman, you need to set an environment variable:

MONGO_URL = mongodb url

##Halapino:

This project is dependent on [halapino](https://github.com/mattjbishop/halapino). At the moment this is placed in a 
local repository. To make this work, you need to build halapino and then deploy it to a local directory using the following
command:

```
mvn deploy:deploy-file -Durl=file:///Users/matt/repo/ -Dfile=target/halapino-0.1.0.jar -DgroupId=com.mattjbishop -DartifactId=halapino -Dpackaging=jar -Dversion=0.1.0
```

##To Do:

There are a number of things still to do:

* nice errors and error pages
* API key
* Authentication and Authorisation
* unit testing and mockito
* soundex searching

##Also Used:

* idea-spock-enhancements (Intellij plugin)


