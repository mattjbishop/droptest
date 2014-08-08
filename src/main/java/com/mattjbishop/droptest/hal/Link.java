package com.mattjbishop.droptest.hal;

import java.util.regex.Pattern;

public class Link {

    // The attributes from 'LinkDefinition' that a HAL link cares about.
    public static final String TYPE = "type";
    public static final String TITLE = "title";
    public static final String TEMPLATED = "templated";
    public static final String PROFILE = "profile";
    public static final String NAME = "name";
    public static final String HREFLANG = "hreflang";
    public static final String HREF = "href";
    public static final String DEPRECATION = "deprecation";

    // Regular expression for the hasTemplate() method.
    // private static final String TEMPLATE_REGEX = "\\{(\\w*?)\\}";
    // private static final Pattern TEMPLATE_PATTERN = Pattern.compile(TEMPLATE_REGEX);

    /**
     * The "href" property is REQUIRED.
     * <p/>
     * Its value is either a URI [RFC3986] or a URI Template [RFC6570].
     * <p/>
     * If the value is a URI Template then the Link Object SHOULD have a
     * "templated" attribute whose value is true.
     */
   // @Required
    private String href;

    /**
     * The "name" property is OPTIONAL.
     * <p/>
     * Its value MAY be used as a secondary key for selecting Link Objects which
     * share the same relation type.
     * <p/>
     * For distinguishing between Resource and Link elements that share the same
     * rel value. The name attribute SHOULD NOT be used exclusively for
     * identifying elements within a HAL representation, it is intended only as
     * a 'secondary key' to a given rel value.
     */
    private String name;

    /**
     * The "hreflang" property is OPTIONAL.
     * <p/>
     * Its value is a string and is intended for indicating the language of the
     * target resource (as defined by [RFC5988]).
     * <p/>
     * The "hreflang" attribute's content describes the language of the resource
     * pointed to by the href attribute. When used together with the
     * rel="alternate", it implies a translated version of the entry. Link
     * elements MAY have an hreflang attribute, whose value MUST be a language
     * tag [RFC3066].
     */
    //@RegexValidation(pattern = "[A-Za-z]{1,8}(-[A-Za-z0-9]{1,8})*", nullable = true, message = "MUST be a language tag [RFC3066]")
    private String hreflang;

    /**
     * The "title" property is OPTIONAL.
     * <p/>
     * Its value is a string and is intended for labelling the link with a
     * human-readable identifier (as defined by [RFC5988]).
     * <p/>
     * The "title" attribute conveys human-readable information about the
     * link.  The content of the "title" attribute is Language-Sensitive.
     * Entities such as "&amp;" and "&lt;" represent their corresponding
     * characters ("&" and "<", respectively), not markup.  Link elements
     * MAY have a title attribute.
     */
    private String title;

    /**
     * The "templated" property is OPTIONAL.
     * <p/>
     * Its value is boolean and SHOULD be true when the Link Object's "href"
     * property is a URI Template.
     * <p/>
     * Its value SHOULD be considered false if it is undefined or any other
     * value than true.
     */
    private Boolean templated;

    /**
     * The "type" property is OPTIONAL.
     * <p/>
     * Its value is a string used as a hint to indicate the media type expected
     * when dereferencing the target resource.
     * <p/>
     * On the link element, the "type" attribute's value is an advisory media
     * type: it is a hint about the type of the representation that is expected
     * to be returned when the value of the href attribute is dereferenced. Note
     * that the type attribute does not override the actual media type returned
     * with the representation. Link elements MAY have a type attribute, whose
     * value MUST conform to the syntax of a MIME media type [MIMEREG].
     */
    private String type;

    /**
     * The "deprecation" property is OPTIONAL.
     * <p/>
     * Its presence indicates that the link is to be deprecated (i.e. removed)
     * at a future date. Its value is a URL that SHOULD provide further
     * information about the deprecation.
     * <p/>
     * A client SHOULD provide some notification (for example, by logging a
     * warning message) whenever it traverses over a link that has this
     * property. The notification SHOULD include the deprecation property's
     * value so that a client maintainer can easily find information about the
     * deprecation.
     */
    private String deprecation;

    /**
     * The "profile" property is OPTIONAL.
     * <p/>
     * Its value is a string which is a URI that hints about the profile (as
     * defined by [I-D.wilde-profile-link]) of the target resource.
     */
    private String profile;

    public Link() {
        super();
    }

   /* public HalLink(Link that) {
        this();
        this.setHref(that.getHref());
        this.setDeprecation(that.get(DEPRECATION));
        this.setHreflang(that.get(HREFLANG));
        this.setName(that.get(NAME));
        this.setProfile(that.get(PROFILE));
        this.setTemplated(that.has(TEMPLATED) ? Boolean.valueOf(that.get(TEMPLATED)) : null);
        this.setTitle(that.get(TITLE));
        this.setType(that.get(TYPE));
    }*/

    public String getHref() {
        return href;
    }

    public Link setHref(String href) {
        this.href = href;
        return this;
    }

    public String getName() {
        return name;
    }

    public Link setName(String name) {
        this.name = name;
        return this;
    }

    public String getHreflang() {
        return hreflang;
    }

    public Link setHreflang(String hreflang) {
        this.hreflang = hreflang;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Link setTitle(String title) {
        this.title = title;
        return this;
    }

    public Boolean getTemplated() {
        return templated;
    }

   /* public boolean hasTemplate() {
        return TEMPLATE_PATTERN.matcher(getHref()).find();
    }*/

    public Link setTemplated(Boolean templated) {
        this.templated = templated;
        return this;
    }

    public String getType() {
        return type;
    }

    public Link setType(String type) {
        this.type = type;
        return this;
    }

    public String getDeprecation() {
        return deprecation;
    }

    public Link setDeprecation(String deprecation) {
        this.deprecation = deprecation;
        return this;
    }

    public String getProfile() {
        return profile;
    }

    public Link setProfile(String profile) {
        this.profile = profile;
        return this;
    }
}