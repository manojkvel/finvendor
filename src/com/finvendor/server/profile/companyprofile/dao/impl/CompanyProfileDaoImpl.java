package com.finvendor.server.profile.companyprofile.dao.impl;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.finvendor.common.util.JsonUtil;
import com.finvendor.server.common.dao.ICommonDao;
import com.finvendor.server.profile.companyprofile.dao.ICompanyProfileDao;
import com.finvendor.server.profile.companyprofile.dto.CompanyProfileData;
import com.finvendor.server.researchreport.dao.IResearchReportDao;
import com.finvendor.server.researchreport.dto.filter.ResearchReportFilter;
import com.finvendor.server.researchreport.dto.result.AbsResearchReportResult;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Repository
public class CompanyProfileDaoImpl implements ICompanyProfileDao {
	public static Logger logger = LoggerFactory.getLogger(CompanyProfileDaoImpl.class);

	/***/
	public static final String companyDataQuery = "SELECT distinct(x.company_id), x.company_name, x.isin_code, x.ticker from rsch_sub_area_company_dtls x, country y where x.country_id=y.country_id  and x.country_id = COUNTRYID and x.company_name like COMPANYNAME or x.isin_code like ISINCODE or x.ticker like TICKER order by x.company_name";
	public static final String companyProfileDataQuery = "SELECT rsch_sub_area_company_dtls.company_id companyId,rsch_sub_area_company_dtls.company_name companyName, market_cap_def.market_cap_name mcap, research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.last_trade_price ltp FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices,country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND research_sub_area.research_area_id = 7 AND country.country_id = COUNTRYID AND rsch_sub_area_company_dtls.isin_code = ISINCODE";

	/***/
	public static final String buyCountQuery = "select count(*) from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_path_growth patGrth FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', - 1) rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y ON x.comapanyId = y.companyId where x.isinCode=? and y.recommType='buy'";
	public static final String sellCountQuery = "select count(*) from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_path_growth patGrth FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', - 1) rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y ON x.comapanyId = y.companyId where x.isinCode=? and y.recommType='sell'";
	public static final String neutralCountQuery = "select count(*) from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_path_growth patGrth FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', - 1) rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y ON x.comapanyId = y.companyId where x.isinCode=? and y.recommType='neutral'";
	public static final String avgCountQuery = "select avg(y.tgtPrice) from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_path_growth patGrth FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = 7 AND country.country_id = ?) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', - 1) rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y ON x.comapanyId = y.companyId where x.isinCode=?";

	/***/
	public static final String companyResearchReportQuery = "select x.comapanyId,x.companyName,x.isinCode, x.style,x.mcap,x.sector,x.cmp,x.prcDt,x.pe,x.patGrth,y.companyId,y.prdId,y.broker,y.recommType,y.tgtPrice,y.prcAtRecomm,y.upside,y.rptName,y.rsrchDt, y.award,y.cfa,y.analystName,y.analystType,y.vendorId,y.ly,y.userName,y.rptDesc from (SELECT rsch_sub_area_company_dtls.company_id comapanyId,rsch_sub_area_company_dtls.company_name companyName,rsch_sub_area_company_dtls.isin_code isinCode, rsch_area_stock_class.stock_class_name style,market_cap_def.market_cap_name mcap,research_sub_area.description sector,stock_current_prices.close_price cmp, stock_current_prices.price_date prcDt,stock_current_info.pe pe,stock_current_info.3_yr_path_growth patGrth FROM rsch_sub_area_company_dtls, rsch_area_stock_class, market_cap_def, comp_mkt_cap_type, research_sub_area, stock_current_prices, stock_current_info, country WHERE rsch_sub_area_company_dtls.stock_class_type_id = rsch_area_stock_class.stock_class_type_id AND rsch_sub_area_company_dtls.company_id = comp_mkt_cap_type.company_id AND comp_mkt_cap_type.market_cap_id = market_cap_def.market_cap_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id AND rsch_sub_area_company_dtls.company_id = stock_current_prices.stock_id AND rsch_sub_area_company_dtls.company_id = stock_current_info.stock_id AND rsch_sub_area_company_dtls.country_id = country.country_id AND rsch_sub_area_company_dtls.rsch_sub_area_id = research_sub_area.research_sub_area_id  AND research_sub_area.research_area_id = 7 AND country.country_id = COUNTRYID) x inner join (SELECT distinct ven_rsrch_rpt_dtls.company_id companyId, ven_rsrch_rpt_offering.product_id prdId, vendor.company broker,ven_rsrch_rpt_dtls.rsrch_recomm_type recommType, ven_rsrch_rpt_dtls.target_price tgtPrice,ven_rsrch_rpt_dtls.price_at_recomm prcAtRecomm, ((ven_rsrch_rpt_dtls.target_price - ven_rsrch_rpt_dtls.price_at_recomm) / ven_rsrch_rpt_dtls.price_at_recomm) * 100 upside, SUBSTRING_INDEX(ven_rsrch_rpt_dtls.rsrch_upload_report, '/', - 1) rptName, ven_rsrch_rpt_dtls.rep_date rsrchDt, ven_rsrch_rpt_analyst_prof.analyst_awards award,ven_rsrch_rpt_analyst_prof.anayst_cfa_charter cfa,ven_rsrch_rpt_analyst_prof.analyst_name analystName, vendor.analystType analystType,vendor.vendor_id vendorId,ven_rsrch_rpt_offering.launched_year ly,vendor.username userName,ven_rsrch_rpt_dtls.rsrch_report_desc rptDesc FROM ven_rsrch_rpt_offering, ven_rsrch_rpt_dtls, ven_rsrch_rpt_analyst_prof, vendor, broker_analyst WHERE ven_rsrch_rpt_offering.product_id = ven_rsrch_rpt_dtls.product_id and ven_rsrch_rpt_dtls.product_id = ven_rsrch_rpt_analyst_prof.product_id and ven_rsrch_rpt_offering.vendor_id = vendor.vendor_id and vendor.vendor_id = broker_analyst.broker_id AND ven_rsrch_rpt_offering.research_area = 7) y ON x.comapanyId = y.companyId where x.isinCode=ISINCODE ";

	@Autowired
	private ICommonDao commonDao;

	@Autowired
	protected SessionFactory sessionFactory;

	@Autowired
	@Qualifier(value = "equityResearchDaoImpl")
	private IResearchReportDao equityResearchReportDao;

	@SuppressWarnings("unchecked")
	@Override
	public String getProfile(String mainQuery) throws RuntimeException {
		SQLQuery query = commonDao.getSql(mainQuery, null);
		List<Object[]> rows = query.list();
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		String companyProfile = "NA";
		try {
			for (Object[] row : rows) {
				String companyId = row[0] != null ? row[0].toString() : "";
				String companyName = row[1] != null ? row[1].toString() : "";
				String mcap = row[2] != null ? row[2].toString() : "";
				String industry = row[3] != null ? row[3].toString() : "";
				String cmp = row[4] != null ? row[4].toString() : "";

				// ltp:LastTradedPrice
				String ltp = row[5] != null ? row[5].toString() : "";
				float cmpAsFloat = Float.parseFloat(cmp);
				float ltpAsFloat = Float.parseFloat(ltp);

				String absoluteLastChangedCmp = String.valueOf(cmpAsFloat - ltpAsFloat);
				String lastChangedCmpInPercentage = String.valueOf((cmpAsFloat - ltpAsFloat) * 100 / cmpAsFloat);

				paramsMap.put("companyProfileData", new CompanyProfileData(companyId, companyName, industry, mcap, cmp,
						absoluteLastChangedCmp, lastChangedCmpInPercentage));

			}
			paramsMap.put("summary", "xxxxx");
			companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
			return companyProfile;
		} catch (IOException e) {
			throw new RuntimeException("Error has occured while creating json for company data", e);
		}
	}

	@Override
	public String getReasearchReport(String isinCode, String mainQuery, ResearchReportFilter filter, String pageNumber,
			String perPageMaxRecords, String sortBy, String orderBy) throws RuntimeException {
		Map<String, Object> paramsMap = new LinkedHashMap<>();
		String companyProfile = "NA";
		try {
			Map<String, ? extends AbsResearchReportResult> equityData = equityResearchReportDao
					.findResearchReportTableData(mainQuery, filter, pageNumber, perPageMaxRecords, sortBy, orderBy);

			Collection<? extends AbsResearchReportResult> equityList = equityData.values();

			paramsMap.put("noOfAnalystReport", equityList.size());
			// Total Buy Recomm
			SQLQuery sqlQuery = commonDao.getSql(buyCountQuery, new String[] { "1", isinCode });
			Object object = sqlQuery.list().get(0);
			if (object instanceof BigInteger) {
				BigInteger i = (BigInteger) object;
				paramsMap.put("totalBuyRecomm", i);
			}

			// Total Sell Recomm
			sqlQuery = commonDao.getSql(sellCountQuery, new String[] { "1", isinCode });
			object = sqlQuery.list().get(0);
			if (object instanceof BigInteger) {
				BigInteger i = (BigInteger) object;
				paramsMap.put("totalSellRecomm", i);
			}

			// Total Neutral Recomm
			sqlQuery = commonDao.getSql(neutralCountQuery, new String[] { "1", isinCode });
			object = sqlQuery.list().get(0);
			if (object instanceof BigInteger) {
				BigInteger i = (BigInteger) object;
				paramsMap.put("totalNeutralRecomm", i);
			}

			// Average Target Price
			sqlQuery = commonDao.getSql(avgCountQuery, new String[] { "1", isinCode });
			object = sqlQuery.list().get(0);
			if (object instanceof Double) {
				Double i = (Double) object;
				paramsMap.put("averageTargetPrice", i);
			}

			paramsMap.put("equityResearch", equityList);
			companyProfile = JsonUtil.createJsonFromParamsMap(paramsMap);
		} catch (Exception e) {
			throw new RuntimeException("Error has occured getReasearchReportData(), DAO Error::", e);
		}
		return companyProfile;
	}
}
