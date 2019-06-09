package com.example.demo.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhaozh01
 * on 2018/1/8.
 */
public class DateUtil {

    /**
     * 是否是日期字符串
     */
    private static boolean isDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(dateStr);
            return true;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 字符串转日期
     */
    public static Date parseDate(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 给定日期字符串n天前的日期
     */
    public static String getDateStr(String dateStr, int n) {
        if (isDate(dateStr)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.format(parseDate(dateStr).getTime() - n * 86400000L);
        }
        return null;
    }

    /**
     * n天前的日期
     */
    public static String getDateStr(int n) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(System.currentTimeMillis() - n * 86400000L);
    }

}
