package org.interview.deserializer;

import java.lang.reflect.Type;
import java.util.Date;

import org.interview.domain.Author;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class AuthorDeserializer implements JsonDeserializer<Author> {

    @Override
    public Author deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Author author = new Author();
        author.setId(jsonObject.get("id_str").getAsString());
        author.setName(jsonObject.get("name").getAsString());
        author.setScreenName(jsonObject.get("screen_name").getAsString());
        if(jsonObject.has("created_at")) {
             author.setCreatedAt(context.deserialize(jsonObject, Date.class));
        }
        
        return author;
    }

}
