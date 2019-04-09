package com.finvendor.common.infra.dff;

/**
 * Process data and feed into database
 *
 * @author Ayush
 * @since 7-April-2019
 */
public interface DffProcesFeed {
    boolean processAndFeed() throws Exception;
}
