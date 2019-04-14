package com.finvendor.api.resources.dff.service.externalfeed.companyprofile;

import com.finvendor.common.commondao.ICommonDao;
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

import static com.finvendor.common.constant.AppConstant.COMMA;
import static com.finvendor.common.util.FileUtil.downloadFile;

/**
 * Company News DffFileFeed (DFS)
 */
@Service
@Transactional
public class CompanyNewsFeed implements CompanyProfileFeed {

    private static final Logger logger = LoggerFactory.getLogger(CompanyNewsFeed.class.getName());

    @Autowired
    private ICommonDao commonDao;

    @Override
    public boolean download(String url, String path) throws Exception {
        logger.info("CompanyNewsFeed::download()-> url:{}", url);
        logger.info("CompanyNewsFeed::download()-> path:{}", path);

        String downloadPath = path + File.separator + "companyNews.csv";
        logger.info("CompanyNewsFeed::download()-> downloadPath:{}", downloadPath);

        try {
            downloadFile(url, downloadPath);
        } catch (IOException e) {
            throw new Exception("Error has occured while downloading Company News from URL ", e);
        }
        return true;
    }

    @Override
    public int feed(String path) throws Exception {
        logger.info("CompanyNewsFeed::feed()-> path: {}", path + File.separator + "companyNews.csv");
        String line;
        File filePath = new File(path + File.separator + "companyNews.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
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

                }
                else {
                    subject = newsColums[3].trim() + "," + newsColums[4].trim();
                    subject = StringUtils.replace(subject, "\"", "");

                    broadcastDate = newsColums[5].trim();
                    broadcastDate = StringUtils.replace(broadcastDate, "\"", "");
                }

                SQLQuery query1 = commonDao.getNativeQuery("select * from company_news where ticker=?", new Object[] { ticker });
                List<Object[]> rows = query1.list();
                if (rows.size() == 0) {
                    String insertQuery = "insert into company_news(ticker,company_name) values(?,?)";
                    SQLQuery sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, ticker);
                    sqlQuery.setParameter(1, companyName);
                    sqlQuery.executeUpdate();
                }
                query1 = commonDao.getNativeQuery("select * from company_news_history where ticker=? and subject=? and broadcast_date=?",
                        new Object[] { ticker, subject, broadcastDate });
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
        }
        return 0;
    }
}
