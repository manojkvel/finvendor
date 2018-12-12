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

    public void saveVoFile(VendorReportFileDto  dto){
        VendorReportFile vrfEntity=new VendorReportFile();
        vrfEntity.setProductId(dto.getProductId());
        vrfEntity.setReportFile(dto.getReportFile());
        saveOrUpdate(vrfEntity);
    }

    public VendorReportFileDto findReportFile(String productId) {
        VendorReportFile byId = findById(productId);
        VendorReportFileDto vendorReportFileDto=new VendorReportFileDto();
        vendorReportFileDto.setProductId(byId.getProductId());
        vendorReportFileDto.setReportFile(byId.getReportFile());
        return vendorReportFileDto;
    }
}
