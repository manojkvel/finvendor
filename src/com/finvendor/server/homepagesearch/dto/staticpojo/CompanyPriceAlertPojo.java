package com.finvendor.server.homepagesearch.dto.staticpojo;

public class CompanyPriceAlertPojo extends AbstractHomePageSearchPojo {
	private static final long serialVersionUID = -1531614291471423894L;
	private String close_price;

	public String getClose_price() {
		return close_price;
	}

	public void setClose_price(String close_price) {
		this.close_price = close_price;
	}
}
