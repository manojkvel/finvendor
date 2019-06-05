package com.finvendor.common.infra.upload;

public interface IFileUpload {

    /**
     * download from "fromPath" and upload to /home/finvendo/tmp folder
     * @param fromPath from file path
     * @param toPath to file path
     * @return /home/finvendo/tmp/
     */
    String upload(String fromPath, String toPath) throws Exception;
}
