package com.finvendor.common.infra.httpclient;

import org.apache.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

public class HttpClientRequest {
    /**
     * uri = url + urn
     */
    private String uri;
    private String json;
    private Map<String, String> headerMap;
    private Map<String, String> requestParamMap;

    public static class Builder {

        private String uri;
        private String json;
        private Map<String, String> headerMap;
        private Map<String, String> requestParamMap;

        public Builder(String uri) {
            this.uri = uri;
            this.headerMap = new HashMap<>();
            headerMap.put(HttpHeaders.CONTENT_TYPE, "application/json");
        }

        public HttpClientRequest.Builder json(String json) {
            this.json = json;
            return this;
        }

        public HttpClientRequest.Builder headerMap(Map<String, String> headerMap) {
            this.headerMap = headerMap;
            return this;
        }

        public HttpClientRequest.Builder requestParamMap(Map<String, String> requestParamMap) {
            this.requestParamMap = requestParamMap;
            return this;
        }

        public HttpClientRequest build() {
            return new HttpClientRequest(this);
        }
    }

    private HttpClientRequest(HttpClientRequest.Builder builder) {
        this.uri = builder.uri;
        this.json = builder.json;
        this.headerMap = builder.headerMap;
        this.requestParamMap = builder.requestParamMap;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    public Map<String, String> getRequestParamMap() {
        return requestParamMap;
    }

    public void setRequestParamMap(Map<String, String> requestParamMap) {
        this.requestParamMap = requestParamMap;
    }
}
