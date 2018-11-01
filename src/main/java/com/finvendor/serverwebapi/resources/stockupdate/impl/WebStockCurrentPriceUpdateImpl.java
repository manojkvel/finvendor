//package com.finvendor.serverwebapi.resources.stockupdate.impl;
//
//import com.finvendor.common.util.ErrorUtil;
//import com.finvendor.common.util.LogUtil;
//import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
//import com.finvendor.server.stockpriceupdate.service.IStockPriceUpdateService;
//import com.finvendor.serverwebapi.exception.WebApiException;
//import com.finvendor.serverwebapi.resources.stockupdate.IWebStockCurrentPriceUpdate;
//import com.finvendor.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//
//import javax.annotation.Resource;
//import java.text.DateFormat;
//import java.text.Format;
//import java.text.SimpleDateFormat;
//import java.util.Properties;
//
//@Controller
//public class WebStockCurrentPriceUpdateImpl implements IWebStockCurrentPriceUpdate {
//
//    @Autowired
//    private IStockPriceUpdateService stockPriceUpdateService;
//
//    private DateFormat bhavDateFormatFromNSESite = new SimpleDateFormat("dd-MMM-yyyy");
//    private Format fvDateFormat = new SimpleDateFormat("MM/dd/yy");
//
//    @Resource(name = "userService")
//    private UserService userService;
//
//    @Resource(name = "finvendorProperties")
//    private Properties fvProperties;
//
//    @Override
//    public ResponseEntity<?> updateStockPriceByVendor(@RequestBody StockCurrentPriceDTO stockCurrentPriceDTO)
//            throws WebApiException {
//        LogUtil.logInfo("-- nUpdate Stock Price  (NSE) - START-- ");
//        return null;
//    }
//
//    @Override
//    public ResponseEntity<?> updateStockPriceByBhavCopy() throws WebApiException {
//        try {
//            stockPriceUpdateService.updateStockPrice();
//            return new ResponseEntity<>("{\"priceUpdateStatus\":\"Today's NSE Price updated successfully\"}", HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            ErrorUtil.logError("*** UpdateStockPrice(...) method", e);
//            System.out.println("\n\n Ghrrrrrrr.......UNABLE TO UPDATE STOCK PRICE ON HOLIDAY !!!");
//            return new ResponseEntity<>("{\"priceUpdateStatus\":\"Ghrrrrrrr.......UNABLE TO UPDATE STOCK PRICE ON HOLIDAY !!!\"}", HttpStatus.FORBIDDEN);
//        }
//    }
//
//    @Override
//    public ResponseEntity<?> updateCompanyDescription() throws WebApiException {
//        try {
//            stockPriceUpdateService.updateCompanyDescription();
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        } catch (Exception e) {
//            ErrorUtil.logError("*** UpdateStockPrice(...) method", e);
//            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
//        }
//    }
//
//}
