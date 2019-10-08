package com.finvendor.api.metrics.dto;

import com.finvendor.api.metrics.enums.FeatureAccessEnum;

import java.io.Serializable;

public class FeatureAllowedDto implements Serializable {
    private FeatureAccessEnum featureAccess;
    private String message;

    public FeatureAccessEnum getFeatureAccess() {
        return featureAccess;
    }

    public void setFeatureAccess(FeatureAccessEnum featureAccess) {
        this.featureAccess = featureAccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
