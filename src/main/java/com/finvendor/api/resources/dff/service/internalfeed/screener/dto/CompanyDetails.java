package com.finvendor.api.resources.dff.service.internalfeed.screener.dto;

public class CompanyDetails {
    private final String companyId;
    private final String companyName;
    private final String mcap;
    private final String sector;
    private final String cmp;
    private final float cmpFloat;
    private final String ltp;
    private final float ltpFloat;
    private final String pe;
    private final float peFloat;
    private final String pb;
    private final float pbFloat;
    private final String divYeild;
    private final float divYeildFloat;
    private final String epsTtm;
    private final float epsTtmFloat;
    private final String wLow;
    private final float wLowFloat;
    private final String wHigh;
    private final float wHighFloat;
    private final String beta;
    private final float betaFloat;
    private final String asOfDate;
    private final String shareOutStanding;
    private final float shareOutStandingFloat;
    private final String closePrice;
    private final float closePriceFloat;
    private final String mktCap;
    private final String revenue;
    private final float revenueFloat;
    private final String faceValue;
    private final float faceValueFloat;
    private final String bvShare;
    private float bvShareFloat;
    private final String roe;
    private final float roeFloat;
    private final String pat;
    private final float patFloat;
    private final String recentQtr;
    private final String priceDate;
    private final String companyDesc;
    private final String yrEpsGrowth;
    private final float yrEpsGrowthFloat;
    private final String currency;

    public CompanyDetails(String companyId, String companyName, String mcap, String sector, String cmp, float cmpFloat, String ltp,
            float ltpFloat, String pe, float peFloat, String pb, float pbFloat, String divYeild, float divYeildFloat, String epsTtm,
            float epsTtmFloat, String wLow, float wLowFloat, String wHigh, float wHighFloat, String beta, float betaFloat, String asOfDate,
            String shareOutStanding, float shareOutStandingFloat, String closePrice, float closePriceFloat, String mktCap, String revenue,
            float revenueFloat, String faceValue, float faceValueFloat, String bvShare, float bvShareFloat, String roe, float roeFloat,
            String pat,
            float patFloat, String recentQtr, String priceDate, String companyDesc, String yrEpsGrowth, float yrEpsGrowthFloat,
            String currency) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.mcap = mcap;
        this.sector = sector;
        this.cmp = cmp;
        this.cmpFloat = cmpFloat;
        this.ltp = ltp;
        this.ltpFloat = ltpFloat;
        this.pe = pe;
        this.peFloat = peFloat;
        this.pb = pb;
        this.pbFloat = pbFloat;
        this.divYeild = divYeild;
        this.divYeildFloat = divYeildFloat;
        this.epsTtm = epsTtm;
        this.epsTtmFloat = epsTtmFloat;
        this.wLow = wLow;
        this.wLowFloat = wLowFloat;
        this.wHigh = wHigh;
        this.wHighFloat = wHighFloat;
        this.beta = beta;
        this.betaFloat = betaFloat;
        this.asOfDate = asOfDate;
        this.shareOutStanding = shareOutStanding;
        this.shareOutStandingFloat = shareOutStandingFloat;
        this.closePrice = closePrice;
        this.closePriceFloat = closePriceFloat;
        this.mktCap = mktCap;
        this.revenue = revenue;
        this.revenueFloat = revenueFloat;
        this.faceValue = faceValue;
        this.faceValueFloat = faceValueFloat;
        this.bvShare = bvShare;
        this.bvShareFloat = bvShareFloat;
        this.roe = roe;
        this.roeFloat = roeFloat;
        this.pat = pat;
        this.patFloat = patFloat;
        this.recentQtr = recentQtr;
        this.priceDate = priceDate;
        this.companyDesc = companyDesc;
        this.yrEpsGrowth = yrEpsGrowth;
        this.yrEpsGrowthFloat = yrEpsGrowthFloat;
        this.currency = currency;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getMcap() {
        return mcap;
    }

    public String getSector() {
        return sector;
    }

    public String getCmp() {
        return cmp;
    }

    public float getCmpFloat() {
        return cmpFloat;
    }

    public String getLtp() {
        return ltp;
    }

    public float getLtpFloat() {
        return ltpFloat;
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

    public String getEpsTtm() {
        return epsTtm;
    }

    public float getEpsTtmFloat() {
        return epsTtmFloat;
    }

    public String getwLow() {
        return wLow;
    }

    public float getwLowFloat() {
        return wLowFloat;
    }

    public String getwHigh() {
        return wHigh;
    }

    public float getwHighFloat() {
        return wHighFloat;
    }

    public String getBeta() {
        return beta;
    }

    public float getBetaFloat() {
        return betaFloat;
    }

    public String getAsOfDate() {
        return asOfDate;
    }

    public String getShareOutStanding() {
        return shareOutStanding;
    }

    public float getShareOutStandingFloat() {
        return shareOutStandingFloat;
    }

    public String getClosePrice() {
        return closePrice;
    }

    public float getClosePriceFloat() {
        return closePriceFloat;
    }

    public String getMktCap() {
        return mktCap;
    }

    public String getRevenue() {
        return revenue;
    }

    public float getRevenueFloat() {
        return revenueFloat;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public float getFaceValueFloat() {
        return faceValueFloat;
    }

    public String getBvShare() {
        return bvShare;
    }

    public String getRoe() {
        return roe;
    }

    public float getRoeFloat() {
        return roeFloat;
    }

    public String getPat() {
        return pat;
    }

    public float getPatFloat() {
        return patFloat;
    }

    public String getRecentQtr() {
        return recentQtr;
    }

    public String getPriceDate() {
        return priceDate;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public String getYrEpsGrowth() {
        return yrEpsGrowth;
    }

    public float getYrEpsGrowthFloat() {
        return yrEpsGrowthFloat;
    }

    public String getCurrency() {
        return currency;
    }

    public float getBvShareFloat() {
        return bvShareFloat;
    }

    public void setBvShareFloat(float bvShareFloat) {
        this.bvShareFloat = bvShareFloat;
    }
}
