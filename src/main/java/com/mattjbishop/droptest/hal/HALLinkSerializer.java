package com.mattjbishop.droptest.hal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by matt on 08/08/2014.
 */
public class HALLinkSerializer
        extends JsonSerializer<List<Link>> {

    final static Logger logger = LoggerFactory.getLogger(HALEmbeddedSerializer.class);

    public HALLinkSerializer() {
        super();
    }

    @Override
    public void serialize(List<Link> links, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        logger.info("Serializing links...");

        jgen.writeStartObject();
        jgen.writeObjectField("self", "a");
        jgen.writeObjectField("next", "b");
        jgen.writeObjectField("last", "c");
        // go through the array and serialise the links
        jgen.writeEndObject();
    }

}
