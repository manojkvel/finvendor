package com.finvendor.serverwebapi.resources.vendoroffering.dao;

import com.finvendor.model.vo.VendorReportFile;
import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.serverwebapi.resources.vendoroffering.dto.VendorReportDataDto;
import com.finvendor.serverwebapi.resources.vendoroffering.dto.VendorReportFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Blob;

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
