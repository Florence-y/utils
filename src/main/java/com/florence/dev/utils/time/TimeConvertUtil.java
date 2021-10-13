package com.florence.dev.utils.time;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.*;

/**
 * @author wuyanzhen
 */
public class TimeConvertUtil {


    private static final String yyyyMMddHH = "yyyy-MM-dd HH";

    private static final String yyyyMMdd = "yyyy-MM-dd";

    private static final String yyyyMM = "yyyy-MM";

    private static final String yyyyMMddHHmmssSSS1 = "yyyyMMddHHmmssSSS";

    private static final String yyyyMMddHHmmssSSS2 = "yyyy-MM-dd HH:mm:ss.SSS";

    private static final String yyyyMMddHHmmss1 = "yyyy-MM-dd HH:mm:ss";

    private static final String yyyyMMddHHmmss2 = "yyyyMMddHHmmss";


    public static String timeLongToString(long time, String pattern) {
        return DateFormatUtils.format(time, pattern);
    }


    public static  Timestamp  getAfterNDayTimestamp(int N, Timestamp timeStamp){
        LocalDateTime localDateTimeAfterN = timestampToLocalDatetime(timeStamp).plusDays(N);
        return localDatetimeToTimestamp(localDateTimeAfterN);
    }

    public static LocalDateTime timestampToLocalDatetime(Timestamp timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }


    public static Timestamp getAfterNMinuteTimestamp(int N,Timestamp timestamp){
        LocalDateTime localDateTimeAfterN = timestampToLocalDatetime(timestamp).plusMinutes(N);
        return localDatetimeToTimestamp(localDateTimeAfterN);
    }

    public static Timestamp localDatetimeToTimestamp(LocalDateTime ldt){
        return new Timestamp(ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }

    public static long currentTimeInMills() {
        return System.currentTimeMillis();
    }

    public static long currentTimeInSeconds() {
        return currentTimeInMills() / 1000;
    }

    /**
     * 返回LocalDateTime形式的当前时间
     *
     * @return
     */
    public static long currentLocalDateTime() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }

    public static int daysBetween(long currTimeMills1, long currTimeMills2) {
        if (currTimeMills1 > currTimeMills2)
            return 0;
        long currTimeMills = currTimeMills2 - currTimeMills1;
        Long daysBetween = Math.abs(currTimeMills / (24 * 60 * 60 * 1000));
        return daysBetween.intValue();
    }

    /**
     * @param months
     * @return
     */
    public static long addMonths(long localDateTimeLong, int months) {
        LocalDateTime localDateTime =
                LocalDateTime.ofEpochSecond(localDateTimeLong, 0, ZoneOffset.UTC);
        localDateTime.plusMonths(months);
        return localDateTime.toEpochSecond(ZoneOffset.UTC);
    }

    public static Long addThirteenMonth(long timeInMills) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(timeInMills);
        calendar.add(Calendar.MONTH, 13);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static Long getLastDayOfLastMonth(long timeInMills) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(timeInMills);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public static long operateMonth(long timeInMills, int months) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(timeInMills);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTimeInMillis();
    }

    public static long subtractMonths(long timeInMills, int months) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(timeInMills);
        calendar.add(Calendar.MONTH, -months);
        return calendar.getTimeInMillis();
    }

    public static long subtractDays(long timeInMills, int days) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(timeInMills);
        calendar.add(Calendar.DAY_OF_YEAR, -days);
        return calendar.getTimeInMillis();
    }

    public static long currentDayOfWeek(long currentTimeInMills) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(currentTimeInMills);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    public static long currentDayOfMonth(long currentTimeInMills) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(currentTimeInMills);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    public static List<Long> findTimeInMillsList(long startTime, long endTime) {
        Date beginDate = new Date();
        beginDate.setTime(startTime);
        Date endDate = new Date();
        endDate.setTime(endTime);

        List<Long> timeInMillsList = new ArrayList<>();
        timeInMillsList.add(startTime);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(beginDate);
        // 测试此日期是否在指定日期之后
        while (endDate.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            timeInMillsList.add(calBegin.getTime().getTime());
        }
        timeInMillsList.add(endTime);
        return timeInMillsList;
    }

    public static List<Integer> findDateIntList(long startTime, long endTime) {
        Date beginDate = new Date();
        beginDate.setTime(startTime);
        Date endDate = new Date();
        endDate.setTime(endTime);

        List<Integer> dateIntList = new ArrayList<>();
        String startDateStr = toDateStr(beginDate, "yyyyMMdd");
        dateIntList.add(Integer.valueOf(startDateStr));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(beginDate);
        // 测试此日期是否在指定日期之后
        while (endDate.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            dateIntList.add(Integer.valueOf(toDateStr(calBegin.getTime(), "yyyyMMdd")));
        }
        String endDateStr = toDateStr(endDate, "yyyyMMdd");
        dateIntList.add(Integer.valueOf(endDateStr));
        return dateIntList;
    }

    public static int getSecondOfDay(int day) {
        return day * 24 * 60 * 60;
    }

    public static Long addSpecifyDay(long timeInMills, int days) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(timeInMills);
        calendar.add(Calendar.DAY_OF_YEAR, days);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }


    /**
     * 日期对象转字符串
     *
     * @param date
     * @param formate
     * @return
     */
    public static String toDateStr(Date date, String formate) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(formate);
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 将日期转成字符串
     *
     * @param date {@link java.sql.Timestamp} 对象
     * @return 格式化的字符串或者空字符串
     */
    public static String toDateStr(Timestamp date) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 将日期转成字符串
     *
     * @param date   {@link java.sql.Timestamp} 对象
     * @param format format的样式
     * @return 格式化的字符串或者空字符串
     */
    public static String toDateStr(Timestamp date, String format) {
        if (date != null) {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(date);
        }
        return "";
    }

    /**
     * 将字符串转化成日期
     *
     * @param str 日期字符串
     * @return {@link Timestamp}对象或者null
     * @throws ParseException 转换错误
     */
    public static Timestamp parseDate(String str) throws ParseException {
        if (StringUtils.isNotBlank(str)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return new Timestamp(sdf.parse(str).getTime());
        }
        return null;
    }


    /**
     * 根据日期格式对日期转换字符串
     *
     * @param format 日期格式
     * @param date   日期
     * @return 当前日期格式的字符串
     */
    public static String getFormatStringFromDate(String format, Date date) {
        return new SimpleDateFormat(format).format(date);
    }

    public static Long addSpecifyMinute(long timeInMills, int minute) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeInMillis(timeInMills);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTimeInMillis();
    }



}
