package com.finvendor.api.datafeed.service.internalfeed.screener;

import com.finvendor.api.datafeed.enums.FeedTypeEnum;
import com.finvendor.common.infra.datafeed.DffProcessFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ScreenerFeedFacade {

    @Autowired
    @Qualifier(value = "kennethFisherFeed")
    private DffProcessFeed kennethFisherFeed;

    @Autowired
    @Qualifier(value = "benjaminGrahamFeed")
    private DffProcessFeed benjaminGrahamFeed;

    @Autowired
    @Qualifier(value = "jamesShaughnessyFeed")
    private DffProcessFeed jamesShaughnessyFeed;

    @Autowired
    @Qualifier(value = "joelGreenblattFeed")
    private DffProcessFeed joelGreenblattFeed;

    @Autowired
    @Qualifier(value = "martinZweigFeed")
    private DffProcessFeed martinZweigFeed;

    @Autowired
    @Qualifier(value = "finvendorPickFeed")
    private DffProcessFeed finvendorPickFeed;

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
        case FINVENDOR_PICK:
            finvendorPickFeed.processAndFeed();
            break;
        default:
            throw new Exception("Invalid type found, Type: " + feedTypeEnum.name());
        }
    }
}
