package com.finvendor.api.resources.dff.service.externalfeed.companyprofile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CompanyProfileDataFeedFacade {
    private static final String COMPANY_NEWS_URL = "https://www.nseindia.com/corporates/datafiles/AN_LATEST_ANNOUNCED.csv";
    private static final String CORP_ACTION_URL = "https://www.nseindia.com/corporates/datafiles/CA_ALL_FORTHCOMING.csv";
    private static final String COMPANY_CALENDAR_URL = "https://www.nseindia.com/corporates/datafiles/BM_All_Forthcoming.csv";

    @Autowired
    @Qualifier(value = "companyNewsFeed")
    private CompanyProfileFeed companyNewsFeed;

    @Autowired
    @Qualifier(value = "corpActionFeed")
    private CompanyProfileFeed corpActionFeed;

    @Autowired
    @Qualifier(value = "companyCalendarFeed")
    private CompanyProfileFeed companyCalendarFeed;

    public void feedNewsData(String downloadPath) throws Exception {
        boolean isDownloaded = companyNewsFeed.download(COMPANY_NEWS_URL, downloadPath);
        if (isDownloaded) {
            companyNewsFeed.feed(downloadPath);
        }
        else {
            throw new Exception("Unable to feed news data from internet, COMPANY_NEWS_URL:" + COMPANY_NEWS_URL);
        }
    }

    public void feedCorpActionData(String downloadPath) throws Exception {
        boolean isDownloaded = corpActionFeed.download(CORP_ACTION_URL, downloadPath);
        if (isDownloaded) {
            corpActionFeed.feed(downloadPath);
        }
        else {
            throw new Exception("Unable to feed corp Action data from internet, CORP_ACTION_URL: " + CORP_ACTION_URL);
        }
    }

    public void feedCalendarData(String downloadPath) throws Exception {
        boolean isDownloaded = companyCalendarFeed.download(COMPANY_CALENDAR_URL, downloadPath);
        if (isDownloaded) {
            companyCalendarFeed.feed(downloadPath);
        }
        else {
            throw new Exception("Unable to feed Calendar data from internet, COMPANY_CALENDAR_URL: " + COMPANY_CALENDAR_URL);
        }
    }
}
