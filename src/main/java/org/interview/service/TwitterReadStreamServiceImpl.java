package org.interview.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.interview.domain.Message;
import org.interview.util.Constants;
import org.interview.util.IParser;
import org.interview.util.Logger;
import org.interview.util.LoggerFactory;
import org.interview.util.NetworkUtil;

import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.gson.stream.JsonReader;

public class TwitterReadStreamServiceImpl implements ITwitterService {

    private final Logger logger = LoggerFactory.getLogger();

    private final IParser parser;
    private final HttpRequestFactory requestFactory;
    private final ExecutorService executor =  Executors.newFixedThreadPool(2);
    
    public TwitterReadStreamServiceImpl(final HttpRequestFactory requestFactory, final IParser parser) {
        this.requestFactory = requestFactory;
        this.parser = parser;
    }
    
    @Override
    public List<Message> readStream() throws Exception {
        try {
            CountDownLatch latch = new CountDownLatch(1);
            List<Message> messages = new ArrayList<>();
            Map<String, String> parameters = new HashMap<>();
            parameters.put("track", "bieber");
            
            HttpResponse response = NetworkUtil.makeGetRequest(
                                                            requestFactory, 
                                                            Constants.STREAM_URL, 
                                                            parameters);
            
            PipedInputStream in = new PipedInputStream();
            final PipedOutputStream out = new PipedOutputStream(in);
            JsonReader reader =  new JsonReader(new InputStreamReader(in, "UTF-8"));
            reader.setLenient(true);
            
            executor.submit(() -> {
                try {
                    response.download(out);
                } catch (IOException e) {
                    //
                }
            });
            
            executor.submit(() -> {
                try {
                    while (reader.hasNext()) {
                        if (messages.size() == 100) {
                            logger.info("Bucket full, stopping stream read.");
                            latch.countDown();
                            break;
                        }
                        Message message = parser.parse(reader, Message.class);
                        messages.add(message);
                    }
                } catch (Exception e) {
                    //
                }
            });
            
            latch.await(3, TimeUnit.SECONDS);
            reader.close();
            response.disconnect();
            executor.shutdown();
            out.close();
            in.close();
            
            return messages;
        } catch (Exception e) {
            logger.error(String.format("Error in readStream(): %s", e));
            throw e;
        }
    }
}
