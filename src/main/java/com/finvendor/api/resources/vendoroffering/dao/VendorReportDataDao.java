package com.finvendor.api.resources.vendoroffering.dao;

import com.finvendor.api.resources.vendoroffering.dto.VendorReportDataDto;
import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.model.vo.VendorReportData;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class VendorReportDataDao extends GenericDao<VendorReportData> {
    private static final Logger logger = LoggerFactory.getLogger(VendorReportDataDao.class.getName());
    @Autowired
    private ICommonDao commonDao;

    private static final String findVendorReportDataByVendorName = "select * from vendor_report_data where vendor_name=?";

    public String saveVoData(VendorReportDataDto dto) throws RuntimeException {
        String productId;
        try {
            VendorReportData vendorReportDataEntity;
            productId = dto.getProductId();
            if (StringUtils.isEmpty(productId)) {
                productId = UUID.randomUUID().toString();
                vendorReportDataEntity = new VendorReportData();
                vendorReportDataEntity.setProductId(productId);
                vendorReportDataEntity.setProductName(dto.getProductName());
                vendorReportDataEntity.setProductDescription(dto.getProductDescription());
                vendorReportDataEntity.setVendorId(dto.getVendorId());
                vendorReportDataEntity.setVendorName(dto.getVendorName());
                vendorReportDataEntity.setVendorCompany(dto.getVendorCompany());
                vendorReportDataEntity.setVendorAnalystType(dto.getVendorAnalystType());
                vendorReportDataEntity.setLaunchedYear(dto.getLaunchedYear());
                vendorReportDataEntity.setResearchAreaId(dto.getResearchAreaId());
                vendorReportDataEntity.setResearchSubAreaId(dto.getResearchSubAreaId());
                vendorReportDataEntity.setStockFundIssueCovered(null);
                vendorReportDataEntity.setAccessibility(null);
                vendorReportDataEntity.setSuitability(dto.getSuitability());
                vendorReportDataEntity.setSubCostPy(dto.getSubscriptionCostPerAnnum());
                vendorReportDataEntity.setReportFormat(null);
                vendorReportDataEntity.setResearchReportForId(dto.getResearchReportForId());
                vendorReportDataEntity.setReportDate(dto.getReportDate());
                vendorReportDataEntity.setTargetPrice(dto.getResearchTargetPrice());
                vendorReportDataEntity.setResearchRecommType(dto.getRecommType());
                vendorReportDataEntity.setPriceAtRecomm(dto.getPriceAtRecomm());
                vendorReportDataEntity.setResearchReportDesc(dto.getReportDescription());
                vendorReportDataEntity.setResearchReportAccess(dto.getReportAccess());
                vendorReportDataEntity.setAnalystName(dto.getAnalystName());

                vendorReportDataEntity.setReportName(dto.getReportName());
                vendorReportDataEntity.setAnalystAward(dto.getAnalystAwarded());
                vendorReportDataEntity.setAnalystCfaCharter(dto.getAnalystCfaCharter());
            } else {
                vendorReportDataEntity = findById(productId);
                if (!StringUtils.isEmpty(dto.getProductName())) {
                    vendorReportDataEntity.setProductName(dto.getProductName());
                }

                if (!StringUtils.isEmpty(dto.getProductDescription())) {
                    vendorReportDataEntity.setProductDescription(dto.getProductDescription());
                }

                if (!StringUtils.isEmpty(dto.getVendorId())) {
                    vendorReportDataEntity.setVendorId(dto.getVendorId());
                }

                if (!StringUtils.isEmpty(dto.getVendorName())) {
                    vendorReportDataEntity.setVendorName(dto.getVendorName());
                }

                if (!StringUtils.isEmpty(dto.getVendorCompany())) {
                    vendorReportDataEntity.setVendorCompany(dto.getVendorCompany());
                }

                if (!StringUtils.isEmpty(dto.getVendorAnalystType())) {
                    vendorReportDataEntity.setVendorAnalystType(dto.getVendorAnalystType());
                }

                if (!StringUtils.isEmpty(dto.getLaunchedYear())) {
                    vendorReportDataEntity.setLaunchedYear(dto.getLaunchedYear());
                }

                if (!StringUtils.isEmpty(dto.getResearchAreaId())) {
                    vendorReportDataEntity.setResearchAreaId(dto.getResearchAreaId());
                }

                if (!StringUtils.isEmpty(dto.getResearchSubAreaId())) {
                    vendorReportDataEntity.setResearchSubAreaId(dto.getResearchSubAreaId());
                }

                vendorReportDataEntity.setStockFundIssueCovered(null);
                vendorReportDataEntity.setAccessibility(null);

                if (!StringUtils.isEmpty(dto.getSuitability())) {
                    vendorReportDataEntity.setSuitability(dto.getSuitability());
                }

                if (!StringUtils.isEmpty(dto.getSubscriptionCostPerAnnum())) {
                    vendorReportDataEntity.setSubCostPy(dto.getSubscriptionCostPerAnnum());
                }

                vendorReportDataEntity.setReportFormat(null);

                if (!StringUtils.isEmpty(dto.getResearchReportForId())) {
                    vendorReportDataEntity.setResearchReportForId(dto.getResearchReportForId());
                }

                if (!StringUtils.isEmpty(dto.getReportDate())) {
                    vendorReportDataEntity.setReportDate(dto.getReportDate());
                }

                if (!StringUtils.isEmpty(dto.getResearchTargetPrice())) {
                    vendorReportDataEntity.setTargetPrice(dto.getResearchTargetPrice());
                }

                if (!StringUtils.isEmpty(dto.getRecommType())) {
                    vendorReportDataEntity.setResearchRecommType(dto.getRecommType());
                }

                if (!StringUtils.isEmpty(dto.getPriceAtRecomm())) {
                    vendorReportDataEntity.setPriceAtRecomm(dto.getPriceAtRecomm());
                }

                if (!StringUtils.isEmpty(dto.getReportDescription())) {
                    vendorReportDataEntity.setResearchReportDesc(dto.getReportDescription());
                }

                if (!StringUtils.isEmpty(dto.getAnalystName())) {
                    vendorReportDataEntity.setAnalystName(dto.getAnalystName());
                }

                if (!StringUtils.isEmpty(dto.getReportAccess())) {
                    vendorReportDataEntity.setResearchReportAccess(dto.getReportAccess());
                }

                if (!StringUtils.isEmpty(dto.getReportName())) {
                    vendorReportDataEntity.setReportName(dto.getReportName());
                }

                if (!StringUtils.isEmpty(dto.getAnalystAwarded())) {
                    vendorReportDataEntity.setAnalystAward(dto.getAnalystAwarded());
                }

                if (!StringUtils.isEmpty(dto.getAnalystCfaCharter())) {
                    vendorReportDataEntity.setAnalystCfaCharter(dto.getAnalystCfaCharter());
                }

                if (!StringUtils.isEmpty(dto.getReportName())) {
                    vendorReportDataEntity.setReportName(dto.getReportName());
                }
            }
            saveOrUpdate(vendorReportDataEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return productId;
    }

    public VendorReportDataDto findVoData(String productId) throws RuntimeException {
        try {
            VendorReportData vendorReportDataEntity = findById(productId);
            VendorReportDataDto dto = new VendorReportDataDto();
            dto.setProductId(vendorReportDataEntity.getProductId());
            dto.setProductName(vendorReportDataEntity.getProductName());
            dto.setProductDescription(vendorReportDataEntity.getProductDescription());
            dto.setVendorId(vendorReportDataEntity.getVendorId());
            dto.setVendorName(vendorReportDataEntity.getVendorName());
            dto.setVendorCompany(vendorReportDataEntity.getVendorCompany());
            dto.setVendorAnalystType(vendorReportDataEntity.getVendorAnalystType());
            dto.setLaunchedYear(vendorReportDataEntity.getLaunchedYear());

            String researchAreaId = vendorReportDataEntity.getResearchAreaId();
            //String nameForResearchReportForId = getNameForResearchReportForId(researchAreaId);
            //logger.info("nameForResearchReportForId: {}", nameForResearchReportForId);
            dto.setResearchAreaId(researchAreaId);

            String researchSubAreaId = vendorReportDataEntity.getResearchSubAreaId();
            //String nameForResearchSubAreaId = getNameForResearchSubAreaId(researchSubAreaId);
            //logger.info("nameForResearchSubAreaId: {}", nameForResearchSubAreaId);
            dto.setResearchSubAreaId(researchSubAreaId);

            dto.setStockFundIssueCovered(vendorReportDataEntity.getStockFundIssueCovered());
            dto.setAccessibility(vendorReportDataEntity.getAccessibility());
            dto.setSuitability(vendorReportDataEntity.getSuitability());
            dto.setSubscriptionCostPerAnnum(vendorReportDataEntity.getSubCostPy());
            dto.setReportFormat("");
            dto.setResearchReportForId(vendorReportDataEntity.getResearchReportForId());
            dto.setReportDate(vendorReportDataEntity.getReportDate());
            dto.setResearchTargetPrice(vendorReportDataEntity.getTargetPrice());
            dto.setRecommType(vendorReportDataEntity.getResearchRecommType());
            dto.setPriceAtRecomm(vendorReportDataEntity.getPriceAtRecomm());
            dto.setRepeortDescription(vendorReportDataEntity.getResearchReportDesc());
            dto.setAnalystName(vendorReportDataEntity.getAnalystName());
            dto.setReportAccess(vendorReportDataEntity.getResearchReportAccess());
            dto.setReportName(vendorReportDataEntity.getReportName());
            dto.setAnalystAwarded(vendorReportDataEntity.getAnalystAward());
            dto.setAnalystCfaCharter(vendorReportDataEntity.getAnalystCfaCharter());
            return dto;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getNameForResearchReportForId(String researchReportForId) {

        String name = "";
        String query = "select research_area_id,description from research_area a where research_area_id=?";
        SQLQuery nativeQuery = commonDao.getNativeQuery(query, new String[]{researchReportForId});

        List<Object[]> rows = nativeQuery.list();
        for (Object[] row : rows) {
            name = row[1] != null ? row[1].toString() : "";
        }
        return name;

    }

    private String getNameForResearchSubAreaId(String researchSubAreaId) {
        StringBuilder name = new StringBuilder();
        String[] ids=researchSubAreaId.split(",");
        for(String id:ids) {
            if(!StringUtils.isEmpty(id)) {
                String query = "select a.research_sub_area_id,a.description from research_sub_area a where a.research_sub_area_id=?";
                SQLQuery nativeQuery = commonDao.getNativeQuery(query, new String[]{id.trim()});
                List<Object[]> rows = nativeQuery.list();
                for (Object[] row : rows) {
                    name.append(row[1] != null ? row[1].toString() : "").append(",");
                }
            }
        }
        return name.deleteCharAt(name.length()-1).toString();

    }

    public List<VendorReportDataDto> findAllVoData(String existingVendorName) throws RuntimeException {
        try {
            SQLQuery nativeQuery = commonDao.getNativeQuery(findVendorReportDataByVendorName, new String[]{existingVendorName});
            List<Object[]> rows = nativeQuery.list();
            List<VendorReportDataDto> dataDtoList = new ArrayList<>();
            for (Object[] row : rows) {
                String productId = row[0] != null ? row[0].toString() : "";
                String productName = row[1] != null ? row[1].toString() : "";
                String productDesc = row[2] != null ? row[2].toString() : "";
                String vendorId = row[3] != null ? row[3].toString() : "";
                String vendorName = row[4] != null ? row[4].toString() : "";
                String vendorCompany = row[5] != null ? row[5].toString() : "";
                String vendorAnalystType = row[6] != null ? row[6].toString() : "";
                String launchedYear = row[7] != null ? row[7].toString() : "";
                String researchAreaId = row[8] != null ? row[8].toString() : "";
                String researchSubAreaId = row[9] != null ? row[9].toString() : "";
                String stockFundIssueCovered = row[10] != null ? row[10].toString() : "";
                String accessibility = row[11] != null ? row[11].toString() : "";
                String suitability = row[12] != null ? row[12].toString() : "";
                String subCostPy = row[13] != null ? row[13].toString() : "";
                String reportFormat = row[14] != null ? row[14].toString() : "";
                String researchReportForId = row[15] != null ? row[15].toString() : "";
                String reportDate = row[16] != null ? row[16].toString() : "";
                String targetPrice = row[17] != null ? row[17].toString() : "";
                String researchRecommType = row[18] != null ? row[18].toString() : "";
                String priceAtRecomm = row[19] != null ? row[19].toString() : "";
                String researchReportDesc = row[20] != null ? row[20].toString() : "";
                String researchReportAccess = row[21] != null ? row[21].toString() : "";
                String reportName = row[22] != null ? row[22].toString() : "";
                String analystName = row[23] != null ? row[23].toString() : "";
                String analystAwards = row[24] != null ? row[24].toString() : "";
                String analystCfaCharter = row[25] != null ? row[25].toString() : "";

                VendorReportDataDto dto = new VendorReportDataDto();
                dto.setProductId(productId);
                dto.setProductName(productName);
                dto.setProductDescription(productDesc);
                dto.setVendorId(vendorId);
                dto.setVendorName(vendorName);
                dto.setVendorCompany(vendorCompany);
                dto.setVendorAnalystType(vendorAnalystType);
                dto.setLaunchedYear(launchedYear);
                String nameForResearchReportForId = getNameForResearchReportForId(researchAreaId);
                dto.setResearchAreaId(nameForResearchReportForId);
                String nameForResearchSubAreaId = getNameForResearchSubAreaId(researchSubAreaId);
                dto.setResearchSubAreaId(nameForResearchSubAreaId);
                dto.setStockFundIssueCovered(stockFundIssueCovered);
                dto.setAccessibility(accessibility);
                dto.setSuitability(suitability);
                dto.setSubscriptionCostPerAnnum(subCostPy);
                dto.setReportFormat(reportFormat);
                dto.setResearchReportForId(researchReportForId);
                dto.setReportDate(reportDate);
                dto.setResearchTargetPrice(targetPrice);
                dto.setRecommType(researchRecommType);
                dto.setPriceAtRecomm(priceAtRecomm);
                dto.setReportDescription(researchReportDesc);
                dto.setReportAccess(researchReportAccess);
                dto.setReportName(reportName);
                dto.setAnalystName(analystName);
                dto.setAnalystAwarded(analystAwards);
                dto.setAnalystCfaCharter(analystCfaCharter);
                dataDtoList.add(dto);
            }
            return dataDtoList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteVoData(String productId) throws RuntimeException {
        boolean deleteStatus;
        try {
            VendorReportData byId = findById(productId);
            delete(byId);
            deleteStatus = true;
        } catch (Exception e) {
            deleteStatus = false;
        }
        return deleteStatus;
    }
}
