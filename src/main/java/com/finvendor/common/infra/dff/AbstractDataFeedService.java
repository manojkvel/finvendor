package com.finvendor.common.infra.dff;

import com.finvendor.common.infra.download.URLReader;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public abstract class AbstractDataFeedService implements DataFeedService {
    public static final String COMMA = ",";
    protected void downloadFile(String url, String downloadPath) throws IOException {
        URLReader.copyURLToFile(new URL(url), new File(downloadPath));
    }

    protected void cleanupDirectory(String dir) throws IOException {
        File tmpPath = new File(dir);
        FileUtils.cleanDirectory(tmpPath);
    }
}
