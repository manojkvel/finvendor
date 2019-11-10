package com.finvendor.api.report.dto.marketdatacontent;

import com.finvendor.common.infra.pdf.PDFContent;

import java.util.List;

public abstract class MarketIndexData extends PDFContent {
    protected String userName;
    protected String currentDate;
    protected String nifty50MappingKeyword;
    protected String consecutiveMsg;
    protected String upDown;
    protected String closeBy;
    protected String closeAt;
    protected String percentChangeStr;
    protected String indexOpen;
    protected String dayHigh;
    protected String dayLow;
    protected String gainer;
    protected String looser;
    protected String unchanged;
    protected List<TopGainers> topGainers;
    protected List<TopLoosers> topLoosers;
    private String peStr;

    public MarketIndexData(String userName, String currentDate, String nifty50MappingKeyword, String consecutiveMsg, String upDown,
            String closeBy, String closeAt,
            String percentChangeStr, String indexOpen, String dayHigh, String dayLow, String gainer, String looser,
            String unchanged, List<TopGainers> topGainers, List<TopLoosers> topLosers, String peStr) {
        this.userName = userName;
        this.currentDate = currentDate;
        this.nifty50MappingKeyword = nifty50MappingKeyword;
        this.consecutiveMsg = consecutiveMsg;
        this.upDown = upDown;
        this.closeBy = closeBy;
        this.closeAt = closeAt;
        this.percentChangeStr = percentChangeStr;
        this.indexOpen = indexOpen;
        this.dayHigh = dayHigh;
        this.dayLow = dayLow;
        this.gainer = gainer;
        this.looser = looser;
        this.unchanged = unchanged;
        this.topGainers = topGainers;
        this.topLoosers = topLosers;
        this.peStr = peStr;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public String getNifty50MappingKeyword() {
        return nifty50MappingKeyword;
    }

    public void setNifty50MappingKeyword(String nifty50MappingKeyword) {
        this.nifty50MappingKeyword = nifty50MappingKeyword;
    }

    public String getConsecutiveMsg() {
        return consecutiveMsg;
    }

    public void setConsecutiveMsg(String consecutiveMsg) {
        this.consecutiveMsg = consecutiveMsg;
    }

    public String getUpDown() {
        return upDown;
    }

    public void setUpDown(String upDown) {
        this.upDown = upDown;
    }

    public String getCloseBy() {
        return closeBy;
    }

    public void setCloseBy(String closeBy) {
        this.closeBy = closeBy;
    }

    public String getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(String closeAt) {
        this.closeAt = closeAt;
    }

    public String getIndexOpen() {
        return indexOpen;
    }

    public void setIndexOpen(String indexOpen) {
        this.indexOpen = indexOpen;
    }

    public String getDayHigh() {
        return dayHigh;
    }

    public void setDayHigh(String dayHigh) {
        this.dayHigh = dayHigh;
    }

    public String getDayLow() {
        return dayLow;
    }

    public void setDayLow(String dayLow) {
        this.dayLow = dayLow;
    }

    public String getGainer() {
        return gainer;
    }

    public void setGainer(String gainer) {
        this.gainer = gainer;
    }

    public String getLooser() {
        return looser;
    }

    public void setLooser(String looser) {
        this.looser = looser;
    }

    public String getUnchanged() {
        return unchanged;
    }

    public void setUnchanged(String unchanged) {
        this.unchanged = unchanged;
    }

    public List<TopGainers> getTopGainers() {
        return topGainers;
    }

    public void setTopGainers(List<TopGainers> topGainers) {
        this.topGainers = topGainers;
    }

    public List<TopLoosers> getTopLoosers() {
        return topLoosers;
    }

    public void setTopLoosers(List<TopLoosers> topLoosers) {
        this.topLoosers = topLoosers;
    }

    public String getPercentChangeStr() {
        return percentChangeStr;
    }

    public void setPercentChangeStr(String percentChangeStr) {
        this.percentChangeStr = percentChangeStr;
    }

    public String getPeStr() {
        return peStr;
    }

    public void setPeStr(String peStr) {
        this.peStr = peStr;
    }
}
