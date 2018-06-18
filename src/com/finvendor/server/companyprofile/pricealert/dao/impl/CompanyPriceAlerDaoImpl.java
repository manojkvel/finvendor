package com.finvendor.server.companyprofile.pricealert.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.finvendor.model.CompanyPriceAlert;
import com.finvendor.modelpojo.staticpojo.wathlist.company.CompanyPriceAlertPojo;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.companyprofile.pricealert.dao.ICompanyPriceAlertDao;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Repository
public class CompanyPriceAlerDaoImpl extends GenericDao<CompanyPriceAlert> implements ICompanyPriceAlertDao {

	@Autowired
	private ICommonDao commonDao;

	@Override
	public boolean addCompanyPriceAlert(CompanyPriceAlertPojo priceAlertPojo) throws RuntimeException {
		boolean addStatus;
		try {
			CompanyPriceAlert findById = findById(Integer.parseInt(priceAlertPojo.getCompanyId()));
			if (findById == null) {
				CompanyPriceAlert companyPriceAlertEntity = new CompanyPriceAlert();
				companyPriceAlertEntity.setCompany_id(Integer.parseInt(priceAlertPojo.getCompanyId()));
				companyPriceAlertEntity.setCompany_name(priceAlertPojo.getCompanyName());

				companyPriceAlertEntity.setUser_name(priceAlertPojo.getUserName());

				companyPriceAlertEntity.setCmp_when_price_alert_set(priceAlertPojo.getCmpWhenPriceAlertSet());

				companyPriceAlertEntity.setDay_min_price(priceAlertPojo.getDayMinPrice());
				companyPriceAlertEntity.setDay_max_price(priceAlertPojo.getDayMaxPrice());

				companyPriceAlertEntity.setWeek_min_price(priceAlertPojo.getWeekMinPrice());
				companyPriceAlertEntity.setWeek_max_price(priceAlertPojo.getWeekMaxPrice());

				companyPriceAlertEntity.setMonth_min_price(priceAlertPojo.getMonthMinPrice());
				companyPriceAlertEntity.setMonth_max_price(priceAlertPojo.getMonthMaxPrice());

				if (priceAlertPojo.getIsResearchReport() != null) {
					companyPriceAlertEntity
							.setIs_research_price(String.valueOf(priceAlertPojo.getIsResearchReport().booleanValue()));
				}

				companyPriceAlertEntity.setNo_time_frame_min_price(priceAlertPojo.getNoTimeFrameMinPrice());
				companyPriceAlertEntity.setNo_time_frame_max_price(priceAlertPojo.getNoTimeFrameMaxPrice());

				companyPriceAlertEntity.setCurr_date(String.valueOf(Calendar.getInstance().getTimeInMillis()));
				saveOrUpdate(companyPriceAlertEntity);
				addStatus = true;
			} else {
				addStatus = false;
			}
			return addStatus;

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public boolean updateCompanyPriceAlert(CompanyPriceAlertPojo companyPriceAlertPojo) throws RuntimeException {
		try {
			CompanyPriceAlert entity = findById(Integer.parseInt(companyPriceAlertPojo.getCompanyId()));

			if (entity == null) {
				return false;
			}
			if (companyPriceAlertPojo.getCompanyName() != null) {
				entity.setCompany_name(companyPriceAlertPojo.getCompanyName());
			}

			if (companyPriceAlertPojo.getUserName() != null) {
				entity.setUser_name(companyPriceAlertPojo.getUserName());
			}

			if (companyPriceAlertPojo.getCmpWhenPriceAlertSet() != null) {
				entity.setCmp_when_price_alert_set(companyPriceAlertPojo.getCmpWhenPriceAlertSet());
			}

			if (companyPriceAlertPojo.getDayMinPrice() != null) {
				entity.setDay_min_price(companyPriceAlertPojo.getDayMinPrice());
			}

			if (companyPriceAlertPojo.getDayMaxPrice() != null) {
				entity.setDay_max_price(companyPriceAlertPojo.getDayMaxPrice());
			}

			if (companyPriceAlertPojo.getWeekMinPrice() != null) {
				entity.setWeek_min_price(companyPriceAlertPojo.getWeekMinPrice());
			}

			if (companyPriceAlertPojo.getWeekMaxPrice() != null) {
				entity.setWeek_max_price(companyPriceAlertPojo.getWeekMaxPrice());
			}

			if (companyPriceAlertPojo.getMonthMinPrice() != null) {
				entity.setMonth_min_price(companyPriceAlertPojo.getMonthMinPrice());
			}

			if (companyPriceAlertPojo.getMonthMaxPrice() != null) {
				entity.setMonth_max_price(companyPriceAlertPojo.getMonthMaxPrice());
			}

			if (companyPriceAlertPojo.getIsResearchReport() != null) {
				entity.setIs_research_price(String.valueOf(companyPriceAlertPojo.getIsResearchReport().booleanValue()));
			}

			if (companyPriceAlertPojo.getNoTimeFrameMinPrice() != null) {
				entity.setNo_time_frame_min_price(companyPriceAlertPojo.getNoTimeFrameMinPrice());
			}

			if (companyPriceAlertPojo.getNoTimeFrameMaxPrice() != null) {
				entity.setNo_time_frame_max_price(companyPriceAlertPojo.getNoTimeFrameMaxPrice());
			}

			entity.setCurr_date(String.valueOf(Calendar.getInstance().getTimeInMillis()));
			saveOrUpdate(entity);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public boolean deleteCompanyPriceAlerts(List<CompanyPriceAlertPojo> pojoList) throws RuntimeException {
		boolean deleteStatus = true;
		try {
			for (CompanyPriceAlertPojo pojo : pojoList) {
				String companyId = pojo.getCompanyId();
				CompanyPriceAlert companyPriceAlertEntity = findById(Integer.parseInt(companyId));
				delete(companyPriceAlertEntity);
			}
		} catch (Exception e) {
			deleteStatus = false;
		}
		return deleteStatus;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CompanyPriceAlertPojo findCompanyPriceAlert(String companyIdInputParam, String userNameInputParam)
			throws RuntimeException {
		SQLQuery query = commonDao.getNativeQuery(
				"SELECT * FROM company_price_alert where company_id=? and user_name=?",
				new String[] { companyIdInputParam, userNameInputParam });
		List<Object[]> rows = query.list();
		CompanyPriceAlertPojo pojo = new CompanyPriceAlertPojo();
		if (rows.size() == 0) {
			pojo.setCurrDate(null);
		}
		for (Object[] row : rows) {
			String companyId = row[0] != null ? row[0].toString().trim() : "";
			String companyName = row[1] != null ? row[1].toString().trim() : "";
			String userName = row[2] != null ? row[2].toString().trim() : "";

			String cmpWhenPriceAlertWasSet = row[3] != null ? row[3].toString().trim() : "";

			String dayMinPrice = row[4] != null ? row[4].toString().trim() : "";
			String dayMaxPrice = row[5] != null ? row[5].toString().trim() : "";

			String weekMinPrice = row[6] != null ? row[6].toString().trim() : "";
			String weekMaxPrice = row[7] != null ? row[7].toString().trim() : "";

			String monthMinPrice = row[8] != null ? row[8].toString().trim() : "";
			String monthMaxPrice = row[9] != null ? row[9].toString().trim() : "";

			String isResearchReport = row[10] != null ? row[10].toString().trim() : "";
			String noTimeFrameMinPrice = row[11] != null ? row[11].toString().trim() : "";
			String noTimeFrameMaxPrice = row[12] != null ? row[12].toString().trim() : "";

			String currDate = row[13] != null ? row[13].toString().trim() : "";

			pojo.setCompanyId(companyId);
			pojo.setCompanyName(companyName);
			pojo.setUserName(userName);
			pojo.setCmpWhenPriceAlertSet(cmpWhenPriceAlertWasSet);
			pojo.setDayMinPrice(dayMinPrice);
			pojo.setDayMaxPrice(dayMaxPrice);
			pojo.setWeekMinPrice(weekMinPrice);
			pojo.setWeekMaxPrice(weekMaxPrice);
			pojo.setMonthMinPrice(monthMinPrice);
			pojo.setMonthMaxPrice(monthMaxPrice);
			pojo.setIsResearchReport(Boolean.valueOf(isResearchReport));
			pojo.setNoTimeFrameMinPrice(noTimeFrameMinPrice);
			pojo.setNoTimeFrameMaxPrice(noTimeFrameMaxPrice);
			pojo.setCurrDate(currDate);
		}
		return pojo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CompanyPriceAlertPojo> findAllCompanyPriceAlerts(Map<Object, Object> paramMap) throws RuntimeException {
		try {
			org.hibernate.Query query = commonDao.getNamedQuery(CompanyPriceAlert.COMPANY_PRICEALERT_BY_USER_NQ,
					paramMap);
			List<CompanyPriceAlert> companyPriceAlertEnityList = query.list();
			List<CompanyPriceAlertPojo> pojoList = new ArrayList<>();

			for (CompanyPriceAlert companyPriceAlertEntity : companyPriceAlertEnityList) {
				CompanyPriceAlertPojo pojo = new CompanyPriceAlertPojo();
				pojo.setCompanyId(String.valueOf(companyPriceAlertEntity.getCompany_id()));
				pojo.setCompanyName(companyPriceAlertEntity.getCompany_name());
				pojo.setUserName(companyPriceAlertEntity.getUser_name());
				pojo.setCmpWhenPriceAlertSet(companyPriceAlertEntity.getCmp_when_price_alert_set());

				pojo.setDayMinPrice(companyPriceAlertEntity.getDay_min_price());
				pojo.setDayMaxPrice(companyPriceAlertEntity.getDay_max_price());

				pojo.setWeekMinPrice(companyPriceAlertEntity.getWeek_min_price());
				pojo.setWeekMaxPrice(companyPriceAlertEntity.getWeek_max_price());

				pojo.setMonthMinPrice(companyPriceAlertEntity.getMonth_min_price());
				pojo.setMonthMaxPrice(companyPriceAlertEntity.getMonth_max_price());

				pojo.setIsResearchReport(
						Boolean.valueOf(companyPriceAlertEntity.getIs_research_price()).booleanValue());
				pojo.setCurrDate(companyPriceAlertEntity.getCurr_date());
				pojo.setNoTimeFrameMinPrice(companyPriceAlertEntity.getNo_time_frame_min_price());
				pojo.setNoTimeFrameMaxPrice(companyPriceAlertEntity.getNo_time_frame_max_price());
				pojoList.add(pojo);
			}
			return pojoList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isResearchPriceSet(String companyName) throws RuntimeException {
		try {
			SQLQuery query = commonDao.getNativeQuery(
					"SELECT company_id,is_research_price FROM company_price_alert where company_name=?",
					new String[] { companyName });

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				// String companyId = row[0] != null ? row[0].toString().trim() : "";
				String isResearchPrice = row[1] != null ? row[1].toString().trim() : "";
				if (isResearchPrice.equals("true")) {
					return true;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return false;
	}
}
