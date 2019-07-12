package com.finvendor.api.report.dto.marketdatacontent;

import java.io.Serializable;

public class AbstractTop implements Serializable {
    protected String companyName;
    protected String _percentChange;

    public AbstractTop(String companyName, String _percentChange) {
        this.companyName = companyName;
        this._percentChange = _percentChange;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String get_percentChange() {
        return _percentChange;
    }

    public void set_percentChange(String _percentChange) {
        this._percentChange = _percentChange;
    }
}
