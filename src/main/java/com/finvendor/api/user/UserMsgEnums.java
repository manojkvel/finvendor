package com.finvendor.api.user;

import com.finvendor.common.util.DateUtils;

import java.text.MessageFormat;
import java.util.Calendar;

public enum UserMsgEnums {
    USER_IN_TRIAL_PERIOD("TRIAL PERIOD - [START: {0} - END: {1}]"),
    SUBSCRIPTION_TYPE_FREE("Subscription type: FREE"),
    SUBSCRIPTION_TYPE_SMART_ACTIVE("Subscription type: SMART - [ACTIVE until {0}]"),
    SUBSCRIPTION_TYPE_SMART_PENDING("Subscription type: SMART - [PENDING]"),
    SUBSCRIPTION_TYPE_SAGE_ACTIVE("Subscription type: SAGE - [ACTIVE until {0}]"),
    SUBSCRIPTION_TYPE_SAGE_PENDING("Subscription type: SAGE - [PENDING]")

    ;
    private String msg;

    UserMsgEnums(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public String getMsg(Object[] obj) {
        return MessageFormat.format(msg, obj);
    }

    public static void main(String[] args) {
        String date_to_dd_mmm_yyyy_hh_format = DateUtils.get_Date_To_DD_MMM_YYYY_hh_Format(Calendar.getInstance().getTimeInMillis());
        String msg = UserMsgEnums.USER_IN_TRIAL_PERIOD.getMsg(new Object[] { date_to_dd_mmm_yyyy_hh_format,date_to_dd_mmm_yyyy_hh_format });
        System.out.println(msg);
    }
}
