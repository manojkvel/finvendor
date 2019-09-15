package com.finvendor.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "code", "message", "data"})
public class ApiResponse<M,D> implements Serializable {
    protected String code;
    //E could be String or Array Of String
    protected M message;
    protected D data;
    private HttpStatus httpStatus;

    public ApiResponse() {

    }

    public ApiResponse(String code, M message, D data, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.httpStatus=httpStatus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public M getMessage() {
        return message;
    }

    public void setMessage(M message) {
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
