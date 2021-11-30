package org.interview;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.interview.deserializer.AuthorDeserializer;
import org.interview.deserializer.DateDeserializer;
import org.interview.deserializer.MessageDeserializer;
import org.interview.domain.Author;
import org.interview.domain.Message;
import org.interview.oauth.twitter.TwitterAuthenticationException;
import org.interview.oauth.twitter.TwitterAuthenticator;
import org.interview.service.ITwitterService;
import org.interview.service.TwitterProcessMessagesServiceImpl;
import org.interview.service.TwitterReadStreamServiceImpl;
import org.interview.util.Logger;
import org.interview.util.LoggerFactory;
import org.interview.util.ParserImpl;

import com.google.api.client.http.HttpRequestFactory;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Application {

    private static Logger logger = LoggerFactory.getLogger();
    
    private static Gson gson;
    private static HttpRequestFactory factory; 
    
    
    @SuppressWarnings("serial")
    public static void main(String[] args) {
        try {
            factory = getAuthenticatedRequestFactory();
            logger.info("Started reading messages.");
            Map<String, List<Message>> messages = processMessages(readStream());

            logger.info("Printing captured messages...");
            Type type = new TypeToken<List<Message>>() {}.getType();
            for(Entry<String, List<Message>> athm : messages.entrySet()) {
                logger.info(gson.toJson(athm.getValue(), type));
            }
            logger.info("finished executing");
        } catch (Exception e) {
            logger.error(String.format("Unable to fetch data, aborting...\n %s", e));
        }
    }
    
    private static HttpRequestFactory getAuthenticatedRequestFactory() throws TwitterAuthenticationException {
        if(factory != null) {
            return factory;
        }
        TwitterAuthenticator authenticator = new TwitterAuthenticator(System.out, "RLSrphihyR4G2UxvA0XBkLAdl", "FTz2KcP1y3pcLw0XXMX5Jy3GTobqUweITIFy4QefullmpPnKm4");
        return authenticator.getAuthorizedHttpRequestFactory();
    }
    
    private static List<Message> readStream() throws Exception {
        ITwitterService service = new TwitterReadStreamServiceImpl(factory, new ParserImpl(gson));
        return service.readStream();
    }
    
    private static Map<String, List<Message>> processMessages(List<Message> messages) throws Exception {
        ITwitterService service = new TwitterProcessMessagesServiceImpl();
        return service.processMessages(messages);
    }
    
    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Message.class, new MessageDeserializer());
        gsonBuilder.registerTypeAdapter(Date.class, new DateDeserializer());
        gsonBuilder.registerTypeAdapter(Author.class, new AuthorDeserializer());
        gson = gsonBuilder.create();
    }
}
