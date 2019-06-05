package com.finvendor.api.companyprofile.companyprofile.dto;

/**
 * @author ayush on May 01, 2018
 */
public class CompanyProfileDataDto {
    private String companyId;
    private String companyName;
    private String industry;
    private String mcap;
    private String cmp;
    private String absoluteLastChangedCmp;
    private String lastChangedCmpInPercentage;
    private String pe;
    private String pb;
    private String dividen_yield;
    private String eps_ttm;
    private String _52w_high;
    private String _52w_low;
    private String beta;
    private String share_outstanding;
    private String mkt_cap;
    private String revenue;
    private String face_value;
    private String bv_share;
    private String roe;
    private String pat;
    private String recent_qtr;
    private String price_date;
    private String price_src_code;
    private String valuationScore;
    private String currency;
    private String ticker;

    public CompanyProfileDataDto(String companyId, String companyName, String industry, String mcap, String cmp,
            String absoluteLastChangedCmp, String lastChangedCmpInPercentage, String pe, String pb,
            String dividen_yield, String eps_ttm, String _52w_high, String _52w_low, String beta,
            String share_outstanding, String mkt_cap, String revenue, String face_value, String bv_share, String roe,
            String pat, String recent_qtr, String price_date, String price_src_code,
            String valuationScore, String currency, String ticker) {
        super();
        this.companyId = companyId;
        this.companyName = companyName;
        this.industry = industry;
        this.mcap = mcap;
        this.cmp = cmp;
        this.absoluteLastChangedCmp = absoluteLastChangedCmp;
        this.lastChangedCmpInPercentage = lastChangedCmpInPercentage;
        this.pe = pe;
        this.pb = pb;
        this.dividen_yield = dividen_yield;
        this.eps_ttm = eps_ttm;
        this._52w_high = _52w_high;
        this._52w_low = _52w_low;
        this.beta = beta;
        this.share_outstanding = share_outstanding;
        this.mkt_cap = mkt_cap;
        this.revenue = revenue;
        this.face_value = face_value;
        this.bv_share = bv_share;
        this.roe = roe;
        this.pat = pat;
        this.recent_qtr = recent_qtr;
        this.price_date = price_date;
        this.price_src_code = price_src_code;
        this.valuationScore = valuationScore;
        this.currency = currency;
        this.ticker = ticker;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMcap() {
        return mcap;
    }

    public void setMcap(String mcap) {
        this.mcap = mcap;
    }

    public String getCmp() {
        return cmp;
    }

    public void setCmp(String cmp) {
        this.cmp = cmp;
    }

    public String getAbsoluteLastChangedCmp() {
        return absoluteLastChangedCmp;
    }

    public void setAbsoluteLastChangedCmp(String absoluteLastChangedCmp) {
        this.absoluteLastChangedCmp = absoluteLastChangedCmp;
    }

    public String getLastChangedCmpInPercentage() {
        return lastChangedCmpInPercentage;
    }

    public void setLastChangedCmpInPercentage(String lastChangedCmpInPercentage) {
        this.lastChangedCmpInPercentage = lastChangedCmpInPercentage;
    }

    public String getPe() {
        return pe;
    }

    public void setPe(String pe) {
        this.pe = pe;
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }

    public String getDividen_yield() {
        return dividen_yield;
    }

    public void setDividen_yield(String dividen_yield) {
        this.dividen_yield = dividen_yield;
    }

    public String getEps_ttm() {
        return eps_ttm;
    }

    public void setEps_ttm(String eps_ttm) {
        this.eps_ttm = eps_ttm;
    }

    public String get_52w_high() {
        return _52w_high;
    }

    public void set_52w_high(String _52w_high) {
        this._52w_high = _52w_high;
    }

    public String get_52w_low() {
        return _52w_low;
    }

    public void set_52w_low(String _52w_low) {
        this._52w_low = _52w_low;
    }

    public String getBeta() {
        return beta;
    }

    public void setBeta(String beta) {
        this.beta = beta;
    }

    public String getShare_outstanding() {
        return share_outstanding;
    }

    public void setShare_outstanding(String share_outstanding) {
        this.share_outstanding = share_outstanding;
    }

    public String getMkt_cap() {
        return mkt_cap;
    }

    public void setMkt_cap(String mkt_cap) {
        this.mkt_cap = mkt_cap;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getFace_value() {
        return face_value;
    }

    public void setFace_value(String face_value) {
        this.face_value = face_value;
    }

    public String getBv_share() {
        return bv_share;
    }

    public void setBv_share(String bv_share) {
        this.bv_share = bv_share;
    }

    public String getRoe() {
        return roe;
    }

    public void setRoe(String roe) {
        this.roe = roe;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }

    public String getRecent_qtr() {
        return recent_qtr;
    }

    public void setRecent_qtr(String recent_qtr) {
        this.recent_qtr = recent_qtr;
    }

    public String getPrice_date() {
        return price_date;
    }

    public void setPrice_date(String price_date) {
        this.price_date = price_date;
    }

    public String getPrice_src_code() {
        return price_src_code;
    }

    public void setPrice_src_code(String price_src_code) {
        this.price_src_code = price_src_code;
    }

    public String getValuationScore() {
        return valuationScore;
    }

    public void setValuationScore(String valuationScore) {
        this.valuationScore = valuationScore;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
}
