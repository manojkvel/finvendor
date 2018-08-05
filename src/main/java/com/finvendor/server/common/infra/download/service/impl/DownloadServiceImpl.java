package com.finvendor.server.common.infra.download.service.impl;

import com.finvendor.common.util.Pair;
import com.finvendor.server.common.infra.download.dao.IDownloadDao;
import com.finvendor.server.common.infra.download.service.IDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * Ayush on 5-Aug-2018
 */
@Service
public class DownloadServiceImpl implements IDownloadService {

    @Autowired
    private IDownloadDao dao;

    @Override
    public Pair<Long,InputStream> download(String productId) throws Exception {
        try {
            return dao.download(productId);
        }catch (RuntimeException e){
            throw new Exception(e);
        }
    }
}
