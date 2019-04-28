package com.finvendor.api.resources.dff.service.internalfeed.screener;

import com.finvendor.api.resources.dff.enums.FeedTypeEnum;
import com.finvendor.common.infra.dff.DffProcesFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ScreenerFeedFacade {

    @Autowired
    @Qualifier(value = "kennethFisherFeed")
    private DffProcesFeed kennethFisherFeed;

    @Autowired
    @Qualifier(value = "benjaminGrahamFeed")
    private DffProcesFeed benjaminGrahamFeed;

    @Autowired
    @Qualifier(value = "jamesShaughnessyFeed")
    private DffProcesFeed jamesShaughnessyFeed;

    @Autowired
    @Qualifier(value = "joelGreenblattFeed")
    private DffProcesFeed joelGreenblattFeed;

    @Autowired
    @Qualifier(value = "martinZweigFeed")
    private DffProcesFeed martinZweigFeed;

    public void processAndFeed(FeedTypeEnum feedTypeEnum) throws Exception {
        switch (feedTypeEnum) {
        case KENNETH_FISHER:
            kennethFisherFeed.processAndFeed();
            break;
        case BENJAMIN_GRAHAM:
            benjaminGrahamFeed.processAndFeed();
            break;
        case JAMES_SHAUGHNESSY:
            jamesShaughnessyFeed.processAndFeed();
            break;
        case JOEL_GREENBLATT:
            joelGreenblattFeed.processAndFeed();
            break;
        case MARTIN_ZWEIG:
            martinZweigFeed.processAndFeed();
            break;
        default:
            throw new Exception("Invalid type found, Type: " + feedTypeEnum.name());
        }
    }
}
