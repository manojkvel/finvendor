package com.finvendor.server.common.infra.upload;

public interface IFileUpload {

    /**
     * download from "fromPath" and uplod to /home/finvendo/tmp folder
     * @param fromPath
     * @param toPath
     * @return /home/finvendo/tmp/
     */
    String upload(String fromPath, String toPath) throws Exception;
}
