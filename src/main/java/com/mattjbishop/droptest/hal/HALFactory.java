package com.mattjbishop.droptest.hal;

import static com.google.common.base.Preconditions.checkNotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by matt on 07/08/2014.
 */
public class HALFactory {

    private static HALFactory factoryInstance;
    final static Logger logger = LoggerFactory.getLogger(HALFactory.class);

    // self links
    private Map<String, UriBuilder> selfBuilders;

    // paths for self links
    //// http://stackoverflow.com/questions/13484350/find-a-list-of-all-jersey-resource-methods-in-my-app
    //// 


    private HALFactory() {
    }

    public static HALFactory getFactory() {
        if (null == factoryInstance) {
            factoryInstance = new HALFactory(); // not thread safe !!!

            logger.info("factory created");
        }

        return factoryInstance;
    }

    // factory methods here

    public HALRepresentation getHALRepresentation(Object resource, UriInfo uriInfo) {
        logger.info("creating a new representation");
        logger.info("baseuri is {}", uriInfo.getBaseUri());

        HALRepresentation representation = new HALRepresentation();
        HALComposer composer = new HALComposer(); // !!! this should be injected

        composer.compose(resource,representation);

        // set links
        // setLinks(representation, uriInfo);

        // add in any other global links, namespaces, etc

        // go through each of the links and update with the base path??

        return representation;
    }

    public void register(Class resourceObject, Class resource) {

        if (selfBuilders == null) {
            selfBuilders = new HashMap<String, UriBuilder>();
        }

        if (selfBuilders.containsKey(resourceObject.getName())) {
            // throw an exception?
        }

        UriBuilder builder = UriBuilder.fromResource(resource);
        selfBuilders.put(resourceObject.getName(), builder);
        builder.host("{hostname}");

        logger.info("creating uribuilder");
    }

    public UriBuilder getSelfBuilder(Class resourceObject) {
        UriBuilder builder = selfBuilders.get(resourceObject.getName());

        checkNotNull(builder);

        return builder.clone();
    }

    private void setLinks(HALRepresentation representation, UriInfo uriInfo) {

        // representation.setSelfLink(uriInfo.getRequestUri().toASCIIString());

        logger.info("going to get a link for {}", representation.getResource().getClass());

        UriBuilder builder = getSelfBuilder(representation.getResource().getClass());

        // cycle through the representation and any embedded objects and build the self links

        URI uri = builder.build();
        logger.info("building uri {}", uri.toString());

    }

}
