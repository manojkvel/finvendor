package com.finvendor.api.dff.service.externalfeed.earningpreview.impl;

import com.finvendor.api.dff.service.externalfeed.earningpreview.EarningPreviewFeed;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AllEarningPreviewFeed implements EarningPreviewFeed {

    @Override
    public int feed(String filePath) throws Exception {
        return 0;
    }
}
