package com.finvendor.api.notification.dto;

import com.finvendor.util.EmailUtil;

import java.util.Arrays;

public class EmailBuilder {
    private String from;
    private String[] to;
    private String subject;
    private String content;
    private String[] attachment;

    public static class Builder {

        private String[] to;
        private String subject;
        private String content;
        private String from;
        private String[] attachment;

        public Builder(String[] to,String subject,String content) {
            this.to = to;
            this.subject = subject;
            this.content= content;
            this.from = EmailUtil.SALES_EMAIL;
        }

        public Builder from(String from){
            this.from=from;
            return this;
        }

        public Builder attachment(String[] attachments) {
            this.attachment = attachments;
            return this;
        }

        public EmailBuilder build() {
            return new EmailBuilder(this);
        }
    }

    private EmailBuilder(Builder builder) {
        this.from = builder.from;
        this.to = builder.to;
        this.subject = builder.subject;
        this.content = builder.content;
        this.attachment = builder.attachment;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
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

    public String[] getAttachment() {
        return attachment;
    }

    public void setAttachment(String[] attachment) {
        this.attachment = attachment;
    }

    @Override public String toString() {
        return "EmailBuilder{" +
                "from='" + from + '\'' +
                ", to=" + Arrays.toString(to) +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", attachment=" + Arrays.toString(attachment) +
                '}';
    }
}
