package com.finvendor.api.common.dto;

import java.io.Serializable;

public class RecordStats implements Serializable {
    private int firstPageNumber;
    private long lastPageNumber;
    private int totalRecords;

    public RecordStats(int firstPageNumber, long lastPageNumber, int totalRecords) {
        this.firstPageNumber = firstPageNumber;
        this.lastPageNumber = lastPageNumber;
        this.totalRecords = totalRecords;
    }

    public int getFirstPageNumber() {
        return firstPageNumber;
    }

    public void setFirstPageNumber(int firstPageNumber) {
        this.firstPageNumber = firstPageNumber;
    }

    public long getLastPageNumber() {
        return lastPageNumber;
    }

    public void setLastPageNumber(long lastPageNumber) {
        this.lastPageNumber = lastPageNumber;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
}
