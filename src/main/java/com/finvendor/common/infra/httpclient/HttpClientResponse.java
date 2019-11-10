package com.finvendor.common.infra.httpclient;

public class HttpClientResponse {
    /**
     * uri = url + urn
     */
    private int statusCode;
    private String reasonPhrase;
    private String jsonResult;

    public static class Builder {
        private int statusCode;
        private String reasonPhrase;
        private String jsonResult;

        public Builder(int statusCode, String reasonPhrase) {
            this.statusCode = statusCode;
            this.reasonPhrase = reasonPhrase;
        }

        public HttpClientResponse.Builder jsonResult(String jsonResult) {
            this.jsonResult = jsonResult;
            return this;
        }

        public HttpClientResponse build() {
            return new HttpClientResponse(this);
        }
    }

    private HttpClientResponse(HttpClientResponse.Builder builder) {
        this.statusCode = builder.statusCode;
        this.reasonPhrase = builder.reasonPhrase;
        this.jsonResult = builder.jsonResult;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public String getJsonResult() {
        return jsonResult;
    }

    public void setJsonResult(String jsonResult) {
        this.jsonResult = jsonResult;
    }
}
