package com.florence.dev.utils.time;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.rmi.UnexpectedException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author wuyanzhen
 */
public class CustomDateFormatUtils {


    private static final String yyyyMMddHH = "yyyy-MM-dd HH";

    private static final String yyyyMMdd = "yyyy-MM-dd";

    private static final String yyyyMM = "yyyy-MM";

    private static final String yyyyMMddHHmmssSSS1 = "yyyyMMddHHmmssSSS";

    private static final String yyyyMMddHHmmssSSS2 = "yyyy-MM-dd HH:mm:ss.SSS";

    private static final String yyyyMMddHHmmss1 = "yyyy-MM-dd HH:mm:ss";

    private static final String yyyyMMddHHmmss2 = "yyyyMMddHHmmss";

    /**
     * 将字符串日期转换为毫秒数
     *
     * @param time
     * @param pattern
     * @return
     */
    public static long timeStrToLong(String time, String pattern) throws UnexpectedException {
        DateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(time).getTime();
        } catch (ParseException e) {
            throw new UnexpectedException(e.getMessage());
        }
    }

    /**
     * 将Long日期转换为String日期
     *
     * @param time
     * @return
     */
    public static String timeLongToString(long time, String pattern) {
        return DateFormatUtils.format(time, pattern);
    }

    /**
     * 将yyyy-MM-dd时间转成yyyyMMdd形式
     *
     * @param time
     * @return
     */
    public static int parseLocalDate(String time) {
        time = time.replaceAll("-", "");
        return Integer.valueOf(time);
    }

    /**
     * 将yyyyMMdd时间转成yyyy-MM-dd时间
     *
     * @param time
     * @return
     */
    public static String parseLocalDateToString(int time) {
        String timeStr = String.valueOf(time);
        if (timeStr.length() != 8) {
            return "";
        }

        StringBuilder sb = new StringBuilder(timeStr);
        sb.insert(4, "-");
        sb.insert(7, "-");

        return sb.toString();
    }

    /**
     * 计算缓存中的过期时间
     *
     * @return
     */
    public static int computeExpireTimeInCache(long putOffTime) {
        long currentTime = System.currentTimeMillis();
        return (int) ((putOffTime - currentTime) / 1000);
    }

    public static String getyyyyMMddStr(long timeInMills) throws UnexpectedException {
        if (timeInMills <= 0) {
            throw new UnexpectedException("timeInMills illegal");
        }
        return DateFormatUtils.format(timeInMills, yyyyMMdd);
    }

    public static String getyyyyMMddHHStr(long timeInMills) throws UnexpectedException {
        if (timeInMills <= 0) {
            throw new UnexpectedException("timeInMills illegal");
        }
        return DateFormatUtils.format(timeInMills, yyyyMMddHH);
    }

    public static String getyyyyMMStr(long timeInMills) throws UnexpectedException {
        if (timeInMills <= 0) {
            throw new UnexpectedException("timeInMills illegal");
        }
        return DateFormatUtils.format(timeInMills, yyyyMM);
    }

    public static String getyyyyMMddHHmmssSSS1Str(long timeInMills) throws UnexpectedException {
        if (timeInMills <= 0) {
            throw new UnexpectedException("timeInMills illegal");
        }
        return DateFormatUtils.format(timeInMills, yyyyMMddHHmmssSSS1);
    }

    public static long getyyyyMMddHHmmssSSSLong(long timeInMills) throws UnexpectedException {
        return Long.parseLong(getyyyyMMddHHmmssSSS1Str(timeInMills));
    }

    public static String getyyyyMMddHHmmssSSS2Str(long timeInMills) throws UnexpectedException {
        if (timeInMills <= 0)
            throw new UnexpectedException("timeInMills illegal");
        return DateFormatUtils.format(timeInMills, yyyyMMddHHmmssSSS2);
    }

    public static String getyyyyMMddHHmmssStr1(long timeInMills) throws UnexpectedException {
        if (timeInMills <= 0)
            throw new UnexpectedException("timeInMills illegal");
        return DateFormatUtils.format(timeInMills, yyyyMMddHHmmss1);
    }

    public static String getyyyyMMddHHmmssStr2(long timeInMills) throws UnexpectedException {
        if (timeInMills <= 0)
            throw new UnexpectedException("timeInMills illegal");
        return DateFormatUtils.format(timeInMills, yyyyMMddHHmmss2);
    }

    public static int getyyyyMMddInt(long timeInMills) throws UnexpectedException {
        return Integer.parseInt(getyyyyMMddStr(timeInMills).replace("-", ""));
    }

    public static int getyyyyMMInt(long timeInMills) throws UnexpectedException {
        return Integer.parseInt(getyyyyMMStr(timeInMills).replace("-", ""));
    }

    public static int getLocalTimeStrToLong(String localTime, String pattern) {
        LocalTime resultTime = LocalTime.parse(localTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        return resultTime.toSecondOfDay();
    }

    public static String getLocalTimeLongToStr(int localTime, String pattern) {
        LocalTime secondOfDay = LocalTime.ofSecondOfDay(localTime);
        return secondOfDay.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static long getLocalDateTimeStrToLong(String localDateTime, String pattern) {
        LocalDateTime time = LocalDateTime.parse(localDateTime, DateTimeFormatter.ofPattern(pattern));
        return time.toEpochSecond(ZoneOffset.UTC);
    }

    public static String getLocalDateTimeLongToStr(long localDateTime, String pattern) {
        LocalDateTime time = LocalDateTime.ofEpochSecond(localDateTime, 0, ZoneOffset.UTC);
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String getyyyyMMddStr(Date date) throws UnexpectedException {
        if (date == null) {
            throw new UnexpectedException("date illegal");
        }
        return DateFormatUtils.format(date, yyyyMMdd);
    }

    public static int getyyyyMMddInt(Date date) throws UnexpectedException {
        return Integer.parseInt(getyyyyMMddStr(date).replace("-", ""));
    }
}