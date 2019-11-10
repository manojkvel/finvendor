package com.finvendor.api.notification.dto;

import java.io.Serializable;

public class EmailDataDto implements Serializable {

    private String subject;
    private String content;

    public EmailDataDto() {
        super();
    }

    public EmailDataDto(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
