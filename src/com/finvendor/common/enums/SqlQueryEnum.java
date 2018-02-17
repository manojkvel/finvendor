package com.finvendor.common.enums;

public enum SqlQueryEnum {
	EQUITY_RESEARCH("select \r\n" + "    rsch_sub_area_company_dtls.company_name,\r\n"
			+ "    rsch_area_stock_class.stock_class_name STYLE,\r\n" + "    market_cap_def.mcap_name M_CAP,\r\n"
			+ "    research_sub_area.description SECTOR,\r\n" + "    stock_historial_prices.close_price AS CMP,\r\n"
			+ "    stock_historial_prices.price_date AS PRC_DT,\r\n" + "    stock_current_info.pe AS PE,\r\n"
			+ "    stock_current_info.3_yr_path_growth AS 3_YR_PAT_GRTH,\r\n" + "    vendor.username BROKER,\r\n"
			+ "	ven_rsrch_rpt_offering.launched_year SINCE,\r\n"
			+ "	ven_rsrch_rpt_analyst_prof.analyst_awards AWARD,\r\n"
			+ "	ven_rsrch_rpt_analyst_prof.anayst_cfa_charter CFA,\r\n"
			+ "	ven_rsrch_rpt_dtls.rsrch_recomm_type RECOMM_TYPE,ven_rsrch_rpt_dtls.target_price TGT_PRICE,\r\n"
			+ "(ven_rsrch_rpt_dtls.target_price - stock_historial_prices.close_price/stock_historial_prices.close_price)*100 UPSIDE,\r\n"
			+ "ven_rsrch_rpt_dtls.rsrch_upload_report AS RPT_NAME,\r\n" + "ven_rsrch_rpt_dtls.rep_date AS RPT_DATE,\r\n"
			+ "ven_rsrch_rpt_analyst_prof.analyst_name\r\n" + "\r\n" + "from\r\n"
			+ "    rsch_sub_area_company_dtls,\r\n" + "    rsch_area_stock_class,\r\n" + "    market_cap_def,\r\n"
			+ "    research_sub_area,\r\n" + "    stock_historial_prices,\r\n" + "    stock_current_info,\r\n"
			+ "    vendor,\r\n" + "    ven_rsrch_rpt_offering,\r\n" + "	ven_rsrch_rpt_dtls,\r\n"
			+ "	ven_rsrch_rpt_analyst_prof,\r\n" + "	country\r\n" + "where\r\n"
			+ "		rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id\r\n"
			+ "        AND rsch_sub_area_company_dtls.company_id = market_cap_def.company_id\r\n"
			+ "        AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id\r\n"
			+ "        AND rsch_sub_area_company_dtls.company_id = stock_historial_prices.stock_id\r\n"
			+ "        AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id\r\n" + "		#\r\n"
			+ "        AND ven_rsrch_rpt_offering.research_area=7\r\n"
			+ "        AND ven_rsrch_rpt_offering.vendor_id=vendor.vendor_id\r\n"
			+ "		AND ven_rsrch_rpt_offering.product_id=ven_rsrch_rpt_analyst_prof.product_id\r\n" + "		#\r\n"
			+ "		AND ven_rsrch_rpt_dtls.company_id=rsch_sub_area_company_dtls.company_id # for price recomm,tgt price, rpt name, rpt date\r\n"
			+ "		#\r\n"
			+ "		AND rsch_sub_area_company_dtls.company_id=ven_rsrch_rpt_dtls.company_id # for analyst name\r\n"
			+ "		#\r\n" + "		\r\n" + "        AND rsch_sub_area_company_dtls.country_id = country.country_id\r\n"
			+ "		AND rsch_sub_area_company_dtls.country_id = ?\r\n"
			+ "		AND ven_rsrch_rpt_offering.research_area=7\r\n"
			+ "order by rsch_sub_area_company_dtls.company_name;"), SECTOR_RESEARCH(""), MACRO_RESEARCH("")
	// Blah...Blah..
	;
	private String sqlQuery;

	private SqlQueryEnum(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String getSqlQuery() {
		return sqlQuery;
	}
}
