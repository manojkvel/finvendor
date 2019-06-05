package com.finvendor.api.vendoroffering.dao;

import com.finvendor.api.vendoroffering.dto.VendorReportFileDto;
import com.finvendor.model.vo.VendorReportFile;
import com.finvendor.common.commondao.GenericDao;
import org.springframework.stereotype.Repository;

@Repository
public class VendorReportFileDao extends GenericDao<VendorReportFile> {

    public void saveVoFile(VendorReportFileDto dto) {
        try {
            VendorReportFile vrfEntity = new VendorReportFile();
            vrfEntity.setProductId(dto.getProductId());
            vrfEntity.setReportFile(dto.getReportFile());
            saveOrUpdate(vrfEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public VendorReportFileDto findVoFile(String productId) {
        VendorReportFileDto vendorReportFileDto = new VendorReportFileDto();
        try {
            VendorReportFile byId = findById(productId);
            vendorReportFileDto.setProductId(byId.getProductId());
            vendorReportFileDto.setReportFile(byId.getReportFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return vendorReportFileDto;
    }

    public boolean deleteVoFile(String productId) {
        boolean deleteStatus;
        try {
            VendorReportFile byId = findById(productId);
            delete(byId);
            deleteStatus = true;
        } catch (Exception e) {
            deleteStatus = false;
        }
        return deleteStatus;
    }
}
