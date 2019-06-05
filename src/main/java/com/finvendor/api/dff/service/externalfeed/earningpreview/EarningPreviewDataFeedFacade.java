package com.finvendor.api.dff.service.externalfeed.earningpreview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class EarningPreviewDataFeedFacade {



    @Autowired
    @Qualifier(value = "allEarningPreviewFeed")
    private EarningPreviewFeed allEarningPreviewFeed;

    public void feedEarningPreviewData(String filePath) throws Exception {
        allEarningPreviewFeed.feed(filePath);
    }
}
