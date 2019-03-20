package com.finvendor.api.resources.companyprofile.companyprofile.dto;

import java.util.List;

public class EarningPreview {

    private List<EarningPreviewResult> quarterly;
    private List<EarningPreviewResult> yearly;

    public EarningPreview() {
    }

    public EarningPreview(List<EarningPreviewResult> quarterly, List<EarningPreviewResult> yearly) {
        this.quarterly = quarterly;
        this.yearly = yearly;
    }

    public List<EarningPreviewResult> getQuarterly() {
        return quarterly;
    }

    public void setQuarterly(List<EarningPreviewResult> quarterly) {
        this.quarterly = quarterly;
    }

    public List<EarningPreviewResult> getYearly() {
        return yearly;
    }

    public void setYearly(List<EarningPreviewResult> yearly) {
        this.yearly = yearly;
    }
}
