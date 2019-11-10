package com.finvendor.common.infra.httpclient.service;

import com.finvendor.common.infra.httpclient.HttpClientRequest;
import com.finvendor.common.infra.httpclient.HttpClientResponse;
import com.finvendor.common.infra.httpclient.HttpRequestType;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class HttpClientService {
    public HttpClientResponse execute(HttpRequestType httpRequestType, HttpClientRequest httpClientRequest) throws IOException {
        HttpClientResponse httpClientResponse;
        switch (httpRequestType) {
        case GET:
            httpClientResponse = callGet(httpClientRequest);
            break;
        case POST:
            httpClientResponse = callPost(httpClientRequest);
        default:
            httpClientResponse = null;
        }
        return httpClientResponse;
    }

    private static HttpClientResponse callGet(HttpClientRequest httpClientRequest) throws IOException {
        HttpGet request = new HttpGet(httpClientRequest.getUri());
        Map<String, String> headerMap = httpClientRequest.getHeaderMap();
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

        int statusCode;
        String reasonPhrase;
        String jsonResult;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request)) {
            // Get HttpResponse Status
            statusCode = response.getStatusLine().getStatusCode();
            reasonPhrase = response.getStatusLine().getReasonPhrase();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                jsonResult = EntityUtils.toString(entity);
            }
            else {
                jsonResult = "n/a";
            }
            return new HttpClientResponse.Builder(statusCode, reasonPhrase).jsonResult(jsonResult).build();
        }
    }

    private static HttpClientResponse callPost(HttpClientRequest httpClientRequest) throws IOException {
        HttpGet request = new HttpGet(httpClientRequest.getUri());
        Map<String, String> headerMap = httpClientRequest.getHeaderMap();
        if (headerMap != null) {
            for (Map.Entry<String, String> entry : headerMap.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

        int statusCode;
        String reasonPhrase;
        String jsonResult;
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
                CloseableHttpResponse response = httpClient.execute(request)) {
            // Get HttpResponse Status
            statusCode = response.getStatusLine().getStatusCode();
            reasonPhrase = response.getStatusLine().getReasonPhrase();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                jsonResult = EntityUtils.toString(entity);
            }
            else {
                jsonResult = "n/a";
            }
            return new HttpClientResponse.Builder(statusCode, reasonPhrase).jsonResult(jsonResult).build();
        }
    }
}
