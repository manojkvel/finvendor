package com.finvendor.api.resources.markets.dto.indexstock;

import com.finvendor.serverwebapi.resources.markets.dto.IndexDto;

import java.util.List;

public class IndexData {
    private String title;
    private String indexDate;
    private List<IndexDto> indexes;

    public IndexData(String title, String indexDate, List<IndexDto> indexes) {
        this.title = title;
        this.indexDate = indexDate;
        this.indexes = indexes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIndexDate() {
        return indexDate;
    }

    public void setIndexDate(String indexDate) {
        this.indexDate = indexDate;
    }

    public List<IndexDto> getIndexes() {
        return indexes;
    }

    public void setIndexes(List<IndexDto> indexes) {
        this.indexes = indexes;
    }
}
