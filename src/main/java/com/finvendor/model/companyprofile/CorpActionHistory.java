package com.finvendor.model.companyprofile;

import javax.persistence.*;

@Entity
@Table(name = "company_news_history")
public class CorpActionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rowId;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "face_value")
    private String faceValue;

    @Column(name = "ex_date")
    private String exDate;

    @Column(name = "record_date")
    private String recordDate;

    @ManyToOne
    @JoinColumn(name = "ticker")
    private CorpAction corpAction;

    public Integer getRowId() {
        return rowId;
    }

    public void setRowId(Integer rowId) {
        this.rowId = rowId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(String faceValue) {
        this.faceValue = faceValue;
    }

    public String getExDate() {
        return exDate;
    }

    public void setExDate(String exDate) {
        this.exDate = exDate;
    }

    public String getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(String recordDate) {
        this.recordDate = recordDate;
    }

    public CorpAction getCorpAction() {
        return corpAction;
    }

    public void setCorpAction(CorpAction corpAction) {
        this.corpAction = corpAction;
    }
}
