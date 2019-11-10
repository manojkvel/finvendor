package com.finvendor.api.common;

import com.finvendor.api.report.service.pdfcontent.MarketDataPDFContentBuilder;
import com.finvendor.common.util.Pair;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Common Algorithm from finvendor
 *
 * @author ayush on 10/11/2019
 */
public final class CommonAlgorithm {
    private static final Logger LOG = LoggerFactory.getLogger(MarketDataPDFContentBuilder.class.getName());
    private CommonAlgorithm() {
    }

    /**
     * Find Nifty50 message for market report based on 3 consecutive nifty50 close price
     *
     * IF d1_closePrice > d2_closePrice > d3_closePrice
     * THEN
     *      message = "This is 3rd consecutive day when NIFTY closed higher"
     * ELSE IF d1_closePrice < d2_closePrice < d3_closePrice
     *      message = "This is 3rd consecutive day when NIFTY closed lower"
     * ELSE
     *      IF d1_closePrice > d2_closePrice < d3_closePrice
     *      THEN
     *          message = "Nifty fond its momentum today, it closed higher "
     *       ELSE
     *          message = "Nifty lost its momentum today, it closed lower"
     *       ENDIF
     *  ENDIF
     */
    public static String findNifty503DaysClosePriceMessage(List<Float> last3DaysNifty50ClosePriceList) {
        LOG.info("## findNifty50ConsecutiveMessage START last3DaysNifty50ClosePriceList: {}", last3DaysNifty50ClosePriceList);
        String msg="";
        Float todaysClosePrice = last3DaysNifty50ClosePriceList.get(0);
        Float yesterdaysClosePrice = last3DaysNifty50ClosePriceList.get(1);
        Float dayBeforeYesterdayClosePrice = last3DaysNifty50ClosePriceList.get(2);
        LOG.info("## todaysClosePrice:{}", todaysClosePrice);
        LOG.info("## yesterdaysClosePrice:{}", yesterdaysClosePrice);
        LOG.info("## dayBeforeYesterdayClosePrice:{}", dayBeforeYesterdayClosePrice);
        if (todaysClosePrice > yesterdaysClosePrice && yesterdaysClosePrice > dayBeforeYesterdayClosePrice) {
            msg = "This is 3rd consecutive session when NIFTY closed higher";
        }
        else if (todaysClosePrice < yesterdaysClosePrice && yesterdaysClosePrice < dayBeforeYesterdayClosePrice) {
            msg = "This is 3rd consecutive session when NIFTY closed lower";
        }
        else {
            if (todaysClosePrice > yesterdaysClosePrice && yesterdaysClosePrice < dayBeforeYesterdayClosePrice) {
                msg = "Nifty fond its momentum today, it closed higher ";
            }
            else {
                msg = "Nifty lost its momentum today, it closed lower";
            }
        }
        return msg;
    }

    public static Pair<String, String> getIndexClosedByUpOrDownValue(String pointChangeStr) throws IOException {
        String isUpOrDown;
        if (StringUtils.isNotEmpty(pointChangeStr)) {
            float pointChange = Float.parseFloat(pointChangeStr);
            isUpOrDown = pointChange > 0.0F ? "Up" : "Down";
        }
        else {
            isUpOrDown = "N/A";
        }
        return new Pair<>(isUpOrDown, pointChangeStr);
    }

    public static String getNifty50MappingKeyword(String percentChangeStr) throws IOException {
        String nifty50IndexValue = "N/A";
        if (StringUtils.isNotEmpty(percentChangeStr)) {
            float percentChange = Float.parseFloat(percentChangeStr);
            if (percentChange > 0.25f && percentChange < 1.5f) {
                nifty50IndexValue = "CLOSED HIGHER";
            }
            else if (percentChange > -1.5f && percentChange < -0.25f) {
                nifty50IndexValue = "REMAINED SUBDUED";
            }
            else if (percentChange > -1.5f && percentChange < 0.25f) {
                nifty50IndexValue = "CLOSED FLAT";
            }
            else if (percentChange > 1.5f) {
                nifty50IndexValue = "ENDED SHARPLY HIGHER";
            }
            else if (percentChange < 1.5f) {
                nifty50IndexValue = "PLUNGED SHARPLY";
            }
        }
        return nifty50IndexValue;
    }
}
