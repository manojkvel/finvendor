package com.finvendor.api.stockpriceupdate.controller;

import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.stockpriceupdate.service.StockPriceUpdateService;
import com.finvendor.api.user.service.UserServiceImpl;
import com.finvendor.common.util.ErrorUtil;
import com.finvendor.common.util.LogUtil;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Properties;

@Controller
@RequestMapping(value = "/system/api")
public class StockPriceUpdateController {

    @Autowired
    private StockPriceUpdateService stockPriceUpdateService;

    private DateFormat bhavDateFormatFromNSESite = new SimpleDateFormat("dd-MMM-yyyy");
    private Format fvDateFormat = new SimpleDateFormat("MM/dd/yy");

    @Autowired
    private UserServiceImpl userService;

    @Resource(name = "finvendorProperties")
    private Properties fvProperties;

    @RequestMapping(value = "/updatestockpricebyvendor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStockPriceByVendor(@RequestBody StockCurrentPriceDTO stockCurrentPriceDTO)
            throws WebApiException {
        LogUtil.logInfo("-- nUpdate Stock Price  (NSE) - START-- ");
        return null;
    }

    @RequestMapping(value = "/updatestockpricebybhavcopy", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateStockPriceByBhavCopy() throws WebApiException {
        try {
            stockPriceUpdateService.updateStockPrice();
            return new ResponseEntity<>("{\"priceUpdateStatus\":\"Today's NSE Price updated successfully\"}", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            ErrorUtil.logError("*** UpdateStockPrice(...) method", e);
            System.out.println("\n\n Ghrrrrrrr.......UNABLE TO UPDATE STOCK PRICE ON HOLIDAY !!!");
            return new ResponseEntity<>("{\"priceUpdateStatus\":\"Ghrrrrrrr.......UNABLE TO UPDATE STOCK PRICE ON HOLIDAY !!!\"}", HttpStatus.FORBIDDEN);
        }
    }

    @RequestMapping(value = "/updatecompanydescription", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateCompanyDescription() throws WebApiException {
        try {
            stockPriceUpdateService.updateCompanyDescription();
            return new ResponseEntity<>(true, HttpStatus.OK);
        } catch (Exception e) {
            ErrorUtil.logError("*** UpdateStockPrice(...) method", e);
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

}
