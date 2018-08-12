package com.finvendor.server.companyprofile.companyprofile.dao.impl;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.finvendor.common.util.DateUtil;
import com.finvendor.common.util.JsonUtil;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.companyprofile.companyprofile.dao.ICompanyProfileDao1;
import com.finvendor.server.companyprofile.companyprofile.dto.CompanyProfileData;
import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.AbsResearchReportResult;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Repository
public class CompanyProfileDaoImpl implements ICompanyProfileDao1 {
	public static Logger logger = LoggerFactory.getLogger(CompanyProfileDaoImpl.class);

	/** Company Search Query */
	public static final String companyDataQuery = "SELECT distinct(x.company_id), x.company_name, x.isin_code, x.ticker from rsch_sub_area_company_dtls x, country y where x.country_id=y.country_id  and x.country_id = %1$d and x.company_name like '%2$s' or x.isin_code like '%3$s' or x.ticker like '%4$s' order by x.company_name";

	/** Company Profile Query */
	public static final String companyProfileDataQuery = "SELECT distinct rsch_sub_area_company_dtls.company_id companyId,rsch_sub_area_company_dtls.company_name companyName, market_cap_def.market_cap_name mcap, research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.last_trade_price ltp,stock_current_info.pe,stock_current_info.pb,stock_current_info.dividend_yield,stock_current_info.eps_ttm,stock_current_info.52w_high,stock_current_info.52w_low,stock_current_info.beta,stock_current_info.as_of_date,stock_current_info.shares_outstanding,stock_current_info.mkt_cap,stock_current_info.revenue,stock_current_info.face_value,stock_current_info.bv_share,stock_current_info.roe,stock_current_info.pat,stock_current_info.recent_qtr,stock_current_prices.price_date, stock_current_prices.price_src_code FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices,stock_current_info,country,ven_rsrch_rpt_dtls WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND research_sub_area.research_area_id = %1$d AND country.country_id = %2$d AND rsch_sub_area_company_dtls.isin_code = '%3$s'";

	/** Company Profile Other Query */
	public static final String buyCountQuery = "select count(*) from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_pat_growth patGrth FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', - 1) rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y ON x.comapanyId = y.companyId where x.isinCode=? and y.recommType='buy'";
	public static final String sellCountQuery = "select count(*) from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_pat_growth patGrth FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', - 1) rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y ON x.comapanyId = y.companyId where x.isinCode=? and y.recommType='sell'";
	public static final String neutralCountQuery = "select count(*) from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_pat_growth patGrth FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', - 1) rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y ON x.comapanyId = y.companyId where x.isinCode=? and y.recommType='neutral'";
	public static final String avgCountQuery = "select avg(y.tgtPrice) from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_pat_growth patGrth FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', - 1) rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y ON x.comapanyId = y.companyId where x.isinCode=?";

	/** Company Research Report Query */
	// Complex Query - Do not refactor it
	public static final String companyResearchReportQuery = "select x.comapanyId,x.companyName,x.isinCode, x.style,x.mcap,x.sector,x.cmp,x.prcDt,x.pe,x.patGrth,y.companyId,y.prdId,y.broker,y.recommType,y.tgtPrice,y.prcAtRecomm,y.upside,y.rptName,y.rsrchDt, y.award,y.cfa,y.analystName,y.analystType,y.vendorId,y.ly,y.userName,y.rptDesc,x.epsGrth,x.epsttm from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_pat_growth patGrth,stock_current_info.3_yr_eps_growth epsGrth,stock_current_info.eps_ttm epsttm FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = %1$d AND country.country_id = %2$d) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, ven_rsrch_rpt_dtls.report_name rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = %3$d) y ON x.comapanyId = y.companyId where x.isinCode='%4$s'";

	@Autowired
	private ICommonDao commonDao;

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	@Qualifier(value = "equityResearchDaoImpl")
	private IResearchReportDao equityResearchReportDao;
	private static DateFormat bhavDateFormatFromNSESite = new SimpleDateFormat("dd-MMM-yyyy");
	@SuppressWarnings("unchecked")
	@Override
	public String getCompanyProfile(String query) throws RuntimeException {
		SQLQuery query1 = commonDao.getNativeQuery(query, null);
		List<Object[]> rows = query1.list();
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		String companyProfile = "NA";
		String summary="";
		try {
			for (Object[] row : rows) {
				String companyId = row[0] != null ? row[0].toString().trim() : "";
				String companyName = row[1] != null ? row[1].toString().trim() : "";
				String mcap = row[2] != null ? row[2].toString().trim() : "";
				String industry = row[3] != null ? row[3].toString().trim() : "";
				String cmp = row[4] != null ? row[4].toString().trim() : "";

				// ltp:LastTradedPrice
				String ltp = row[5] != null ? row[5].toString().trim() : "";
				float cmpAsFloat = Float.parseFloat(cmp);
				float ltpAsFloat = Float.parseFloat(ltp);

				String absoluteLastChangedCmp = String.valueOf(cmpAsFloat - ltpAsFloat);
				String lastChangedCmpInPercentage = String.valueOf((cmpAsFloat - ltpAsFloat) * 100 / cmpAsFloat);

				// cmp/epsttm if eps is 0 then N/A
				String pe = row[6] != null ? row[6].toString().trim() : "";

				// cmp/bv_share if bv_share is 0 then na
				String pb = row[7] != null ? row[7].toString().trim() : "";

				String dividen_yield = row[8] != null ? row[8].toString().trim() : "";

				// static and it will updated quaterly
				String eps_ttm = row[9] != null ? row[9].toString().trim() : "";
				float eps_ttm_as_float = Float.parseFloat(eps_ttm);

				// temp and we will get from vendor feed
				String _52w_high = row[10] != null ? row[10].toString().trim() : "";
				// temp and we will get from vendor feed
				String _52w_low = row[11] != null ? row[11].toString().trim() : "";

				// static and it will updated quaterly
				String beta = row[12] != null ? row[12].toString().trim() : "";
				// String as_of_date = row[13] != null ? row[13].toString().trim() : "";

				// static and it will updated quaterly
				String share_outstanding = row[14] != null ? row[14].toString().trim() : "";// static
				float shareOutStandingAsFloat = Float.parseFloat(share_outstanding);

				// market cap needs to be calculated daily using below formula:
				// = shares_outstanding x today's market price
				String mkt_cap = String.valueOf(shareOutStandingAsFloat * cmpAsFloat);// row[15] != null ?
																						// row[15].toString().trim() :
																						// "";

				// static and it will updated quaterly
				String revenue = row[16] != null ? row[16].toString().trim() : "";// static
				revenue= StringUtils.remove(revenue,",");
				// static and it will updated quaterly
				String face_value = row[17] != null ? row[17].toString().trim() : "";// static

				// static and it will updated quaterly
				//String bv_share = row[18] != null ? row[18].toString().trim() : "";// static
				float bv_share_as_float = cmpAsFloat/Float.parseFloat(pb);

				// static and it will updated quaterly
				String roe = row[19] != null ? row[19].toString().trim() : "";// static update qtrly

				// static and it will updated quaterly
				String pat = row[20] != null ? row[20].toString().trim() : "";// static
				String recent_qtr = row[21] != null ? row[21].toString().trim() : "";

				// date is comming in this format "dd/MMM/yy HH:mm:ss"
				String price_date = row[22] != null ? row[22].toString().trim() : "";
				String price_date_in_millis = String.valueOf(DateUtil.convertFvPriceDateToTimestamp(price_date));
				String price_src_code=row[23] != null ? row[23].toString().trim() : "";
				// PE calculation PE=cmp/eps-ttm
				String newPeStr = "";
				float newPe = 0.0f;
				if (eps_ttm_as_float == 0.0f) {
					newPeStr = "N/A";
				} else {
					newPe = cmpAsFloat / eps_ttm_as_float;
					newPeStr = String.valueOf(newPe);
				}

				// PB Calculation PB=cmp/bv_share
				String newPBStr = "";
				float newPB = 0.0f;
				if (bv_share_as_float == 0.0f) {
					newPBStr = "N/A";
				} else {
					newPB = cmpAsFloat / bv_share_as_float;
					newPBStr = String.valueOf(newPB);
				}

				paramsMap.put("companyProfileData",
						new CompanyProfileData(companyId, companyName, industry, mcap, cmp, absoluteLastChangedCmp,
								lastChangedCmpInPercentage, newPeStr, "-", dividen_yield, eps_ttm, _52w_high,
								_52w_low, beta, share_outstanding, mkt_cap, revenue.trim(), face_value, "-", roe, pat,
								recent_qtr,price_date_in_millis,price_src_code));

			}
			paramsMap.put("summary", summary);
			companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
			return companyProfile;
		} catch (Exception e) {
			throw new RuntimeException("Error has occured while creating json for company profile data", e);
		}
	}

	@Override
	public String getCompanyProfileReasearchReport(String isinCode, String mainQuery, ResearchReportFilter filter,
			String pageNumber, String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		String companyProfile = "NA";
		try {
			Map<String, ? extends AbsResearchReportResult> equityData = equityResearchReportDao
					.findResearchReportTableData(mainQuery, filter, pageNumber, perPageMaxRecords, sortBy, orderBy);

			Collection<? extends AbsResearchReportResult> equityList = equityData.values();

			paramsMap.put("noOfAnalystReport", equityList.size());
			// Total Buy Recomm
			SQLQuery sqlQuery = commonDao.getNativeQuery(buyCountQuery, new String[] { "1", isinCode });
			Object object = sqlQuery.list().get(0);
			if (object instanceof BigInteger) {
				BigInteger i = (BigInteger) object;
				paramsMap.put("totalBuyRecomm", i);
			}

			// Total Sell Recomm
			sqlQuery = commonDao.getNativeQuery(sellCountQuery, new String[] { "1", isinCode });
			object = sqlQuery.list().get(0);
			if (object instanceof BigInteger) {
				BigInteger i = (BigInteger) object;
				paramsMap.put("totalSellRecomm", i);
			}

			// Total Neutral Recomm
			sqlQuery = commonDao.getNativeQuery(neutralCountQuery, new String[] { "1", isinCode });
			object = sqlQuery.list().get(0);
			if (object instanceof BigInteger) {
				BigInteger i = (BigInteger) object;
				paramsMap.put("totalNeutralRecomm", i);
			}

			// Average Target Price
			sqlQuery = commonDao.getNativeQuery(avgCountQuery, new String[] { "1", isinCode });
			object = sqlQuery.list().get(0);
			if (object instanceof Double) {
				Double i = (Double) object;
				paramsMap.put("averageTargetPrice", i);
			}

			paramsMap.put("equity", equityList);
			companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
		} catch (Exception e) {
			throw new RuntimeException("Error has occured getReasearchReportData(), DAO Error::", e);
		}
		return companyProfile;
	}
}
