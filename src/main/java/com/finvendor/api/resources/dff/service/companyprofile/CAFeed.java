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
import java.util.Objects;

/**
 * Corporate Action DataFeedService (DFS)
 */
@Service
@Transactional
public class CAFeed extends AbstractDataFeedService {

    private static final Logger logger = LoggerFactory.getLogger(CAFeed.class.getName());

    @Autowired
    private ICommonDao commonDao;

    @Override
    public DataFeedService download(String url, String path) throws Exception {
        logger.info("CoprActionDFS::download-> url:{}", url);
        logger.info("CoprActionDFS::download-> path:{}", path);

        String downloadPath = path + File.separator + "corpAction.csv";
        logger.info("CoprActionDFS::downloadPath:{}", downloadPath);

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
        File newFilePath = Objects.requireNonNull(filePath.listFiles())[0];
        try (BufferedReader br = new BufferedReader(new FileReader(newFilePath))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] newsColums = line.split(COMMA);
                String ticker = newsColums[0].trim();
                ticker = StringUtils.replace(ticker, "\"", "");

                String companyName = newsColums[1].trim();
                companyName = StringUtils.replace(companyName, "\"", "");

                String faceValue = newsColums[4].trim();
                faceValue = StringUtils.replace(faceValue, "\"", "");

                String purpose = newsColums[5].trim();
                purpose = StringUtils.replace(purpose, "\"", "");

                String exDate = newsColums[6].trim();
                exDate = StringUtils.replace(exDate, "\"", "");

                String recordDate = newsColums[7].trim();
                recordDate = StringUtils.replace(recordDate, "\"", "");


                SQLQuery query1 = commonDao.getNativeQuery("select * from corp_action where ticker=?", new Object[]{ticker});
                List<Object[]> rows = query1.list();
                if (rows.size() == 0) {
                    String insertQuery = "insert into corp_action(ticker,company_name) values(?,?)";
                    SQLQuery sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, ticker);
                    sqlQuery.setParameter(1, companyName);
                    sqlQuery.executeUpdate();
                }
                query1 = commonDao.getNativeQuery("select * from corp_action_history where ticker=? and purpose=? and face_value=? and ex_date=? and record_date=?",
                        new Object[]{ticker, purpose, faceValue, exDate, recordDate});
                rows = query1.list();
                if (rows.size() == 0) {
                    String insertQuery = "insert into corp_action_history(ticker,purpose,face_value,ex_date,record_date) values(?,?,?,?,?)";
                    SQLQuery sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, ticker);
                    sqlQuery.setParameter(1, purpose);
                    sqlQuery.setParameter(2, faceValue);
                    sqlQuery.setParameter(3, exDate);
                    sqlQuery.setParameter(4, recordDate);
                    sqlQuery.executeUpdate();
                }
            }
        } finally {
            cleanupDirectory(path);
        }
        return this;
    }
}
