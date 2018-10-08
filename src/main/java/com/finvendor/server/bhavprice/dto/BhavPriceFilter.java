package com.finvendor.server.bhavprice.dto;

import java.util.List;

public class BhavPriceFilter {
    private List<String> mcap = null;
    private List<String> index = null;

    public List<String> getMcap() {
        return mcap;
    }

    public void setMcap(List<String> mcap) {
        this.mcap = mcap;
    }

    public List<String> getIndex() {
        return index;
    }

    public void setIndex(List<String> index) {
        this.index = index;
    }
}
