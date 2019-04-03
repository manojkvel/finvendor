package com.finvendor.api.resources.screener.mf.dao.filedao;

import com.finvendor.common.commondao.GenericDao;
import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.infra.persist.IFilePersist;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMfFilePersist<T> extends GenericDao<T> implements IFilePersist {
    @Autowired
    protected ICommonDao commonDao;
}
