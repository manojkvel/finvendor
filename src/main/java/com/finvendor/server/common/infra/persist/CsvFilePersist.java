package com.finvendor.server.common.infra.persist;

import com.finvendor.server.common.infra.download.service.URLReader;

import java.io.File;
import java.net.URL;

public class CsvFilePersist extends AbstractFilePersist {

    private static final String downloadDirectory="d:\\ayush\\bhav";
    @Override
    public Long persist(String filePath) throws Exception {
        URL url = new URL(filePath);
        String bhavZipFileName = "bhav.zip";
        String bhavZipFileDownloadPath = downloadDirectory + "/" + bhavZipFileName;

        //Download File
        URLReader.copyURLToFile(url, new File(bhavZipFileDownloadPath));


        //Upzip Bhav Copy
        URLReader.unzip(bhavZipFileDownloadPath, downloadDirectory);
        return null;
    }


}
