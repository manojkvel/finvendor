package com.finvendor.modelpojo.staticpojo.stockprice;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author ayush on 28-apr-2018
 */
@JsonPropertyOrder({ "stock_id", "price_src_code",
	"price_date", "open_price", "high_price", "low_price", "close_price","last_traded_price"})
public class StockCurrentPriceDTO {
	
    private Integer stock_id;
	
    private String price_src_code;
    
    private String price_date;
    
    private String open_price;
    
    private String high_price;
    
    private String low_price;
    
    private String close_price;
    
    private String last_traded_price;

	public Integer getStock_id() {
		return stock_id;
	}

	public void setStock_id(Integer stock_id) {
		this.stock_id = stock_id;
	}

	public String getPrice_src_code() {
		return price_src_code;
	}

	public void setPrice_src_code(String price_src_code) {
		this.price_src_code = price_src_code;
	}

	public String getPrice_date() {
		return price_date;
	}

	public void setPrice_date(String price_date) {
		this.price_date = price_date;
	}

	public String getOpen_price() {
		return open_price;
	}

	public void setOpen_price(String open_price) {
		this.open_price = open_price;
	}

	public String getHigh_price() {
		return high_price;
	}

	public void setHigh_price(String high_price) {
		this.high_price = high_price;
	}

	public String getLow_price() {
		return low_price;
	}

	public void setLow_price(String low_price) {
		this.low_price = low_price;
	}

	public String getClose_price() {
		return close_price;
	}

	public void setClose_price(String close_price) {
		this.close_price = close_price;
	}

	public String getLast_traded_price() {
		return last_traded_price;
	}

	public void setLast_traded_price(String last_traded_price) {
		this.last_traded_price = last_traded_price;
	}
	
	
}
