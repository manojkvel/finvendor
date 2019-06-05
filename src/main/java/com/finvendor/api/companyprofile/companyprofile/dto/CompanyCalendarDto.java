package com.finvendor.api.companyprofile.companyprofile.dto;

public class CompanyCalendarDto {

    private String purpose;
    private String boardMeetinDate;

    public CompanyCalendarDto(String purpose, String boardMeetinDate) {
        this.purpose = purpose;
        this.boardMeetinDate = boardMeetinDate;
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
}
