package com.finvendor.api.resources.companyprofile.pricealert.dao;

import com.finvendor.model.CompanyPriceAlert;
import com.finvendor.model.CompanyWatchList;
import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.ICommonDao;
import org.hibernate.SQLQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * 
 * @author ayush on May 01, 2018
 */
@Repository
public class CompanyPriceAlertDao extends GenericDao<CompanyPriceAlert> {

	@Autowired
	private ICommonDao commonDao;

	@SuppressWarnings("unchecked")
	public boolean addConsumerPriceAlert(ConsumerPriceAlertDTO priceAlertPojo) throws RuntimeException {
		boolean addStatus;
		try {
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("username", priceAlertPojo.getUserName());
			paramMap.put("companyId", Integer.parseInt(priceAlertPojo.getCompanyId()));

			org.hibernate.Query query = commonDao.getNamedQuery(
					CompanyPriceAlert.COMPANY_PRICE_ALERT_BY_COMPANY_ID_AND_USER_NAME_NAMED_QUERY, paramMap);
			List<CompanyWatchList> companyPriceAlertEntityList = query.list();

			if (companyPriceAlertEntityList.size() == 0) {
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

	@SuppressWarnings("unchecked")
	public boolean updateConsumerPriceAlert(ConsumerPriceAlertDTO companyPriceAlertPojo) throws RuntimeException {
		try {
			Map<Object, Object> paramMap = new HashMap<>();
			paramMap.put("username", companyPriceAlertPojo.getUserName());
			paramMap.put("companyId", Integer.parseInt(companyPriceAlertPojo.getCompanyId()));

			org.hibernate.Query query = commonDao.getNamedQuery(
					CompanyPriceAlert.COMPANY_PRICE_ALERT_BY_COMPANY_ID_AND_USER_NAME_NAMED_QUERY, paramMap);
			List<CompanyPriceAlert> companyPriceAlertEntityList = query.list();

			if (companyPriceAlertEntityList == null || companyPriceAlertEntityList.size() == 0) {
				return false;
			}
			CompanyPriceAlert entity = companyPriceAlertEntityList.get(0);

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

	@SuppressWarnings("unchecked")
	public boolean deleteConsumerPriceAlerts(List<ConsumerPriceAlertDTO> pojoList) throws RuntimeException {
		boolean deleteStatus = true;
		try {
			Map<Object, Object> paramMap = new HashMap<>();

			for (ConsumerPriceAlertDTO pojo : pojoList) {
				paramMap.put("username", pojo.getUserName());
				paramMap.put("companyId", Integer.parseInt(pojo.getCompanyId()));
				org.hibernate.Query query = commonDao.getNamedQuery(
						CompanyPriceAlert.COMPANY_PRICE_ALERT_BY_COMPANY_ID_AND_USER_NAME_NAMED_QUERY, paramMap);
				List<CompanyPriceAlert> companyPriceAlertEntityList = query.list();
				if (companyPriceAlertEntityList != null && companyPriceAlertEntityList.size() == 1) {
					delete(companyPriceAlertEntityList.get(0));
					paramMap.clear();
				} else {
					throw new Exception("Unable to delete company price alert !!");
				}
			}
		} catch (Exception e) {
			deleteStatus = false;
		}
		return deleteStatus;
	}

	@SuppressWarnings("unchecked")
	public ConsumerPriceAlertDTO findConsumerPriceAlert(String companyIdInputParam, String userNameInputParam)
			throws RuntimeException {
		SQLQuery query = commonDao.getNativeQuery(
				"SELECT * FROM company_price_alert where company_id=? and user_name=?",
				new String[] { companyIdInputParam, userNameInputParam });
		List<Object[]> rows = query.list();
		ConsumerPriceAlertDTO pojo = new ConsumerPriceAlertDTO();
		if (rows.size() == 0) {
			pojo.setCurrDate(null);
		}
		for (Object[] row : rows) {
			String companyId = row[1] != null ? row[1].toString().trim() : "";
			String companyName = row[2] != null ? row[2].toString().trim() : "";
			String userName = row[3] != null ? row[3].toString().trim() : "";

			String cmpWhenPriceAlertWasSet = row[4] != null ? row[4].toString().trim() : "";

			String dayMinPrice = row[5] != null ? row[5].toString().trim() : "";
			String dayMaxPrice = row[6] != null ? row[6].toString().trim() : "";

			String weekMinPrice = row[7] != null ? row[7].toString().trim() : "";
			String weekMaxPrice = row[8] != null ? row[8].toString().trim() : "";

			String monthMinPrice = row[9] != null ? row[9].toString().trim() : "";
			String monthMaxPrice = row[10] != null ? row[10].toString().trim() : "";

			String isResearchReport = row[11] != null ? row[11].toString().trim() : "";
			String noTimeFrameMinPrice = row[12] != null ? row[12].toString().trim() : "";
			String noTimeFrameMaxPrice = row[13] != null ? row[13].toString().trim() : "";

			String currDate = row[14] != null ? row[14].toString().trim() : "";

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
	public List<ConsumerPriceAlertDTO> findAllConsumerPriceAlerts(Map<Object, Object> paramMap)
			throws RuntimeException {
		try {
			org.hibernate.Query query = commonDao.getNamedQuery(CompanyPriceAlert.COMPANY_PRICEALERT_BY_USER_NQ,
					paramMap);
			List<CompanyPriceAlert> companyPriceAlertEnityList = query.list();
			List<ConsumerPriceAlertDTO> pojoList = new ArrayList<>();

			for (CompanyPriceAlert companyPriceAlertEntity : companyPriceAlertEnityList) {
				ConsumerPriceAlertDTO pojo = new ConsumerPriceAlertDTO();
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
						Boolean.parseBoolean(companyPriceAlertEntity.getIs_research_price()));
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
	public boolean isResearchPriceSet(String companyName) throws RuntimeException {
		try {
			SQLQuery query = commonDao.getNativeQuery(
					"SELECT company_id,is_research_price FROM company_price_alert where company_name=?",
					new String[] { companyName });

			List<Object[]> rows = query.list();
			for (Object[] row : rows) {
				// String companyId = row[0] != null ? row[0].toString().trim() : "";
				String isResearchPrice = row[1] != null ? row[1].toString().trim() : "";
				if ("true".equals(isResearchPrice)) {
					return true;
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return false;
	}
}
