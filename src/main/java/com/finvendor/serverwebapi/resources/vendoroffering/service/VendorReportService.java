package com.finvendor.serverwebapi.resources.vendoroffering.service;

import com.finvendor.serverwebapi.resources.vendoroffering.dao.VendorReportDataDao;
import com.finvendor.serverwebapi.resources.vendoroffering.dao.VendorReportFileDao;
import com.finvendor.serverwebapi.resources.vendoroffering.dto.VendorReportDataDto;
import com.finvendor.serverwebapi.resources.vendoroffering.dto.VendorReportFileDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VendorReportService {

    @Autowired
    private VendorReportDataDao dataDao;

    @Autowired
    private VendorReportFileDao fileDao;

    public String saveVoData(VendorReportDataDto dto) throws Exception {
        try {
            String productId = dataDao.saveVoData(dto);
            return productId;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public void saveVoFile(VendorReportFileDto dto) throws Exception {
        try {
            fileDao.saveVoFile(dto);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public VendorReportDataDto findVoData(String productId) throws Exception {
        try {
            VendorReportDataDto vo = dataDao.findVoData(productId);
            return vo;
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public VendorReportFileDto findVoFile(String productId) throws Exception {
        try {
            return fileDao.findVoFile(productId);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public boolean deleteVoData(String productId) throws Exception {
        try {
          return dataDao.deleteVoData(productId);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }


    public void deleteVoFile(String productId) throws Exception {
        try {
            fileDao.deleteVoFile(productId);
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }

    public String getCompanyName(String researchReportFor) {
        return "";
    }

    public List<VendorReportDataDto> findAllVo(String vendorName) {
        return dataDao.findAllVoData(vendorName);
    }
}
