package com.finvendor.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "indice_details")
public class IndiceDetails implements Serializable {

    @Id
    @Column(name = "indice_details_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer indiceDetailsId;

    @Column(name = "date")
    private String date;

    @Column(name = "open")
    private String open;

    @Column(name = "high")
    private String high;

    @Column(name = "low")
    private String low;

    @Column(name = "closing")
    private String closing;

    @Column(name = "point_change")
    private String pointChange;

    @Column(name = "percent_change")
    private String percentChange;

    @Column(name = "volume")
    private String volume;

    @Column(name = "turnover_in_crore")
    private String turnoverInCrore;

    @Column(name = "pe")
    private String pe;

    @Column(name = "pb")
    private String pb;

    @Column(name = "div_yield")
    private String divYield;

    public Integer getIndiceDetailsId() {
        return indiceDetailsId;
    }

    public void setIndiceDetailsId(Integer indiceDetailsId) {
        this.indiceDetailsId = indiceDetailsId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public String getPointChange() {
        return pointChange;
    }

    public void setPointChange(String pointChange) {
        this.pointChange = pointChange;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getTurnoverInCrore() {
        return turnoverInCrore;
    }

    public void setTurnoverInCrore(String turnoverInCrore) {
        this.turnoverInCrore = turnoverInCrore;
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

    public String getDivYield() {
        return divYield;
    }

    public void setDivYield(String divYield) {
        this.divYield = divYield;
    }
}
