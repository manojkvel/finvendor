package com.finvendor.server.common.infra.download.dao;

import com.finvendor.common.util.Pair;

import java.io.InputStream;

public interface IDownloadDao {
    Pair<Long,InputStream> download(String productId) throws RuntimeException;
}
