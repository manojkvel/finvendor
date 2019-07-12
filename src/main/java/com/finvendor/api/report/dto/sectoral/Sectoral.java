package com.finvendor.api.report.dto.sectoral;

import com.finvendor.api.report.dto.marketdatacontent.MarketIndexData;
import com.finvendor.common.infra.pdf.PDFContent;

public class Sectoral extends PDFContent {
    private final MarketIndexData bankIndexData;
    private final MarketIndexData itIndexData;
    private final MarketIndexData metalIndexData;
    private final MarketIndexData autoIndexData;
    private final MarketIndexData energyIndexData;
    private final MarketIndexData fmcgIndexData;
    private final MarketIndexData pharmaIndexData;
    private final MarketIndexData realtyIndexData;

    public Sectoral(MarketIndexData bankIndexData, MarketIndexData itIndexData, MarketIndexData metalIndexData, MarketIndexData autoIndexData, MarketIndexData energyIndexData, MarketIndexData fmcgIndexData, MarketIndexData pharmaIndexData, MarketIndexData realtyIndexData) {
        super();
        this.bankIndexData = bankIndexData;
        this.itIndexData = itIndexData;
        this.metalIndexData = metalIndexData;
        this.autoIndexData = autoIndexData;
        this.energyIndexData = energyIndexData;
        this.fmcgIndexData = fmcgIndexData;
        this.pharmaIndexData = pharmaIndexData;
        this.realtyIndexData = realtyIndexData;
    }

    public MarketIndexData getBankIndexData() {
        return bankIndexData;
    }

    public MarketIndexData getItIndexData() {
        return itIndexData;
    }

    public MarketIndexData getMetalIndexData() {
        return metalIndexData;
    }

    public MarketIndexData getAutoIndexData() {
        return autoIndexData;
    }

    public MarketIndexData getEnergyIndexData() {
        return energyIndexData;
    }

    public MarketIndexData getFmcgIndexData() {
        return fmcgIndexData;
    }

    public MarketIndexData getPharmaIndexData() {
        return pharmaIndexData;
    }

    public MarketIndexData getRealtyIndexData() {
        return realtyIndexData;
    }
}
