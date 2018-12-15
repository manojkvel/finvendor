package com.finvendor.api.resources.researchreport.mf.dao.filedao;

import com.finvendor.server.common.commondao.GenericDao;
import com.finvendor.server.common.commondao.ICommonDao;
import com.finvendor.server.common.infra.persist.IFilePersist;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractMfFilePersist<T> extends GenericDao<T> implements IFilePersist {
    @Autowired
    protected ICommonDao commonDao;
}
