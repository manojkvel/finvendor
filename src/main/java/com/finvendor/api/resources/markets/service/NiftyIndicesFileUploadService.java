package com.finvendor.api.resources.markets.service;

import com.finvendor.common.infra.download.URLReader;
import com.finvendor.common.infra.upload.IFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;

@Service
public class NiftyIndicesFileUploadService implements IFileUpload {
    private static final Logger logger = LoggerFactory.getLogger(NiftyIndicesFileUploadService.class.getName());

    @Override
    public String upload(String fromPath, String toPath) throws Exception {
        logger.info("NiftyIndicesFileUploadService-> fromPath:{}", fromPath);
        logger.info("NiftyIndicesFileUploadService-> toPath:{}", toPath);

        String downloadPath = toPath + File.separator + "ind_close_all.csv";

        //Download File
        URLReader.copyURLToFile(new URL(fromPath), new File(downloadPath));
        return downloadPath;
    }
}
