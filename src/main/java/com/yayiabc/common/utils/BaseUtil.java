package com.yayiabc.common.utils;

import java.sql.Date;

/**
 * Created by Administrator on 2016/6/1.
 */
public class BaseUtil {
    public static Date stringToSqlDate(String dateString) {
        Date date = null;
        try{
            date = Date.valueOf(dateString);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return date;
        
    }
}
