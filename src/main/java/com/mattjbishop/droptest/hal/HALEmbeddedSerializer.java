package com.mattjbishop.droptest.hal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by matt on 07/08/2014.
 *
 * serializes embedded resources
 */
public class HALEmbeddedSerializer
       extends JsonSerializer<Map<String, List<HALRepresentation>>> {

    final static Logger logger = LoggerFactory.getLogger(HALEmbeddedSerializer.class);

    public HALEmbeddedSerializer() {
        super();
    }

    @Override
    public void serialize(Map<String, List<HALRepresentation>> embeddedResources, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        logger.info("Serializing embedded resource...");

        jgen.writeStartObject();
        jgen.writeObjectField("embedded", "bar");

        // cycle through

        jgen.writeEndObject();
    }

}
