package com.finvendor.common.infra.datafeed;

/**
 * Process data and feed into database
 *
 * @author Ayush
 * @since 7-April-2019
 */
public interface DffProcessFeed {
    boolean processAndFeed() throws Exception;
}
