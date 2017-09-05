
package net.jr.geoip;

import java.io.IOException;
import java.io.StringWriter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSON {

  private static final ObjectMapper OBJECT_MAPPER;

  private static final JsonFactory JSON_FACTORY;

  static {
    OBJECT_MAPPER = new ObjectMapper();
    OBJECT_MAPPER.enable(SerializationFeature.INDENT_OUTPUT);
    JSON_FACTORY = new JsonFactory(OBJECT_MAPPER);
  }

  public static String stringify(Object obj) {
    StringWriter sw = new StringWriter();
    try {
      JSON_FACTORY.createGenerator(sw).writeObject(obj);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return sw.toString();
  }
}
