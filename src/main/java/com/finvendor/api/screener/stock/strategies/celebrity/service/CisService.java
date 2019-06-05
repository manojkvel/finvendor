package com.finvendor.api.screener.stock.strategies.celebrity.service;

import com.finvendor.api.screener.stock.strategies.celebrity.dao.AbstractCisDao;
import com.finvendor.api.screener.stock.strategies.celebrity.dto.AbstractStrategyDto;
import com.finvendor.api.screener.stock.strategies.celebrity.enums.CisEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Celebrity Investors' Strategies (Cis) Service
 */
@Service
@Transactional
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

    @Autowired
    @Qualifier(value = "martinZweigDao")
    private AbstractCisDao martinZweigDao;

    @Autowired
    @Qualifier(value = "finvendorPickDao")
    private AbstractCisDao finvendorPickDao;

    /**
     * Find Celebrity Investor Records Stats for given type
     */
    public String findCisRecordStats(CisEnum type, String perPageMaxRecords) {
        String recordStats;
        switch (type) {
        case KENNITH_FISHER:
            recordStats = kennethFisherDao.findCisRecordStats(perPageMaxRecords);
            break;
        case BENJAMIN_GRAHAM:
            recordStats = benjaminGrahamDao.findCisRecordStats(perPageMaxRecords);
            break;
        case JAMES_SHAUGHNESSY:
            recordStats = jamesShaughnessyDao.findCisRecordStats(perPageMaxRecords);
            break;
        case JOEL_GREENBLATT:
            recordStats = joelGreenBlattDao.findCisRecordStats(perPageMaxRecords);
            break;
        case MARTIN_ZWEIG:
            recordStats = martinZweigDao.findCisRecordStats(perPageMaxRecords);
            break;
        case FINVENDOR_PICK:
            recordStats = finvendorPickDao.findCisRecordStats(perPageMaxRecords);
            break;
        default:
            recordStats = "";
        }
        return recordStats;
    }

    /**
     * Find Celebrity Investor Records for given type
     */
    public List<? extends AbstractStrategyDto> findCis(CisEnum type, String pageNumber, String perPageMaxRecords, String sortBy,
            String orderBy) {
        List<? extends AbstractStrategyDto> strategyResultList;
        switch (type) {
        case KENNITH_FISHER:
            strategyResultList = kennethFisherDao.findCis(pageNumber, perPageMaxRecords,sortBy, orderBy);
            break;
        case BENJAMIN_GRAHAM:
            strategyResultList = benjaminGrahamDao.findCis(pageNumber, perPageMaxRecords, sortBy, orderBy);
            break;
        case JAMES_SHAUGHNESSY:
            strategyResultList = jamesShaughnessyDao.findCis(pageNumber, perPageMaxRecords, sortBy, orderBy);
            break;
        case JOEL_GREENBLATT:
            strategyResultList = joelGreenBlattDao.findCis(pageNumber, perPageMaxRecords, sortBy, orderBy);
            break;
        case MARTIN_ZWEIG:
            strategyResultList = martinZweigDao.findCis(pageNumber, perPageMaxRecords, sortBy, orderBy);
            break;
        case FINVENDOR_PICK:
            strategyResultList = finvendorPickDao.findCis(pageNumber, perPageMaxRecords, sortBy, orderBy);
            break;
        default:
            strategyResultList = null;
        }
        return strategyResultList;
    }
}
