package com.finvendor.api.resources.dff.service;

import com.finvendor.common.infra.dff.DffProcesFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class InternalFeedFacade {

    @Autowired
    @Qualifier(value = "kennethFisherFeed")
    private DffProcesFeed kennethFisherFeed;

    @Autowired
    @Qualifier(value = "benjaminGrahamFeed")
    private DffProcesFeed benjaminGrahamFeed;

    public void processAndFeed_KennethFisherStrategy() throws Exception {
        kennethFisherFeed.processAndFeed();
    }

    public void processAndFeed_BenjaminGrahamStrategy() throws Exception {
        benjaminGrahamFeed.processAndFeed();
    }
}
