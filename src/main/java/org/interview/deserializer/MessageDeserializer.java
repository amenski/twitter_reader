package org.interview.deserializer;

import java.lang.reflect.Type;
import java.util.Date;

import org.interview.domain.Author;
import org.interview.domain.Message;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class MessageDeserializer implements JsonDeserializer<Message> {

    @Override
    public Message deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject(); 
        Message msg = new Message();
        msg.setId(jsonObject.get("id_str").getAsString());
        msg.setText(jsonObject.get("text").getAsString());
        if(jsonObject.has("created_at")) {
            msg.setCreatedAt(context.deserialize(jsonObject, Date.class));
        }
        
        if(jsonObject.has("user")) {
            msg.setAuthor(context.deserialize(jsonObject.getAsJsonObject("user"), Author.class));
        }
        
        return msg;
    }

}
