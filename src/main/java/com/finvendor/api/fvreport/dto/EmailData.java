package com.finvendor.api.fvreport.dto;

import java.io.Serializable;

public class EmailData implements Serializable {
    private String from;
    private String to;
    private String content;
    private String attachment;
}
