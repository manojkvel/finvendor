package com.finvendor.server.common.commondao;

public final class DaoUtils {
    private DaoUtils() {
    }

    // TDB: Bit Static way, need to think for dynamic approach:ayush
    public static String getParamertizedQuery(String qry, Object[] params) {
        String query = "";

        int len = params.length;
        switch (len) {
            case 1:
                query = String.format(qry, params[0]);
                break;
            case 2:
                query = String.format(qry, params[0], params[1]);
                break;
            case 3:
                query = String.format(qry, params[0], params[1], params[2]);
                break;
            case 4:
                query = String.format(qry, params[0], params[1], params[2], params[3]);
                break;
            case 5:
                query = String.format(qry, params[0], params[1], params[2], params[3], params[4]);
                break;
            case 6:
                query = String.format(qry, params[0], params[1], params[2], params[3], params[4], params[5]);
                break;
            case 7:
                query = String.format(qry, params[0], params[1], params[2], params[3], params[4], params[5], params[6]);
                break;
            default:
                query = "";
                break;
        }
        return query;
    }
}
