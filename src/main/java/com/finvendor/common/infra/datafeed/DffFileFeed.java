package com.finvendor.common.infra.datafeed;

/**
 * Parse File and feed data into db
 *
 * @author Ayush
 * @since 7-April-2019
 */
public interface DffFileFeed {
    int feed(String filePath) throws Exception;
}
