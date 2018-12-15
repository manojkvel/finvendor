package com.finvendor.api.resources.markets.dao;

import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.common.infra.persist.IFilePersist;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractNiftyFilePersist<T> extends GenericDao<T> implements IFilePersist {
    @Autowired
    protected ICommonDao commonDao;
}
