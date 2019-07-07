package com.finvendor.api.fvreport.dto.marketdatacontent;

import com.finvendor.common.infra.pdf.PDFContent;

import java.util.List;

public abstract class MarketIndexData extends PDFContent {
    protected String userName;
    protected String currentDate;
    protected String nifty50Index;
    protected String consecutiveNumber;
    protected String upDown;
    protected String closeBy;
    protected String closeAt;
    protected String indexOpen;
    protected String dayHigh;
    protected String dayLow;
    protected String gainer;
    protected String looser;
    protected String unchanged;
    protected List<TopGainers> topGainers;
    protected List<TopLoosers> topLoosers;

    public MarketIndexData(String userName, String currentDate, String nifty50Index, String consecutiveNumber, String upDown, String closeBy, String closeAt, String indexOpen, String dayHigh, String dayLow, String gainer, String looser,
            String unchanged, List<TopGainers> topGainers, List<TopLoosers> topLoosers) {
      this.userName=userName;
      this.currentDate=currentDate;
      this.nifty50Index=nifty50Index;
      this.consecutiveNumber=consecutiveNumber;
      this.upDown=upDown;
      this.closeBy=closeBy;
      this.closeAt=closeAt;
      this.indexOpen=indexOpen;
      this.dayHigh=dayHigh;
      this.dayLow=dayLow;
      this.gainer=gainer;
      this.looser=looser;
      this.unchanged=unchanged;
      this.topGainers=topGainers;
      this.topLoosers=topLoosers;
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

    public String getNifty50Index() {
        return nifty50Index;
    }

    public void setNifty50Index(String nifty50Index) {
        this.nifty50Index = nifty50Index;
    }

    public String getConsecutiveNumber() {
        return consecutiveNumber;
    }

    public void setConsecutiveNumber(String consecutiveNumber) {
        this.consecutiveNumber = consecutiveNumber;
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
}
