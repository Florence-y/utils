package com.florence.dev.utils.time;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

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


    public static  Timestamp  getAfterNDayTimeStamp(int N, Timestamp timeStamp){
        LocalDateTime localDateTimeAfterN = timestampToLocalDatetime(timeStamp).plusDays(N);
        return localDatetimeToTimestamp(localDateTimeAfterN);
    }

    public static LocalDateTime timestampToLocalDatetime(Timestamp timestamp){
        Instant instant = Instant.ofEpochMilli(timestamp.getTime());
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }


    public static Timestamp getAfterNMinuteTimeStamp(int N,Timestamp timestamp){
        LocalDateTime localDateTimeAfterN = timestampToLocalDatetime(timestamp).plusMinutes(N);
        return localDatetimeToTimestamp(localDateTimeAfterN);
    }

    public static Timestamp localDatetimeToTimestamp(LocalDateTime ldt){
        return new Timestamp(ldt.toInstant(ZoneOffset.of("+8")).toEpochMilli());
    }


}
