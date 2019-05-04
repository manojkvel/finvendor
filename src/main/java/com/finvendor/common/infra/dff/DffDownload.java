package com.finvendor.common.infra.dff;

/**
 * Download file from given url and download it to given path
 *
 * @author Ayush
 * @since 7-April-2019
 */
public interface DffDownload {
    boolean download(String url, String downloadPath) throws Exception;
}
