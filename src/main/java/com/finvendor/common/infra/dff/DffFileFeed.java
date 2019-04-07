package com.finvendor.common.infra.dff;

/**
 * Parse File and feed data into db
 *
 * @author Ayush
 * @since 7-April-2019
 */
public interface DffFileFeed {
    int feed(String filePath) throws Exception;
}
