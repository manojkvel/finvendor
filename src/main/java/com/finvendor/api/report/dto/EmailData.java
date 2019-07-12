package com.finvendor.api.report.dto;

import java.io.Serializable;

public class EmailData implements Serializable {
    private String from;
    private String to;
    private String content;
    private String attachment;
}
