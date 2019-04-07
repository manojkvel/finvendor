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
import java.util.Objects;

import static com.finvendor.common.constant.AppConstant.COMMA;
import static com.finvendor.common.util.FileUtil.cleanupDirectory;
import static com.finvendor.common.util.FileUtil.downloadFile;

/**
 * Company Calendar DffFileFeed (DFS)
 */
@Service
@Transactional
public class Cal implements CompanyProfileFeed {

    private static final Logger logger = LoggerFactory.getLogger(Cal.class.getName());

    @Autowired
    private ICommonDao commonDao;

    @Override
    public boolean download(String url, String path) throws Exception {
        logger.info("CompanyCalendarDFS::download-> url:{}", url);
        logger.info("CompanyCalendarDFS::download-> path:{}", path);

        String downloadPath = path + File.separator + "companyCalendar.csv";
        logger.info("CompanyCalendarDFS::downloadPath:{}", downloadPath);

        try {
            downloadFile(url, downloadPath);
        } catch (IOException e) {
            throw new Exception(e);
        }
        return true;
    }

    @Override
    public int feed(String path) throws Exception {
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

                String purpose = newsColums[3].trim();
                purpose = StringUtils.replace(purpose, "\"", "");

                String boardMeetingDate = newsColums[4].trim();
                boardMeetingDate = StringUtils.replace(boardMeetingDate, "\"", "");

                SQLQuery query1 = commonDao.getNativeQuery("select * from company_calendar where ticker=?", new Object[] { ticker });
                List<Object[]> rows = query1.list();
                if (rows.size() == 0) {
                    String insertQuery = "insert into company_calendar(ticker,company_name) values(?,?)";
                    SQLQuery sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, ticker);
                    sqlQuery.setParameter(1, companyName);
                    sqlQuery.executeUpdate();
                }
                query1 = commonDao
                        .getNativeQuery("select * from company_calendar_history where ticker=? and purpose=? and board_meeting_date=?",
                                new Object[] { ticker, purpose, boardMeetingDate });
                rows = query1.list();
                if (rows.size() == 0) {
                    String insertQuery = "insert into company_calendar_history(ticker,purpose,board_meeting_date) values(?,?,?)";
                    SQLQuery sqlQuery = commonDao.getNativeQuery(insertQuery, null);
                    sqlQuery.setParameter(0, ticker);
                    sqlQuery.setParameter(1, purpose);
                    sqlQuery.setParameter(2, boardMeetingDate);
                    sqlQuery.executeUpdate();
                }

            }
        } finally {
            cleanupDirectory(path);
        }
        return 0;
    }
}
