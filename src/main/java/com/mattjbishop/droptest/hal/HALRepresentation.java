package com.mattjbishop.droptest.hal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static com.google.common.base.Preconditions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonPropertyOrder({"_links"})
// @JsonInclude(JsonInclude.Include.NON_NULL)
public class HALRepresentation {

    // custom serializer here
    @JsonSerialize(using = HALLinkSerializer.class)
    private List<Link> _links;

    @JsonSerialize(using = HALEmbeddedSerializer.class)
    private Map<String, List<HALRepresentation>> _embedded;

    // need to filter this to make sure that no links are serialised unless we want them to be.
    // filter out anything marked as embedded - and then
    @JsonUnwrapped
    private Object resource = null;

    public Object getResource() {
        return resource;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

    public void addLink(Link link) {
        // lazy loading of _links
        if (_links == null) {
            _links = new ArrayList<Link>();
        }
        _links.add(link);
    }

    public void addEmbedded(String key, HALRepresentation resource) {
        if (_embedded == null) {
            _embedded = new HashMap<String, List<HALRepresentation>>();
        }

        checkNotNull(key, "Cannot embed resource using null key");
        checkNotNull(resource, "Cannot embed null resource");

        List<HALRepresentation> forRel = acquireResourcesForRel(key);
        forRel.add(resource);

    }

    /*public Resource addResource(String rel, Resource resource, boolean isMultiple)
    {
        if (rel == null) throw new ResourceException("Cannot embed resource using null 'rel'");
        if (resource == null) throw new ResourceException("Cannot embed null resource");

        List<Resource> forRel = acquireResourcesForRel(rel);
        forRel.add(resource);

        if (isMultiple)
        {
            arrayResourceRels.add(rel);
        }

        return this;
    }*/


    private List<HALRepresentation> acquireResourcesForRel(String rel)
    {
        List<HALRepresentation> forRel = _embedded.get(rel);

        if (forRel == null)
        {
            forRel = new ArrayList<HALRepresentation>();
            _embedded.put(rel, forRel);
        }

        return forRel;
    }
}

/*
{
     "_links": {
       "self": { "href": "/blog-post" },
       "author": { "href": "/people/alan-watts" }
     },
     "_embedded": {
       "author": {
         "_links": { "self": { "href": "/people/alan-watts" } },
         "name": "Alan Watts",
         "born": "January 6, 1915",
         "died": "November 16, 1973"
       }
     }
   }



{
     "_links": {
       "self": { "href": "/orders" },
       "next": { "href": "/orders?page=2" },
       "find": { "href": "/orders{?id}", "templated": true }
     },
     "_embedded": {
       "orders": [{
           "_links": {
             "self": { "href": "/orders/123" },
             "basket": { "href": "/baskets/98712" },
             "customer": { "href": "/customers/7809" }
           },
           "total": 30.00,
           "currency": "USD",
           "status": "shipped",
         },{
           "_links": {
             "self": { "href": "/orders/124" },
             "basket": { "href": "/baskets/97213" },
             "customer": { "href": "/customers/12369" }
           },
           "total": 20.00,
           "currency": "USD",
           "status": "processing"
       }]
     },
     "currentlyProcessing": 14,
     "shippedToday": 20
   }


 */
