package com.finvendor.api.dff.service.internalfeed.screener.dto;

public class CompanyDetails {
    private final String companyId;
    private final String companyName;
    private final String cmp;
    private String sector;
    private final float cmpFloat;
    private final String pe;
    private final float peFloat;
    private final String pb;
    private final float pbFloat;
    private final String divYeild;
    private final float divYeildFloat;
    private final String shareOutStanding;
    private final float shareOutStandingFloat;
    private final String pat;
    private final float patFloat;
    private String ticker;

    public CompanyDetails(String companyId, String companyName, String cmp, String sector, float cmpFloat, String pe, float peFloat,
            String pb, float pbFloat, String divYeild, float divYeildFloat, String shareOutStanding, float shareOutStandingFloat,
            String pat, float patFloat, String ticker) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.cmp = cmp;
        this.sector = sector;
        this.cmpFloat = cmpFloat;
        this.pe = pe;
        this.peFloat = peFloat;
        this.pb = pb;
        this.pbFloat = pbFloat;
        this.divYeild = divYeild;
        this.divYeildFloat = divYeildFloat;
        this.shareOutStanding = shareOutStanding;
        this.shareOutStandingFloat = shareOutStandingFloat;
        this.pat = pat;
        this.patFloat = patFloat;
        this.ticker = ticker;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCmp() {
        return cmp;
    }

    public float getCmpFloat() {
        return cmpFloat;
    }

    public String getPe() {
        return pe;
    }

    public float getPeFloat() {
        return peFloat;
    }

    public String getPb() {
        return pb;
    }

    public float getPbFloat() {
        return pbFloat;
    }

    public String getDivYeild() {
        return divYeild;
    }

    public float getDivYeildFloat() {
        return divYeildFloat;
    }

    public String getShareOutStanding() {
        return shareOutStanding;
    }

    public float getShareOutStandingFloat() {
        return shareOutStandingFloat;
    }

    public String getPat() {
        return pat;
    }

    public float getPatFloat() {
        return patFloat;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }
}
