package com.finvendor.model.companyprofile;

import javax.persistence.*;

@Entity
@Table(name = "company_calendar_history")
public class CompanyCalendarHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rowId;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "board_meeting_date")
    private String boardMeetinDate;

    @ManyToOne
    @JoinColumn(name = "ticker")
    private CompanyCalendar companyCalendar;

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

    public String getBoardMeetinDate() {
        return boardMeetinDate;
    }

    public void setBoardMeetinDate(String boardMeetinDate) {
        this.boardMeetinDate = boardMeetinDate;
    }

    public CompanyCalendar getCompanyCalendar() {
        return companyCalendar;
    }

    public void setCompanyCalendar(CompanyCalendar companyCalendar) {
        this.companyCalendar = companyCalendar;
    }
}
