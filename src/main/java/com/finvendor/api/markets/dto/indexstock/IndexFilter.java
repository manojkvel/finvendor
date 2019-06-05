package com.finvendor.api.markets.dto.indexstock;

import java.util.List;

public class IndexFilter {
    private List<String> indexNames;

    public List<String> getIndexNames() {
        return indexNames;
    }

    public void setIndexNames(List<String> indexNames) {
        this.indexNames = indexNames;
    }
}
