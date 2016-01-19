package net.atos.pamm.infrastructure.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import play.Logger;

import javax.inject.Singleton;
import java.io.IOException;
import java.io.StringWriter;

@Singleton
public class JSONHelper {
    private static final Logger.ALogger LOG = Logger.of(JSONHelper.class);


    public String getJSONForObject(Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final StringWriter sw = new StringWriter();
            mapper.writeValue(sw, obj);

            return sw.toString();
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final T obj = mapper.readValue(jsonString, clazz);

            return obj;
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
