package com.finvendor.common.infra.upload;

import com.finvendor.common.infra.upload.exception.FileUploadException;

public interface IFileUpload {

    /**
     * download from "fromPath" and upload to /home/finvendo/tmp folder
     * @param fromPath from file path
     * @param toPath to file path
     * @return /home/finvendo/tmp/
     */
    String upload(String fromPath, String toPath) throws FileUploadException;
}
