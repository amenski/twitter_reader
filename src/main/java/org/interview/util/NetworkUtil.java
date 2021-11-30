package org.interview.util;

import java.io.IOException;
import java.util.Map;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;

public class NetworkUtil {

    public static HttpResponse makeGetRequest(HttpRequestFactory factory, String url, Map<String, String> parameters) throws IOException {
        GenericUrl genericUrl = new GenericUrl(url);
        if (parameters != null && !parameters.isEmpty()) {
            genericUrl.putAll(parameters);
        }

        HttpRequest getRequest = factory.buildGetRequest(genericUrl);
        getRequest.getHeaders().setContentType("application/json");
        HttpResponse response = getRequest.execute();
        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            throw new IOException("Bad status code: " + statusCode + " error: " + response.getStatusMessage());
        }

        return response;
    }
}