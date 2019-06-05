package com.finvendor.api.markets.dao;

import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.infra.persist.IFilePersist;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMarketsFilePersist<T> extends GenericDao<T> implements IFilePersist {
    @Autowired
    protected ICommonDao commonDao;
}
