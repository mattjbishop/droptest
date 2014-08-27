package com.mattjbishop.droptest.hal;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * lightweight representation, serialised by Jackson to JSON.
 */
@JsonPropertyOrder({"_links"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class HALRepresentation {

    @JsonSerialize(using = HALLinkSerializer.class)
    @JsonProperty("_links")
    private Map<String, List<Link>> _links;

    // namespaces

    @JsonSerialize(using = HALEmbeddedSerializer.class)
    @JsonProperty("_embedded")
    private Map<String, List<HALRepresentation>> _embedded;

    @JsonUnwrapped
    private Object resource = null;

    public Object getResource() {
        return resource;
    }

    public void setResource(Object resource) {
        this.resource = resource;
    }

    public void addLink(Link link) {
        addLink(link, false);
    }

    public void addLink(Link link, boolean isSingleLink) {
        if (_links == null) {
            _links = new HashMap<String, List<Link>>();
        }

        List<Link> linksForName = _links.get(link.getName());

        if (linksForName == null)
        {
            linksForName = new ArrayList<Link>();
            _links.put(link.getName(), linksForName);
        } else if (isSingleLink) {
            linksForName.clear();
        }

        linksForName.add(link);
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

    @JsonIgnore
    public Map<String, List<HALRepresentation>> getEmbedded() {
        return _embedded;
    }

    public void setSelfLink(String uri) {

        checkNotNull(uri, "Cannot set the self link to be null");

        Link self = new Link();
        self.setName("self");
        self.setHref(uri);

        addLink(self, true);
    }

    @JsonIgnore
    public Link getSelfLink()
    {
        if (_links == null) {
            _links = new HashMap<String, List<Link>>();
        }

        List<Link> linksForName = _links.get("self");

        checkNotNull(linksForName);

        return linksForName.iterator().next();
    }

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
