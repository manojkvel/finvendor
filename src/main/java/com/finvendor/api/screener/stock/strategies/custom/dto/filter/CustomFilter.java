package com.finvendor.api.screener.stock.strategies.custom.dto.filter;

import java.io.Serializable;
import java.util.List;

public class CustomFilter implements Serializable{
    private List<String> industry;
    private Mcap mcap;
    private Pe pe;
    private Pb pb;
    private DebtToEquityRatio debtToEquityRatio;
    private CurrentRatio currentRatio;
    private NetOperatingCashFlow netOperatingCashFlow;
    private RoeInPercentage roeInPercentage;
    private OperatingProfitMargin operatingProfitMargin;
    private PatGrowthInPercentage patGrowthInPercentage;
    private EpsGrowthInPercentage epsGrowthInPercentage;
    private RevenueGrowthInPercentage revenueGrowthInPercentage;
    private TotalFreeCashFlow totalFreeCashFlow;
    private ReturnOnAssetInPercentage returnOnAssetInPercentage;
    private DivYield divYield;
    private RotcInPercentage rotcInPercentage;

    public List<String> getIndustry() {
        return industry;
    }

    public void setIndustry(List<String> industry) {
        this.industry = industry;
    }

    public Mcap getMcap() {
        return mcap;
    }

    public void setMcap(Mcap mcap) {
        this.mcap = mcap;
    }

    public Pe getPe() {
        return pe;
    }

    public void setPe(Pe pe) {
        this.pe = pe;
    }

    public Pb getPb() {
        return pb;
    }

    public void setPb(Pb pb) {
        this.pb = pb;
    }

    public DebtToEquityRatio getDebtToEquityRatio() {
        return debtToEquityRatio;
    }

    public void setDebtToEquityRatio(DebtToEquityRatio debtToEquityRatio) {
        this.debtToEquityRatio = debtToEquityRatio;
    }

    public CurrentRatio getCurrentRatio() {
        return currentRatio;
    }

    public void setCurrentRatio(CurrentRatio currentRatio) {
        this.currentRatio = currentRatio;
    }

    public NetOperatingCashFlow getNetOperatingCashFlow() {
        return netOperatingCashFlow;
    }

    public void setNetOperatingCashFlow(NetOperatingCashFlow netOperatingCashFlow) {
        this.netOperatingCashFlow = netOperatingCashFlow;
    }

    public RoeInPercentage getRoeInPercentage() {
        return roeInPercentage;
    }

    public void setRoeInPercentage(RoeInPercentage roeInPercentage) {
        this.roeInPercentage = roeInPercentage;
    }

    public OperatingProfitMargin getOperatingProfitMargin() {
        return operatingProfitMargin;
    }

    public void setOperatingProfitMargin(OperatingProfitMargin operatingProfitMargin) {
        this.operatingProfitMargin = operatingProfitMargin;
    }

    public PatGrowthInPercentage getPatGrowthInPercentage() {
        return patGrowthInPercentage;
    }

    public void setPatGrowthInPercentage(PatGrowthInPercentage patGrowthInPercentage) {
        this.patGrowthInPercentage = patGrowthInPercentage;
    }

    public EpsGrowthInPercentage getEpsGrowthInPercentage() {
        return epsGrowthInPercentage;
    }

    public void setEpsGrowthInPercentage(EpsGrowthInPercentage epsGrowthInPercentage) {
        this.epsGrowthInPercentage = epsGrowthInPercentage;
    }

    public RevenueGrowthInPercentage getRevenueGrowthInPercentage() {
        return revenueGrowthInPercentage;
    }

    public void setRevenueGrowthInPercentage(
            RevenueGrowthInPercentage revenueGrowthInPercentage) {
        this.revenueGrowthInPercentage = revenueGrowthInPercentage;
    }

    public TotalFreeCashFlow getTotalFreeCashFlow() {
        return totalFreeCashFlow;
    }

    public void setTotalFreeCashFlow(TotalFreeCashFlow totalFreeCashFlow) {
        this.totalFreeCashFlow = totalFreeCashFlow;
    }

    public ReturnOnAssetInPercentage getReturnOnAssetInPercentage() {
        return returnOnAssetInPercentage;
    }

    public void setReturnOnAssetInPercentage(
            ReturnOnAssetInPercentage returnOnAssetInPercentage) {
        this.returnOnAssetInPercentage = returnOnAssetInPercentage;
    }

    public DivYield getDivYield() {
        return divYield;
    }

    public void setDivYield(DivYield divYield) {
        this.divYield = divYield;
    }

    public RotcInPercentage getRotcInPercentage() {
        return rotcInPercentage;
    }

    public void setRotcInPercentage(RotcInPercentage rotcInPercentage) {
        this.rotcInPercentage = rotcInPercentage;
    }
}
