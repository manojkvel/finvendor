package com.finvendor.api.resources.dff.service.externalfeed;

import com.finvendor.api.resources.dff.service.externalfeed.companyprofile.CompanyProfileFeed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ExternalFeedFacade {
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

    public boolean newsDownload(String downloadPath) throws Exception {
        return companyNewsFeed.download(COMPANY_NEWS_URL, downloadPath);
    }

    public void newsFeed(String downloadPath) throws Exception {
        companyNewsFeed.feed(downloadPath);
    }

    public boolean caDownload(String downloadPath) throws Exception {
        return corpActionFeed.download(CORP_ACTION_URL,downloadPath);
    }

    public void caFeed(String downloadPath) throws Exception {
        corpActionFeed.feed(downloadPath);
    }

    public boolean calDownload(String downloadPath) throws Exception {
        return companyCalendarFeed.download(COMPANY_CALENDAR_URL,downloadPath);
    }

    public void calFeed(String downloadPath) throws Exception {
        companyCalendarFeed.feed(downloadPath);
    }
}
