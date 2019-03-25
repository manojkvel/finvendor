package com.finvendor.common.infra.dff;

public interface DataFeedService {

    DataFeedService download(String url, String path) throws Exception;
    DataFeedService feed(String path) throws Exception;
}
