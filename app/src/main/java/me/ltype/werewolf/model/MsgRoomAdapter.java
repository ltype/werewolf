package me.ltype.werewolf.model;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MsgRoomAdapter implements JsonDeserializer<List<Room>> {
    @Override
    public List<Room> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Room> vals = new ArrayList<>();
        if (json.isJsonArray()) {
            for (JsonElement e : json.getAsJsonArray()) {
                vals.add(context.deserialize(e, Room.class));
            }
        } else if (json.isJsonObject()) {
            vals.add(context.deserialize(json, Room.class));
        } else {
            throw new RuntimeException("Unexpected JSON type: " + json.getClass());
        }
        return vals;
    }
}
