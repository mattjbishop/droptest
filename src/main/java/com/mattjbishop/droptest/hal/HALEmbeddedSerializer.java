package com.mattjbishop.droptest.hal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
        writeOutResources(embeddedResources, jgen);
        jgen.writeEndObject();
    }

    private void writeOutResources(Map<String, List<HALRepresentation>> resources, JsonGenerator jgen)
            throws IOException, JsonProcessingException {
        for (Map.Entry<String, List<HALRepresentation>> entry : resources.entrySet()) {

            if (entry.getValue().size() == 1) // && !resource.isMultipleLinks(entry.getKey())) // Write single link
            {
                HALRepresentation resource = entry.getValue().iterator().next();
                jgen.writeObjectField(entry.getKey(), resource);
            }
            else // write resource collection
            {
                jgen.writeArrayFieldStart(entry.getKey());

                for (HALRepresentation resource : entry.getValue())
                {
                    jgen.writeObject(resource);
                }

                jgen.writeEndArray();
            }
        }
    }
}
