package org.interview.util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class ParserImpl implements IParser {
    private Gson parser;
    
    public ParserImpl(final Gson gson) {
        this.parser = gson;
    }
    
    @Override
    public <T> T parse(JsonReader reader, Class<T> clz) throws ParsingException {
        try {
            return parser.fromJson(reader, clz);
        } catch (Exception e) {
           throw new ParsingException("Unable to parse json");
        }
    }
}