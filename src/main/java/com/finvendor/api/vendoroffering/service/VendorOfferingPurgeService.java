package com.finvendor.api.vendoroffering.service;

import com.finvendor.api.vendoroffering.dao.VendorOfferingPurgeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class VendorOfferingPurgeService {

    private final VendorOfferingPurgeDao dao;

    @Autowired
    public VendorOfferingPurgeService(VendorOfferingPurgeDao dao) {
        this.dao = dao;
    }

    public void purgeVendorReport() throws Exception {
        try {
            dao.purgeVendorReportData();
        } catch (RuntimeException e) {
            throw new Exception(e);
        }
    }
}
