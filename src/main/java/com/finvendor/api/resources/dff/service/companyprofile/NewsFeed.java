package com.finvendor.api.resources.dff.service.companyprofile;

import com.finvendor.common.commondao.ICommonDao;
import com.finvendor.common.infra.dff.AbstractDataFeedService;
import com.finvendor.common.infra.dff.DataFeedService;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Company News DataFeedService (DFS)
 */
@Service
@Transactional
public class NewsFeed extends AbstractDataFeedService {

    private static final Logger logger = LoggerFactory.getLogger(NewsFeed.class.getName());

    @Autowired
    private ICommonDao commonDao;

    @Override
    public DataFeedService download(String url, String path) throws Exception {
        logger.info("CompanyNewsDFS::download-> url:{}", url);
        logger.info("CompanyNewsDFS::download-> path:{}", path);

        String downloadPath = path + File.separator + "companyNews.csv";
        logger.info("CompanyNewsDFS::downloadPath:{}", downloadPath);

        try {
            downloadFile(url, downloadPath);
        } catch (IOException e) {
            throw new Exception(e);
        }
        return this;
    }

    @Override
    public DataFeedService feed(String path) throws Exception {
        String line;
        File filePath = new File(path);
        File newFilePath = filePath.listFiles()[0];
        try (BufferedReader br = new BufferedReader(new FileReader(newFilePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] newsColums = line.split(COMMA);

                String ticker = newsColums[0].trim();
                ticker = StringUtils.replace(ticker, "\"", "");

                String companyName = newsColums[1].trim();
                companyName = StringUtils.replace(companyName, "\"", "");

                String subject;
                String broadcastDate;
                if (newsColums.length == 5) {
                    subject = newsColums[3].trim();
                    subject = StringUtils.replace(subject, "\"", "");

                    broadcastDate = newsColums[4].trim();
                    broadcastDate = StringUtils.replace(broadcastDate, "\"", "");

                } else {
                    subject = newsColums[3].trim() + "," + newsColums[4].trim();
                    subject = StringUtils.replace(subject, "\"", "");

                    broadcastDate = newsColums[5].trim();
                    broadcastDate = StringUtils.replace(broadcastDate, "\"", "");
                }


                SQLQuery query1 = commonDao.getNativeQuery("select * from company_news where ticker=?", new Object[]{ticker});
                List<Object[]> rows = query1.list();
                if (rows.size() == 0) {
                    String insertQuery = "insert into company_news(ticker,company_name) values(?,?)";
                    SQLQuery sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, ticker);
                    sqlQuery.setParameter(1, companyName);
                    sqlQuery.executeUpdate();
                }
                query1 = commonDao.getNativeQuery("select * from company_news_history where ticker=? and subject=? and broadcast_date=?",
                        new Object[]{ticker, subject, broadcastDate});
                rows = query1.list();
                if (rows.size() == 0) {
                    String insertQuery = "insert into company_news_history(ticker,subject,broadcast_date) values(?,?,?)";
                    SQLQuery sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, ticker);
                    sqlQuery.setParameter(1, subject);
                    sqlQuery.setParameter(2, broadcastDate);
                    sqlQuery.executeUpdate();
                }
            }
        } finally {
            cleanupDirectory(path);
        }
        return this;
    }
}
