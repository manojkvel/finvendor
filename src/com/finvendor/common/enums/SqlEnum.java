package com.finvendor.common.enums;

public enum SqlEnum {
	EQUITY_RESEARCH("select rsch_sub_area_company_dtls.company_id,rsch_sub_area_company_dtls.company_name,rsch_area_stock_class.stock_class_name STYLE,market_cap_def.mcap_name M_CAP, research_sub_area.description SECTOR,stock_historial_prices.close_price AS CMP,stock_historial_prices.price_date AS PRC_DT, stock_current_info.pe AS PE,stock_current_info.3_yr_path_growth AS 3_YR_PAT_GRTH,vendor.username BROKER, ven_rsrch_rpt_offering.launched_year SINCE,ven_rsrch_rpt_analyst_prof.analyst_awards AWARD,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter CFA, broker_analyst.broker_rank BROKER_RANK,ven_rsrch_rpt_dtls.rsrch_recomm_type RECOMM_TYPE,ven_rsrch_rpt_dtls.price_at_recomm RRC_AT_RECOMM, ven_rsrch_rpt_dtls.target_price TGT_PRICE,(ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm / ven_rsrch_rpt_dtls.price_at_recomm) * 100 UPSIDE, ven_rsrch_rpt_dtls.rsrch_upload_report AS RPT_NAME,ven_rsrch_rpt_dtls.rep_date AS RPT_DATE, ven_rsrch_rpt_analyst_prof.analyst_name from rsch_sub_area_company_dtls,rsch_area_stock_class,market_cap_def,research_sub_area,stock_historial_prices,stock_current_info,vendor,broker_analyst, ven_rsrch_rpt_offering,ven_rsrch_rpt_dtls,ven_rsrch_rpt_analyst_prof,country where rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = market_cap_def.company_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_historial_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND ven_rsrch_rpt_offering.research_area = 7 AND ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id AND ven_rsrch_rpt_offering.vendor_id=broker_analyst.broker_id AND ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_analyst_prof.product_id AND ven_rsrch_rpt_dtls.company_id = rsch_sub_area_company_dtls.company_id AND rsch_sub_area_company_dtls.company_id = ven_rsrch_rpt_dtls.company_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND ven_rsrch_rpt_offering.research_area = 7 AND rsch_sub_area_company_dtls.country_id = ?"),
	EQUITY_RESEARCH_DASHBOARD("select rsch_sub_area_company_dtls.company_name,ven_rsrch_rpt_offering.product_name,ven_rsrch_rpt_dtls.rsrch_report_desc,ven_rsrch_rpt_dtls.rsrch_upload_report from ven_rsrch_rpt_offering,rsch_sub_area_company_dtls, ven_rsrch_rpt_dtls where rsch_sub_area_company_dtls.company_id = ven_rsrch_rpt_dtls.company_id and ven_rsrch_rpt_offering.product_id=ven_rsrch_rpt_dtls.product_id and rsch_sub_area_company_dtls.company_id=?"),
	SECTOR_RESEARCH(""),
	MACRO_RESEARCH(""),
	VO_COMPANY_DETAILS("select company_id, company_name from rsch_sub_area_company_dtls where rsch_sub_area_id in( SELECT research_sub_area_id FROM finvendo_dev.research_sub_area where research_area_id=?)"),
	VO_VENDOR_DETAILS("SELECT distinct(ven_rsrch_rpt_offering.vendor_id), vendor.username FROM ven_rsrch_rpt_offering,vendor where ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id");
	
	private String sqlQuery;

	private SqlEnum(String sqlQuery) {
		this.sqlQuery = sqlQuery;
	}

	public String valueOf() {
		return sqlQuery;
	}
}
