package org.interview.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.interview.domain.Message;
import org.interview.util.Logger;
import org.interview.util.LoggerFactory;

public class TwitterProcessMessagesServiceImpl implements ITwitterService {

    private final Logger logger = LoggerFactory.getLogger();

    @Override
    @SuppressWarnings("serial")
    public Map<String, List<Message>> processMessages(List<Message> messages) throws Exception {
        Map<String, List<Message>> tree = new TreeMap<String, List<Message>>((v1, v2) -> v1.compareTo(v2));
        for (Message msg : messages) {
            if (tree.containsKey(msg.getAuthor().getScreenName())) {
                tree.get(msg.getAuthor().getScreenName()).add(msg);
                continue;
            }

            // messages per user should be sorted chronologically, ascending
            List<Message> list = new ArrayList<Message>() {
                @Override
                public boolean add(Message e) {
                    int index = Collections.binarySearch(this, e);
                    if (index < 0) index = ~index;
                    super.add(index, e);
                    return true;
                }
            };
            list.add(msg);
            tree.put(msg.getAuthor().getScreenName(), list);
        }

        return tree;
    }

}
