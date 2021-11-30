package org.interview.service;

import java.util.List;
import java.util.Map;

import org.interview.domain.Message;

public interface ITwitterService {

    default List<Message> readStream() throws Exception {
        throw new UnsupportedOperationException("Not implemented");
    };
    
    default Map<String, List<Message>> processMessages(List<Message> messages) throws Exception {
        throw new UnsupportedOperationException("Not implemented");
    };
}
