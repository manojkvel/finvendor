package com.finvendor.api.resources.admin.controller;

import com.finvendor.common.util.ErrorUtil;
import com.finvendor.serverwebapi.resources.WebUriConstants;
import com.finvendor.serverwebapi.resources.admin.dao.DataFeedDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finvendor.common.exception.ExceptionEnum.MARKETS_PERSIST;

@RestController
@RequestMapping(WebUriConstants.BASE_URI)
public class DataFeedController {
    private static final Logger logger = LoggerFactory.getLogger(DataFeedController.class.getName());

    @Autowired
    DataFeedDao dao;

    @GetMapping(value = "/persist/indexcompdetails")
    public ResponseEntity<?> insertIndexCompanyDetails() {
        try {
            String filePath="d:\\tmp\\CompanyIndexMapping.xlsx";
            int persistCount = dao.insertIndexCompanyDetails(filePath);
            logger.info("Total record inserted: {}", persistCount);
            return new ResponseEntity<>("Bhav Copy file persisted into finvendor database successfully with total prices:" + persistCount, HttpStatus.OK);
        } catch (Exception e) {
            return ErrorUtil.getError(MARKETS_PERSIST.getCode(), MARKETS_PERSIST.getUserMessage(), e);
        }
    }
}
