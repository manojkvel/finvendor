package com.finvendor.api.resources.markets.service;

import com.finvendor.server.common.infra.download.URLReader;
import com.finvendor.server.common.infra.upload.IFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;

@Service
public class MarketsFileUploadService implements IFileUpload {
    private static final Logger logger = LoggerFactory.getLogger(MarketsFileUploadService.class.getName());

    @Override
    public String upload(String fromPath, String toPath) throws Exception {
        logger.info("BhavCopyCsvFileUpload-> fromPath:{}", fromPath);
        logger.info("BhavCopyCsvFileUpload-> toPath:{}", toPath);

        URL url = new URL(fromPath);
        String downloadDirectory = toPath;
        String bhavZipFileDownloadPath = downloadDirectory + "/bhav.zip";

        logger.info("bhavZipFileDownloadPath:{}", bhavZipFileDownloadPath);

        //Download File
        URLReader.copyURLToFile(url, new File(bhavZipFileDownloadPath));

        //Upzip Bhav Copy
        URLReader.unzip(bhavZipFileDownloadPath, downloadDirectory);
        String bhavCsvFileName = StringUtils.replace(StringUtils.substring(fromPath, fromPath.lastIndexOf("/") + 1), ".zip", "");
        String bhavCsvFilePath = downloadDirectory + "/" + bhavCsvFileName;
        logger.info("bhavCsvFilePath:{}", bhavCsvFilePath);
        return bhavCsvFilePath;
    }
}
