package com.humorboy.practise.utils;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtils {
//    private static Long minutes;
//
//    @Value("${sys.minutes}")
//    public void setMinutes(Long minutes) {
//        this.minutes = minutes;
//    }

    public static String getCurrentTime() {
        return LocalDateTime.now(ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    public static String getCurrentMonth() {
        return LocalDateTime.now(ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyy-MM"));
    }
    public static String getCurrentMonth1() {
        return LocalDateTime.now(ZoneId.of("+8")).format(DateTimeFormatter.ofPattern("yyyyMM"));
    }

    /**
     * 获取token过期日期
     *
     * @return
     */
//    public static Date getExpiresAt() {
//        LocalDateTime localDate = LocalDateTime.now();
//        LocalDateTime plusDays = localDate.plusMinutes(minutes);
//        ZonedDateTime zonedDateTime = plusDays.atZone(ZoneId.systemDefault());
//        return Date.from(zonedDateTime.toInstant());
//    }
}
