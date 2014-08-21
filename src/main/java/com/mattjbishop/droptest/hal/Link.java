package com.mattjbishop.droptest.hal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.regex.Pattern;

/*
 * A Link Object represents a hyperlink from the containing resource to a URI.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Link {

/*  5.1.  href

    The "href" property is REQUIRED.

    Its value is either a URI [RFC3986] or a URI Template [RFC6570].

    If the value is a URI Template then the Link Object SHOULD have a
    "templated" attribute whose value is true.*/

    private String href;

/*  5.2.  templated

    The "templated" property is OPTIONAL.

    Its value is boolean and SHOULD be true when the Link Object's "href"
    property is a URI Template.

    Its value SHOULD be considered false if it is undefined or any other
    value than true.*/

    private boolean templated;

/*  5.3.  type

    The "type" property is OPTIONAL.

    Its value is a string used as a hint to indicate the media type
    expected when dereferencing the target resource.*/

    private String type;

 /* 5.4.  deprecation

    The "deprecation" property is OPTIONAL.

    Its presence indicates that the link is to be deprecated (i.e.
    removed) at a future date.  Its value is a URL that SHOULD provide
    further information about the deprecation.

    A client SHOULD provide some notification (for example, by logging a
    warning message) whenever it traverses over a link that has this
    property.  The notification SHOULD include the deprecation property's
    value so that a client manitainer can easily find information about
    the deprecation.*/

    private String deprecation;

/*  5.5.  name

    The "name" property is OPTIONAL.

    Its value MAY be used as a secondary key for selecting Link Objects
    which share the same relation type.*/

    @JsonIgnore
    private String name;

/*  5.6.  profile

    The "profile" property is OPTIONAL.

    Its value is a string which is a URI that hints about the profile (as
    defined by [I-D.wilde-profile-link]) of the target resource.*/

    private String profile;

/*  5.7.  title

    The "title" property is OPTIONAL.

    Its value is a string and is intended for labelling the link with a
    human-readable identifier (as defined by [RFC5988]).*/

    private String title;

/*  5.8.  hreflang

    The "hreflang" property is OPTIONAL.

    Its value is a string and is intended for indicating the language of
    the target resource (as defined by [RFC5988]).*/

    private String hreflang;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isTemplated() {
        return templated;
    }

    public void setTemplated(boolean templated) {
        this.templated = templated;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDeprecation() {
        return deprecation;
    }

    public void setDeprecation(String deprecation) {
        this.deprecation = deprecation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHreflang() {
        return hreflang;
    }

    public void setHreflang(String hreflang) {
        this.hreflang = hreflang;
    }
}