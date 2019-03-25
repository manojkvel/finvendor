package com.finvendor.api.resources.companyprofile.pricealert.service;

import com.finvendor.common.util.LogUtil;
import com.finvendor.common.util.Pair;
import com.finvendor.modelpojo.staticpojo.stockprice.StockCurrentPriceDTO;
import com.finvendor.modelpojo.staticpojo.wathlist.company.ConsumerPriceAlertDTO;
import com.finvendor.api.exception.WebApiException;
import com.finvendor.api.resources.companyprofile.pricealert.dto.ConsumerPriceAlertDetails;
import com.finvendor.api.resources.companyprofile.pricealert.dto.ConsumerPriceAlertDetailsWrapper;
import com.finvendor.api.resources.stockpriceupdate.service.StockPriceUpdateService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ConsumerPriceAlertMailService{

    @Autowired
    private StockPriceUpdateService stockPriceUpdateService;

    enum PriceDurationEnum {
        DAILY("% daily change"), WEEKLY("% daily change"), MONTHLY("% daily change"), NO_TIME_FRAME("% daily change");
        private String value;

        private PriceDurationEnum(String v) {
            this.value = v;
        }

        public String getValue() {
            return value;
        }
    }

    public ConsumerPriceAlertDetailsWrapper buildConsumerPriceAlertDetails() throws Exception {
        Map<String, List<ConsumerPriceAlertDTO>> consumerPriceAlertDTOMap = stockPriceUpdateService.fetchConsumerPriceAlert();
        if (consumerPriceAlertDTOMap != null && consumerPriceAlertDTOMap.size() == 0) {
            LogUtil.logInfo("*** Unable to send price alert As No price Alert set!!");
            ConsumerPriceAlertDetailsWrapper userCompaniesMailContent = new ConsumerPriceAlertDetailsWrapper();
            Map<String, List<ConsumerPriceAlertDetails>> consumerPriceAlertDetailsMap = new HashMap<>();
            userCompaniesMailContent.setConsumerPriceAlertDetailsMap(consumerPriceAlertDetailsMap);
            return userCompaniesMailContent;
        }
        Map<String, List<ConsumerPriceAlertDetails>> perUserConsumerPriceAlertDetailsMap = new LinkedHashMap<>();

        if (consumerPriceAlertDTOMap != null) {
            Map<String, StockCurrentPriceDTO> todaysStockPriceMap = stockPriceUpdateService.fetchAllStockCurrentPrice();
            // key is Consumer User and value is CunsumerPriceAlerDTO list also 1 Consumer
            // user can set alert for one or more than one company (1:M)
            for (Map.Entry<String, List<ConsumerPriceAlertDTO>> consumerPriceAlertDTOMapEntry : consumerPriceAlertDTOMap.entrySet()) {
                List<ConsumerPriceAlertDetails> companyMailMessageList = new ArrayList<>();
                String consumerUserName = consumerPriceAlertDTOMapEntry.getKey();
                List<ConsumerPriceAlertDTO> consumerPriceAlertDTOList = consumerPriceAlertDTOMapEntry.getValue();
                for (ConsumerPriceAlertDTO consumerPriceAlertDto : consumerPriceAlertDTOList) {
                    // get stock price for given company id
                    String companyId = consumerPriceAlertDto.getCompanyId();
                    StockCurrentPriceDTO todayStockPrice = todaysStockPriceMap.get(companyId);

                    Pair<Float, Float> todayAndYesterdayPrice = getTodayAndYesterdayPrice(todayStockPrice);
                    Float todaysClosePrice = todayAndYesterdayPrice.getElement1();
                    Float yesterdayClosePrice = todayAndYesterdayPrice.getElement2();

                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    // Daily Price hit checking
                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Pair<Float, Float> consumerMinMaxPrice = consumerMinMaxPrice(PriceDurationEnum.DAILY, consumerPriceAlertDto);
                    float priceDiffInPercentage = (todaysClosePrice - yesterdayClosePrice) * 100.00f / yesterdayClosePrice;
                    String dayPriceChange = checkPriceChange(PriceDurationEnum.DAILY, consumerMinMaxPrice, priceDiffInPercentage);

                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    // Weekly Price hit checking
                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    consumerMinMaxPrice = consumerMinMaxPrice(PriceDurationEnum.WEEKLY, consumerPriceAlertDto);
                    Float lastWeekClosePrice = getLastWeekClosePrice(companyId);
                    String weekPriceChange = "";
                    if (lastWeekClosePrice != null) {
                        priceDiffInPercentage = (todaysClosePrice - lastWeekClosePrice) * 100.00f / lastWeekClosePrice;
                        weekPriceChange = checkPriceChange(PriceDurationEnum.WEEKLY, consumerMinMaxPrice, priceDiffInPercentage);
                    }

                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    // Monthly Price hit checking
                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    consumerMinMaxPrice = consumerMinMaxPrice(PriceDurationEnum.MONTHLY, consumerPriceAlertDto);
                    Float lastMonthClosePrice = getLastMonthClosePrice(companyId);
                    String monthPriceChange = "";
                    if (lastMonthClosePrice != null) {
                        priceDiffInPercentage = (todaysClosePrice - lastMonthClosePrice) * 100.00f / lastMonthClosePrice;
                        monthPriceChange = checkPriceChange(PriceDurationEnum.MONTHLY, consumerMinMaxPrice, priceDiffInPercentage);
                    }

                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    // No time frame price hit checking
                    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    consumerMinMaxPrice = consumerMinMaxPrice(PriceDurationEnum.NO_TIME_FRAME, consumerPriceAlertDto);
                    float whenPriceWasSet = Float.parseFloat(consumerPriceAlertDto.getCmpWhenPriceAlertSet());

                    priceDiffInPercentage = (todaysClosePrice - whenPriceWasSet) * 100.00f / whenPriceWasSet;
                    String noTimeFramePriceChange = checkPriceChange(PriceDurationEnum.NO_TIME_FRAME, consumerMinMaxPrice, priceDiffInPercentage);

                    // Build Consumer PriceDetails
                    ConsumerPriceAlertDetails companyEmailContent = new ConsumerPriceAlertDetails();
                    companyEmailContent.setUserName(consumerUserName);
                    companyEmailContent.setCompanyName(consumerPriceAlertDto.getCompanyName());
                    companyEmailContent.setPriceDate(todayStockPrice.getPrice_date());

                    if (!StringUtils.isEmpty(dayPriceChange)) {
                        companyEmailContent.setTodaysCmp(todaysClosePrice);
                        companyEmailContent.setYesterdayCmp(yesterdayClosePrice);
                        companyEmailContent.setTodaysCmpInPercentage(dayPriceChange);
                    }

                    if (lastWeekClosePrice != null && !StringUtils.isEmpty(weekPriceChange)) {
                        companyEmailContent.setLastWeekCmp(lastWeekClosePrice);
                        companyEmailContent.setLastWeekCmpInPercentage(weekPriceChange);
                    }

                    if (lastMonthClosePrice != null && !StringUtils.isEmpty(monthPriceChange)) {
                        companyEmailContent.setLastMonthCmp(lastMonthClosePrice);
                        companyEmailContent.setLastMonthCmpInPercentage(monthPriceChange);
                    }

                    if (!StringUtils.isEmpty(noTimeFramePriceChange)) {
                        companyEmailContent.setCmpWhenPriceAlertWasSet(consumerPriceAlertDto.getCmpWhenPriceAlertSet());
                        companyEmailContent.setNoTimeFrameInPercentage(noTimeFramePriceChange);
                    }
                    companyMailMessageList.add(companyEmailContent);
                }
                perUserConsumerPriceAlertDetailsMap.put(consumerUserName, companyMailMessageList);
            }
        }
        return new ConsumerPriceAlertDetailsWrapper(perUserConsumerPriceAlertDetailsMap);
    }

    private String checkPriceChange(PriceDurationEnum priceDurationEnum, Pair<Float, Float> consumerMinMaxPricePair, float priceDiffInPercentage) {
        LogUtil.logInfo("checkMonthlyCMP - START");
        String priceChange = "";
        Float consumerMinPrice = consumerMinMaxPricePair.getElement1();
        Float consumerMaxPrice = consumerMinMaxPricePair.getElement2();
        if (consumerMinPrice != null) {
            if (priceDiffInPercentage < (consumerMinPrice / 100.00f)) {
                priceChange = "-" + consumerMinPrice + priceDurationEnum.getValue();
            }
            if (priceDiffInPercentage == (consumerMinPrice / 100.00f)) {
                priceChange = consumerMinPrice + priceDurationEnum.getValue();
            }
        } else {
            LogUtil.logInfo(priceDurationEnum.name() + " Min Price alert was not set by consumer!!");
        }
        if (consumerMaxPrice != null) {
            if (priceDiffInPercentage > (consumerMaxPrice / 100.00f)) {
                priceChange = "+" + consumerMaxPrice + priceDurationEnum.getValue();
            }
            if (priceDiffInPercentage == (consumerMaxPrice / 100.00f)) {
                priceChange = consumerMaxPrice + priceDurationEnum.getValue();
            }
        } else {
            LogUtil.logInfo(priceDurationEnum.name() + " Max Price alert was not set by consumer!!");
        }
        return priceChange;
    }

    private Pair<Float, Float> getTodayAndYesterdayPrice(StockCurrentPriceDTO stockCurrentPrice) {
        Float todaysClosePrice = 0.0f;
        Float yesterdayClosePriceAsLTP = 0.0f;
        if (stockCurrentPrice != null) {
            String todaysClosePriceFromDb = stockCurrentPrice.getClose_price();
            if (todaysClosePriceFromDb != null && !todaysClosePriceFromDb.isEmpty()) {
                todaysClosePrice = Float.parseFloat(todaysClosePriceFromDb);
            } else {
                throw new WebApiException("Unable to find today's close price from DB!!");
            }

            String yesterdayClosePriceAsLTPFromDb = stockCurrentPrice.getLast_traded_price();
            if (yesterdayClosePriceAsLTPFromDb != null && !yesterdayClosePriceAsLTPFromDb.isEmpty()) {
                yesterdayClosePriceAsLTP = Float.parseFloat(yesterdayClosePriceAsLTPFromDb);
            } else {
                throw new WebApiException("Unable to find yesterday's close price from DB!!");
            }
        }
        return new Pair<Float, Float>(todaysClosePrice, yesterdayClosePriceAsLTP);
    }

    private Pair<Float, Float> consumerMinMaxPrice(PriceDurationEnum priceDurationEnum,
                                                   ConsumerPriceAlertDTO consumerPriceAlertDto) {
        Pair<Float, Float> consumerMinMaxPricePair = new Pair<>();
        switch (priceDurationEnum) {
            case DAILY:
                String consumerDayMinPrice = consumerPriceAlertDto.getDayMinPrice();
                String consumerDayMaxPrice = consumerPriceAlertDto.getDayMaxPrice();
                if (StringUtils.isNotEmpty(consumerDayMinPrice)) {
                    consumerMinMaxPricePair.setElement1(Float.parseFloat(consumerDayMinPrice));
                } else {
                    consumerMinMaxPricePair.setElement1(null);
                }

                if (StringUtils.isNotEmpty(consumerDayMaxPrice)) {
                    consumerMinMaxPricePair.setElement2(Float.parseFloat(consumerDayMaxPrice));
                } else {
                    consumerMinMaxPricePair.setElement2(null);
                }

                break;
            case MONTHLY:
                String consumerWeekMinPrice = consumerPriceAlertDto.getWeekMinPrice();
                String consumerWeekMaxPrice = consumerPriceAlertDto.getWeekMaxPrice();
                if (StringUtils.isNotEmpty(consumerWeekMinPrice)) {
                    consumerMinMaxPricePair.setElement1(Float.parseFloat(consumerWeekMinPrice));
                } else {
                    consumerMinMaxPricePair.setElement1(null);
                }

                if (StringUtils.isNotEmpty(consumerWeekMaxPrice)) {
                    consumerMinMaxPricePair.setElement2(Float.parseFloat(consumerWeekMaxPrice));
                } else {
                    consumerMinMaxPricePair.setElement2(null);
                }

                break;
            case WEEKLY:
                String consumerMonthMinPrice = consumerPriceAlertDto.getMonthMinPrice();
                String consumerMonthMaxPrice = consumerPriceAlertDto.getMonthMaxPrice();
                if (StringUtils.isNotEmpty(consumerMonthMinPrice)) {
                    consumerMinMaxPricePair.setElement1(Float.parseFloat(consumerMonthMinPrice));
                } else {
                    consumerMinMaxPricePair.setElement1(null);
                }

                if (StringUtils.isNotEmpty(consumerMonthMaxPrice)) {
                    consumerMinMaxPricePair.setElement2(Float.parseFloat(consumerMonthMaxPrice));
                } else {
                    consumerMinMaxPricePair.setElement2(null);
                }

                break;
            case NO_TIME_FRAME:
                String consumerNoTimeFrameMinPrice = consumerPriceAlertDto.getNoTimeFrameMinPrice();
                String consumerNoTimeFrameMaxPrice = consumerPriceAlertDto.getNoTimeFrameMaxPrice();
                if (StringUtils.isNotEmpty(consumerNoTimeFrameMinPrice)) {
                    consumerMinMaxPricePair.setElement1(Float.parseFloat(consumerNoTimeFrameMinPrice));
                } else {
                    consumerMinMaxPricePair.setElement1(null);
                }

                if (StringUtils.isNotEmpty(consumerNoTimeFrameMaxPrice)) {
                    consumerMinMaxPricePair.setElement2(Float.parseFloat(consumerNoTimeFrameMaxPrice));
                } else {
                    consumerMinMaxPricePair.setElement2(null);
                }

                break;
            default:
                break;
        }
        return consumerMinMaxPricePair;
    }

    private Float getLastWeekClosePrice(String stockId) throws NumberFormatException, Exception {
        StockCurrentPriceDTO lastWeekPrice = stockPriceUpdateService.getLastWeekPrice(stockId);
        String close_price = lastWeekPrice.getClose_price();
        if (close_price != null && !close_price.isEmpty()) {
            return Float.parseFloat(close_price);
        } else {
            return null;
        }
    }

    private Float getLastMonthClosePrice(String stockId) throws NumberFormatException, Exception {
        StockCurrentPriceDTO lastMonthPrice = stockPriceUpdateService.getLastMonthPrice(stockId);
        String close_price = lastMonthPrice.getClose_price();
        if (close_price != null && !close_price.isEmpty()) {
            return Float.parseFloat(close_price);
        } else {
            return null;
        }
    }

    public String getIsinCode(String companyId) throws Exception {
        return stockPriceUpdateService.findIsinFromDb(companyId);
    }
}
