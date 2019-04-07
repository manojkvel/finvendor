package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.infra.dff.DffProcesFeed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 */
@Service
@Transactional
public class KennethFisherFeed implements DffProcesFeed {

    private static final Logger logger = LoggerFactory.getLogger(KennethFisherFeed.class.getName());

    @Autowired
    private ICommonDao commonDao;

    @Override
    public boolean processAndFeed() throws Exception {
        return false;
    }
}
