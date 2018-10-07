package com.finvendor.serverwebapi.resources.bhavprice;

import com.finvendor.common.util.DateUtil;
import com.finvendor.serverwebapi.exception.WebApiException;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

@Controller
public class WebBhavPriceImpl implements IWebBhavPrice {

    @Resource(name = "finvendorProperties")
    private Properties finvendorProperties;
    @Override
    public ResponseEntity<?> getBhavPriceRecordStats(@RequestParam("type") String type,
                                                     @RequestParam("type") String perPageMaxRecords) throws WebApiException {
        return null;
    }

    @Override
    public ResponseEntity<?> getBhavPrices(@RequestParam("type") String type,
                                           @RequestParam("order") String order,
                                           @RequestParam("pageNumber") String pageNumber,
                                           @RequestParam("perPageMaxRecords") String perPageMaxRecords) throws WebApiException {
        return null;
    }

    @Override
    public ResponseEntity<?> uploadBhavPrice(HttpServletRequest request, HttpServletResponse response) throws WebApiException {
        String bhavCopyPriceUrl = getBhavCopyPriceUrl();
        return null;
    }

    private String getBhavCopyPriceUrl() {
        String bhavCopyPriceUrl = finvendorProperties.getProperty("nse_bhav_copy_price_url");
        String dayNumber = DateUtil.getDayNumber();
        String threeLetterMonthName = DateUtil.getThreeLetterMonthName();
        String year = DateUtil.getYear();
        bhavCopyPriceUrl= StringUtils.replace(bhavCopyPriceUrl, "$DAY", dayNumber);
        bhavCopyPriceUrl=StringUtils.replace(bhavCopyPriceUrl, "$MON", threeLetterMonthName);
        bhavCopyPriceUrl=StringUtils.replace(bhavCopyPriceUrl, "$YEAR", year);
        return bhavCopyPriceUrl;
    }


}
