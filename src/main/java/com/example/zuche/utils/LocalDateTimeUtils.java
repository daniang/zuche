package com.example.zuche.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * localDateTime 的工具类
 */
@Slf4j
public class LocalDateTimeUtils {


    /**
     * localDateTime 转时间戳   秒级别
     *
     * @param localDateTime
     * @return
     */
    public static Long localDateTimetoSecond(LocalDateTime localDateTime) {
        long epochSecond = localDateTime.toInstant(ZoneOffset.ofHours(8)).getEpochSecond();

//        long epochSecond = localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
        return epochSecond;
    }

    /**
     * localDateTime 转时间戳  毫秒级别
     * @param localDateTime
     * @return
     */
    public static Long localDateTimeToMilliSeconds(LocalDateTime localDateTime) {

        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 时间戳转localDateTime  秒级别
     * @param second
     * @return
     */
    public static LocalDateTime secondToLocalDateTime(Long second) {
        return LocalDateTime.ofEpochSecond(second, 0, ZoneOffset.ofHours(8));
    }

    /**
     * 时间戳转localDateTime  毫秒级别     一个是精确的有小数点后三位的
     * @param millSecond
     * @return
     */
    public static LocalDateTime millSecondTOLocalDateTime(Long millSecond){

//        return  Instant.ofEpochMilli(millSecond).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();

        return LocalDateTime.ofEpochSecond(millSecond / 1000, 0, ZoneOffset.ofHours(8));

    }

    /**
     * 时间戳转localDate 毫秒级别
     * @param millsSecond
     * @return
     */
    public static LocalDate millSecondToLocalDate(Long millsSecond){

        return Instant.ofEpochMilli(millsSecond).atZone(ZoneOffset.ofHours(8)).toLocalDate();

    }

    /**
     * 时间戳转localDate  秒级别
     * @param second
     * @return
     */
    public static LocalDate secondToLocalDate(Long second){

        return Instant.ofEpochSecond(second).atZone(ZoneOffset.ofHours(8)).toLocalDate();

    }

    /**
     * localDate 转时间戳  毫秒级别
     * @param localDate
     * @return
     */
    public static Long localDateToMillSecond(LocalDate localDate){

        return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();

    }

    /**
     * localDate 转时间戳 秒级别
     * @param localDate
     * @return
     */
    public static Long localDateToSecond(LocalDate localDate){

        return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().getEpochSecond();

    }

    /**
     * date 转localDateTime  会有小数点
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
        return date.toInstant().atOffset(ZoneOffset.ofHours(8)).toLocalDateTime();
    }


    /**
     * date转 localDateTime 精确到秒 。无小数点
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime1(Date date){
        long second = date.toInstant().atOffset(ZoneOffset.ofHours(8)).toEpochSecond();
        LocalDateTime localDateTime = LocalDateTime.ofEpochSecond(second, 0, ZoneOffset.ofHours(8));
        return localDateTime;
    }

    /**
     * localDateTime 转date
     * @param localDateTime
     * @return
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime){
//        Instant instant1 = localDateTime.atZone(ZoneOffset.ofHours(8)).toInstant();
        Instant instant = Instant.ofEpochSecond(localDateTime.toEpochSecond(ZoneOffset.ofHours(8)));
        Date from = Date.from(instant);
        return from;

    }

    /**
     * localDate 转date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate){
        Instant instant = localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant();
        return Date.from(instant);
    }

    /**
     * date 转 localDate
     * @param date
     * @return
     */
    public static LocalDate dateToLocalDate(Date date){

        return date.toInstant().atOffset(ZoneOffset.ofHours(8)).toLocalDate();

    }

    /**
     * localDateTime 转字符串
     * @param localDateTime
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime,String formatter){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        return localDateTime.format(dateTimeFormatter);
    }

    public static String localDateToString(LocalDate localDate,String formatter){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);
        return localDate.format(dateTimeFormatter);
    }

    public static LocalDateTime stringToLocalDateTime(String str,String formatter){

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatter);

        return LocalDateTime.parse(str, dateTimeFormatter);

    }







}
