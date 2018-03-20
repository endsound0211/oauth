package com.endsound.pagination.adapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.Instant;

public class TimestampAdapter implements JsonDeserializer<Timestamp>, JsonSerializer<Timestamp> {
    @Override
    public Timestamp deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return Timestamp.from(Instant.ofEpochMilli(json.getAsLong()));
    }

    @Override
    public JsonElement serialize(Timestamp src, Type typeOfSrc, JsonSerializationContext context) {
        return context.serialize(src.getTime());
    }
}
