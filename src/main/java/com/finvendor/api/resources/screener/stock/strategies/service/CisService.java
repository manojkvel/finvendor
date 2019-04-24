package com.finvendor.api.resources.screener.stock.strategies.service;

import com.finvendor.api.resources.screener.stock.strategies.dao.AbstractCisDao;
import com.finvendor.api.resources.screener.stock.strategies.dto.AbstractStrategyDto;
import com.finvendor.api.resources.screener.stock.strategies.dto.StrategyToolTips;
import com.finvendor.api.resources.screener.stock.strategies.enums.CisEnum;
import com.finvendor.common.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Celebrity Investors' Strategies (Cis) Service
 */
@Service
public class CisService {

    private static final String TOOL_TIP_KENNETH_FISHER = "Founder of Fisher Investments, Managing over $100B assets. Ken Fisher’s public stock picks outperform the broad U.S. stock market over the past 18 years by an average 4.2% annually";
    private static final String TOOL_TIP_BNJAMIN_GRAHAM = "Widely known as the \"father of value investing\", Warren buffet leant the value investment from Benjamin graham";

    @Autowired
    @Qualifier(value = "kennethFisherDao")
    private AbstractCisDao kennethFisherDao;

    @Autowired
    @Qualifier(value = "benjaminGrahamDao")
    private AbstractCisDao benjaminGrahamDao;

    @Autowired
    @Qualifier(value = "jamesShaughnessyDao")
    private AbstractCisDao jamesShaughnessyDao;

    @Autowired
    @Qualifier(value = "joelGreenBlattDao")
    private AbstractCisDao joelGreenBlattDao;
    /**
     * Find Celebrity Investor Records Stats for given type
     */
    public String findCisRecordStats(CisEnum type, String perPageMaxRecords) {
        String recordStats;
        switch (type) {
        case KENNITH_FISHER:
            recordStats = kennethFisherDao.findCisRecordStats(AbstractCisDao.KENNIT_FISHER_RECORD_STATS_QUERY, perPageMaxRecords);
            break;
        case BENJAMIN_GRAHAM:
            recordStats = benjaminGrahamDao.findCisRecordStats(AbstractCisDao.BENJAMIN_GRAHAM_RECORD_STATS_QUERY, perPageMaxRecords);
            break;
        case JAMES_SHAUGHNESSY:
            recordStats = jamesShaughnessyDao.findCisRecordStats(AbstractCisDao.JAMES_SHAUGHNESSY_RECORD_STATS_QUERY, perPageMaxRecords);
            break;
        case JOEL_GREENBLATT:
            recordStats = joelGreenBlattDao.findCisRecordStats(AbstractCisDao.JOEL_GREENBLATT_RECORD_STATS_QUERY, perPageMaxRecords);
            break;
        default:
            recordStats = "";
        }
        return recordStats;
    }

    /**
     * Find Celebrity Investor Records for given type
     */
    public List<? extends AbstractStrategyDto> findCis(CisEnum type, String pageNumber, String perPageMaxRecords) {
        List<? extends AbstractStrategyDto> strategyResultList = new ArrayList<>();
        switch (type) {
        case KENNITH_FISHER:
            strategyResultList = kennethFisherDao.findCis(AbstractCisDao.KENNIT_FISHER_STRATEGY_QUERY, pageNumber, perPageMaxRecords);
            break;
        case BENJAMIN_GRAHAM:
            strategyResultList = benjaminGrahamDao.findCis(AbstractCisDao.BENJAMIN_GRAHAM_STRATEGY_QUERY, pageNumber, perPageMaxRecords);
            break;
        case JAMES_SHAUGHNESSY:
            strategyResultList = jamesShaughnessyDao.findCis(AbstractCisDao.JAMES_SHAUGHNESSY_STRATEGY_QUERY, pageNumber, perPageMaxRecords);
            break;
        case JOEL_GREENBLATT:
            strategyResultList = joelGreenBlattDao.findCis(AbstractCisDao.JOEL_GREENBLATT_STRATEGY_QUERY, pageNumber, perPageMaxRecords);
            break;
        default:
            strategyResultList = null;
        }
        return strategyResultList;
    }

    public String findCisToolTips(CisEnum type) throws IOException {
        String toolTip;
        switch (type) {
        case KENNITH_FISHER:
            toolTip = JsonUtil.createJsonFromObject(new StrategyToolTips(TOOL_TIP_KENNETH_FISHER));
            break;
        case BENJAMIN_GRAHAM:
            toolTip = JsonUtil.createJsonFromObject(new StrategyToolTips(TOOL_TIP_BNJAMIN_GRAHAM));
            break;
        default:
            toolTip = "NA";
        }
        return toolTip;
    }
}
