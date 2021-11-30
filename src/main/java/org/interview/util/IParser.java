package org.interview.util;

import com.google.gson.stream.JsonReader;

public interface IParser {
    public abstract <T> T parse(JsonReader reader, Class<T> clz) throws ParsingException;
}
