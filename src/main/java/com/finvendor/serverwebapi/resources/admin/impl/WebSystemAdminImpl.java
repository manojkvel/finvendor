package com.finvendor.serverwebapi.resources.admin.impl;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import com.finvendor.serverwebapi.resources.admin.IWebSystemAdmin;

@Controller
public class WebSystemAdminImpl implements IWebSystemAdmin, Serializable {

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;

    @Override
    public String getPerPageMaxRecordCount() {
        final String maxCount = finvendorProperties.getProperty("per_page_max_record_count");
        return "{\"perpagemaxrecordcount\":\"" + maxCount + "\"}";
    }
}
