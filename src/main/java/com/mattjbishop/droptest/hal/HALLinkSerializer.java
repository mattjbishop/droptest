package com.mattjbishop.droptest.hal;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by matt on 08/08/2014.
 */
public class HALLinkSerializer
        extends JsonSerializer<Map<String, List<Link>>> {

    private static final String CURIES = "curies";
    private static final String EMBEDDED = "_embedded";
    private static final String LINKS = "_links";

    final static Logger logger = LoggerFactory.getLogger(HALEmbeddedSerializer.class);

    public HALLinkSerializer() {
        super();
    }

    @Override
    public void serialize(Map<String, List<Link>> links, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        logger.info("Serializing links...");

        logger.info("found {} links", links.size());

        // write curies

        // write out links

        jgen.writeStartObject();

        writeOutLinks(links, jgen);

        jgen.writeEndObject();
    }






    private void writeOutLinks(Map<String, List<Link>> links, JsonGenerator jgen)
            throws IOException, JsonProcessingException
    {
        for (Map.Entry<String, List<Link>> entry : links.entrySet())
        {
            if (entry.getValue().size() == 1) // && !resource.isMultipleLinks(entry.getKey())) // Write single link
            {
                Link link = entry.getValue().iterator().next();

//                if (null == link.getTemplated())
//                {
//                    link.setTemplated(link.hasTemplate() ? true : null);
//                }

                jgen.writeObjectField(entry.getKey(), link);
            }
            else // Write link array
            {
                jgen.writeArrayFieldStart(entry.getKey());

                for (Link link : entry.getValue())
                {
//                    if (null == link.getTemplated())
//                    {
//                        link.setTemplated(link.hasTemplate() ? true : null);
//                    }

                    jgen.writeObject(link);
                }

                jgen.writeEndArray();
            }

        }

    }

}
