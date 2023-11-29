package com.board.adapter;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class GsonLocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

  @Override
  public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
    System.out.println(" ==== ==== ==== ==== ====");
    System.out.println("src : " + src);
    System.out.println("typeOfSrc : " + typeOfSrc);
    System.out.println("context : " + context);
    System.out.println("DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(src) : " + new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(src)));
    System.out.println(" ==== ==== ==== ==== ====");
    return new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(src));
  }

  @Override
  public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    
    System.out.println(" ***** ***** ***** ***** ***** ");
    System.out.println("json : " + json);
    System.out.println("typeOfT : " + typeOfT);
    System.out.println("context : " + context);
    System.out.println(" ***** ***** ***** ***** ***** ");
    
    return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
  }

}

