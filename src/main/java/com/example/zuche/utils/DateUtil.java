package com.example.zuche.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.util.*;


@Slf4j
public class DateUtil {

    public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";

    public static final String FORMAT_DATETIME_SEARCH = "yyyyMMdd HH:mm:ss";
    /**
     * 缺省的日期时间格式
     */
    public static final String FORMAT_DATETIME_SIMPLE = "yyyy-M-d HH:mm:ss";
    /**
     * Datetime all yyyyMMddHHmmssS .
     */
    public static final String FORMAT_DATETIME_ALL_S = "yyyyMMddHHmmssS";

    /**
     * Datetime all yyyyMMddHHmmss
     */
    public static final String FORMAT_DATETIME_ALL = "yyyyMMddHHmmss";

    /**
     * yyyyMMddHHmmssSSS
     */
    public static final String FORMAT_DATETIME_ALL_SSS = "yyyyMMddHHmmssSSS";

    /**
     * yyyy-MM-dd HH:mm:ss.SSS
     */
    public static final String _FORMAT_DATETIME_ALL_SSS = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * Date yyyyMMdd.
     */
    public static final String FORMAT_DATE_COMPACT = "yyyyMMdd";

    /**
     * Date yyyy-MM-dd .
     */
    public static final String FORMAT_DATE = "yyyy-MM-dd";

    /**
     * Date monty yyyyM.
     */
    public static final String FORMAT_DATE_M = "yyyyM";

    /**
     * yyyy-M-d.
     */
    public static final String FORMAT_DATE_SIMPLE = "yyyy-M-d";

    /**
     * 日期格式 yyyy-MM-dd HH.
     */
    public static final String FORMAT_DATE_HH = "yyyy-MM-dd HH";

    /**
     * 日期格式 yyyy_MM_dd.
     */
    public static final String FORMAT_DATE_LOW = "yyyy_MM_dd";

    /**
     * 日期格式 yyyy-MM.
     */
    public static final String FORMAT_DATE_YEARMONTH = "yyyy-MM";

    /**
     * 日期格式 yyyy.MM.dd.
     */
    public static final String FORMAT_DATE_DOT = "yyyy.MM.dd";

    /**
     * 日期格式 yyyy.MM.dd.HH.
     */
    public static final String FORMAT_DATE_DOTHOUR = "yyyy.MM.dd.HH";

    /**
     * 日期格式yyyyMMddHH .
     */
    public static final String FORMAT_DATA_PDF_ = "yyyyMMddHH";

    /**
     * HH:mm:ss .
     */
    public static final String FORMAT_TIME = "HH:mm:ss";


    private DateUtil() {

    }

    /**
     * 返回当天日期的{@link #FORMAT_DATE}格式.
     */

    public static String getDayStr() {

        return new SimpleDateFormat(FORMAT_DATE).format(new Date());

    }

    /**
     * 返回当天日期的{@link Timestamp}格式.
     */
    public static Timestamp getDayTimestamp() {
        return new Timestamp(new Date().getTime());
    }

    /**
     * 返回当天的年月{@link #FORMAT_DATE_YEARMONTH}格式.
     */
    public static String getYearMonth(Date date) {
        return new SimpleDateFormat(FORMAT_DATE_YEARMONTH).format(date);
    }

    /**
     * 返回当天的年月的上一月{@link #FORMAT_DATE_YEARMONTH}格式 , 如果日期出错则返回null.
     */
    public static String getBeforeYearMonth(String yearMonth) {
        try {
            SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATE_YEARMONTH);
            Date date = df.parse(yearMonth);
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
            return getYearMonth(cal.getTime());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 返回{@code date}日期的{@link #FORMAT_DATE}格式.
     */
    public static String getDayStr(Date date) {
        return new SimpleDateFormat(FORMAT_DATE).format(date);
    }

    /**
     * 取当天的第1秒的时间.
     */
    public static Date getBeginOfDay(Date date) {
        Calendar cBegin = Calendar.getInstance();
        cBegin.setTime(date);
        cBegin.set(Calendar.HOUR_OF_DAY, 0);
        cBegin.set(Calendar.MINUTE, 0);
        cBegin.set(Calendar.SECOND, 0);
        cBegin.set(Calendar.MILLISECOND, 0);
        return cBegin.getTime();
    }

    /**
     * 取当天的最后一秒的时间.
     */
    public static Date getEndOfDay(Date date) {
        Calendar cEnd = Calendar.getInstance();
        cEnd.setTime(date);
        cEnd.set(Calendar.HOUR_OF_DAY, 23);
        cEnd.set(Calendar.MINUTE, 59);
        cEnd.set(Calendar.SECOND, 59);
        cEnd.set(Calendar.MILLISECOND, 999);
        return cEnd.getTime();
    }

    /**
     * 返回当天时间的{@link #FORMAT_TIME}格式.
     */
    public static String getTimeStr() {
        return new SimpleDateFormat(FORMAT_TIME).format(new Date());
    }

    /**
     * 返回{@code date}时间的{@link #FORMAT_TIME}格式.
     */
    public static String getTimeStr(Date date) {
        return new SimpleDateFormat(FORMAT_TIME).format(date);
    }

    /**
     * 当前日期转换成yyyyMMddHHmmssSSS格式.
     */
    public static String getDateStr() {
        Date time = new Date();
        String timeString = new SimpleDateFormat(FORMAT_DATETIME_ALL_SSS).format(time);
        return timeString;
    }

    /**
     * 返回当前日期的 yyyy年MM月dd日.
     */
    public static String getDateStringChinese() {
        Date time = new Date();
        String timeString = new SimpleDateFormat("yyyy年MM月dd日").format(time);
        return timeString;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm
     */
    public static String dateToStrNoSecond(Date dateDate) {
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(dateDate);
        return dateString;
    }

    /**
     * 取日期的字符串 {@link #FORMAT_DATA_PDF_}.
     */
    public static String getPdfDate() {
        return new SimpleDateFormat(FORMAT_DATA_PDF_).format(new Date());
    }

    /**
     * 获得昨天的日期.
     */
    public static String getDateStr(Date day) {
        return new SimpleDateFormat(FORMAT_DATE).format(day);
    }

    /**
     * 将字符串解析成日期.
     */
    public static Date getDate(String dateStr, String formate) {
        try {
            return new SimpleDateFormat(formate).parse(dateStr);
        } catch (ParseException e) {
            log.error("解析日期出错");
        }
        return null;
    }

    /**
     * 将字符串解析成日期.
     */
    public static Date getDateFromStr(String dateStr) {
        try {
            return new SimpleDateFormat(FORMAT_DATE).parse(dateStr);
        } catch (ParseException e) {
            log.error("解析日期出错");
        }
        return null;
    }

    /**
     * 获得昨天的日期.
     */
    public static Date getLastDayDate() {
        String lastDay = getDownloadDateString()[1];
        try {
            return new SimpleDateFormat(FORMAT_DATE).parse(lastDay);
        } catch (ParseException e) {
            log.error("解析日期出错");
        }
        return null;
    }

    /**
     * 获得昨天和今天的日期.
     */
    public static String[] getDownloadDateString() {
        String string[] = new String[2];
        Calendar cal = Calendar.getInstance();
        String yesterday = new SimpleDateFormat(FORMAT_DATE).format(cal.getTime());
        string[0] = yesterday;
        cal.add(Calendar.DAY_OF_MONTH, -1);
        yesterday = new SimpleDateFormat(FORMAT_DATE).format(cal.getTime());
        string[1] = yesterday;
        return string;
    }

    /**
     * 取日期的字符串 yyyyMMddHHmmssSSS.
     */
    public static String getDateTime() {
        return new SimpleDateFormat(FORMAT_DATETIME_ALL_SSS).format(new Date());
    }

    /**
     * 取date的年与日期的字符串,返回格式为2013/002/.
     *
     * @param date 计算的日期.
     */
    public static String getYearDaysStr(Date date) {
        return getDayOfYear(date);
    }

    /**
     * 取date的年与日期的字符串,返回格式为2013/002/.
     *
     * @param date   计算的日期.
     * @param dayPos 日期的偏移，+为今天以后的，－为今天之前的偏移量
     */
    public static String getYearDaysStr(Date date, int dayPos) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + dayPos);
        return getDayOfYear(c.getTime());
    }

    /**
     * 取date所在年份的第几天，长度为3位，不足的前补0.
     *
     * @param date 计算的天数
     */
    private static String getDayOfYear(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        String dayOfYear = c.get(Calendar.DAY_OF_YEAR) + "";
        while (dayOfYear.length() < 3) {
            dayOfYear = "0" + dayOfYear;
        }
        return c.get(Calendar.YEAR) + "/" + dayOfYear + "/";
    }

    /**
     * 格式化Calendar.
     */
    public static String formatCalendar(Calendar calendar) {
        if (calendar == null) {
            return "";
        }
        return new SimpleDateFormat(FORMAT_DATE_SIMPLE).format(calendar.getTime());
    }

    /**
     * FORMAT_DATETIME_SIMPLE.
     */
    public static String formatDateTime(Date d) {
        if (d == null) {
            return "";
        }
        return new SimpleDateFormat(FORMAT_DATETIME_SIMPLE).format(d);
    }

    /**
     * FORMAT_DATE_SIMPLE.
     */
    public static String formatDate(Date d) {
        if (d == null) {
            return "";
        }
        return new SimpleDateFormat(FORMAT_DATE_SIMPLE).format(d);
    }

    /**
     * 格式化时间.
     */
    public static String formatTime(Date d) {
        if (d == null) {
            return "";
        }
        return new SimpleDateFormat(FORMAT_TIME).format(d);
    }

    /**
     * 格式化整数型日期.
     */
    public static String formatIntDate(Integer intDate) {
        if (intDate == null) {
            return "";
        }
        Calendar c = newCalendar(intDate);
        return formatCalendar(c);
    }

    /**
     * 根据指定格式化来格式日期.
     *
     * @param date    待格式化的日期.
     * @param pattern 格式化样式或分格,如yyMMddHHmmss
     * @return 字符串型日期.
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        if (null == pattern || "".equals(pattern.trim())) {
            return formatDate(date);
        }
        SimpleDateFormat simpleDateFormat = null;
        try {
            simpleDateFormat = new SimpleDateFormat(pattern);
        } catch (Exception e) {
            e.printStackTrace();
            return formatDate(date);
        }
        return simpleDateFormat.format(date);
    }

    /**
     * 取得Integer型的当前日期.
     */
    public static Integer getIntNow() {
        return getIntDate(getNow());
    }

    /**
     * 取得Integer型的当前日期.
     */
    public static Integer getIntToday() {
        return getIntDate(getNow());
    }

    /**
     * 取得Integer型的当前年份.
     */
    public static Integer getIntYearNow() {
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 取得Integer型的当前月份.
     */
    public static Integer getIntMonthNow() {
        Calendar c = Calendar.getInstance();
        int month = c.get(Calendar.MONTH) + 1;
        return month;
    }

    /**
     * 根据年月日获取整型日期.
     */
    public static Integer getIntDate(int year, int month, int day) {
        return getIntDate(newCalendar(year, month, day));
    }

    /**
     * 某年月的第一天.
     */
    public static Integer getFirstDayOfMonth(int year, int month) {
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 某年月的第一天.
     */
    public static Integer getFirstDayOfThisMonth() {
        Integer year = getIntYearNow();
        Integer month = getIntMonthNow();
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 某年月的第一天.
     */
    public static Integer getFistDayOfMonth(Date date) {
        Integer intDate = getIntDate(date);
        int year = intDate / 10000;
        int month = intDate % 10000 / 100;
        return getIntDate(newCalendar(year, month, 1));
    }

    /**
     * 某年月的最后一天.
     */
    public static Integer getLastDayOfMonth(int year, int month) {
        return intDateSub(getIntDate(newCalendar(year, month + 1, 1)), 1);
    }

    /**
     * 根据Calendar获取整型年份.
     */
    public static Integer getIntYear(Calendar c) {
        int year = c.get(Calendar.YEAR);
        return year;
    }

    /**
     * 根据Calendar获取整型日期.
     */
    public static Integer getIntDate(Calendar c) {
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);
        return year * 10000 + month * 100 + day;
    }

    /**
     * 根据Date获取整型年份.
     */
    public static Integer getIntYear(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return getIntYear(c);
    }

    /**
     * 根据Date获取整型日期.
     */
    public static Integer getIntDate(Date d) {
        if (d == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        return getIntDate(c);
    }

    /**
     * 判断今天是今年的第几天.
     */
    public static String getSysString() {
        // 获取系统时间
        Date date = new Date();
        // %tj表示一年中的第几天
        String strDate = String.format("%tj", date);
        return strDate;
    }

    /**
     * 判断该日是今年的第几天.
     */
    public static String getSysString(String dateString) {
        // 设置时间
        Date date = strToDate(dateString);
        // %tj表示一年中的第几天
        String strDate = String.format("%tj", date);
        return strDate;
    }

    /**
     * 判断给定日期是今年的第几天.
     */
    public static String getSysString(Date date) {
        // %tj表示一年中的第几天
        String strDate = String.format("%tj", date);
        return strDate;
    }

    /**
     * 根据Integer获取Date日期.
     */
    public static Date getDate(Integer n) {
        if (n == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(n / 10000, n / 100 % 100 - 1, n % 100);
        return c.getTime();
    }

    public static Date getDate(String date) {
        if (date == null || date.length() == 0) {
            return null;
        }

        try {
            if (date.contains("/")) {
                date = date.replaceAll("/", "-");
            }
            return new SimpleDateFormat(FORMAT_DATE).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 根据年份Integer获取Date日期.
     */
    public static Date getFirstDayOfYear(Integer year) {
        if (year == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.set(year, 1, 1);
        return c.getTime();
    }

    /**
     * 根据年月日生成Calendar.
     */
    public static Calendar newCalendar(int year, int month, int day) {
        Calendar ret = Calendar.getInstance();
        if (year < 100) {
            year = 2000 + year;
        }
        ret.set(year, month - 1, day);
        return ret;
    }

    /**
     * 根据整型日期生成Calendar.
     */
    public static Calendar newCalendar(int date) {
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        Calendar ret = Calendar.getInstance();
        ret.set(year, month - 1, day);
        return ret;
    }

    /**
     * 取得Date型的当前日期.
     */
    public static Date getNow() {
        return new Date();
    }

    /**
     * 取得Date型的当前日期.
     */
    public static Date getToday() {
        return getDate(getIntToday());
    }

    /**
     * 整数型日期的加法.
     */
    public static Integer intDateAdd(int date, int days) {
        int year = date / 10000;
        int month = (date % 10000) / 100;
        int day = date % 100;

        day += days;

        return getIntDate(year, month, day);
    }

    /**
     * 整数型日期的减法.
     */
    public static Integer intDateSub(int date, int days) {
        return intDateAdd(date, -days);
    }

    /**
     * 计算两个整型日期之间的天数.
     */
    public static Integer daysBetweenDate(Integer startDate, Integer endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        Calendar c1 = newCalendar(startDate);
        Calendar c2 = newCalendar(endDate);

        Long lg = (c2.getTimeInMillis() - c1.getTimeInMillis()) / 1000 / 60 / 60 / 24;
        return lg.intValue();
    }

    /**
     * 计算两个整型日期之间的天数.
     */
    public static Integer daysBetweenDate(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        Long interval = endDate.getTime() - startDate.getTime();
        interval = interval / (24 * 60 * 60 * 1000);
        return interval.intValue();
    }

    /**
     * 计算两个日期之间间隔的时间,*D*H*M*S.
     */
    public static String calTwoDays(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        Long interval = endDate.getTime() - startDate.getTime();
        Long days = interval / (24 * 60 * 60 * 1000);
        Long hour = interval / (60 * 60 * 1000) % 24;
        Long min = interval / (60 * 1000) % (24 * 60);
        Long sencond = interval / (1000) % (24 * 60 * 60);
        return new StringBuffer().append(days + "D" + hour + "H" + min + "M" + sencond + "S").toString();
    }

    /**
     * 根据calendar产生字符串型日期 eg:20080707.
     */
    public static String getStringDate(Date d) {
        if (d == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(d);
    }

    public static String getFormatStringDate(Date d) {
        if (d == null) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        return sdf.format(d);
    }

    /**
     * 获得所在星期的第一天.
     */
    public static Date getFirstDateByWeek(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        now.set(Calendar.DATE, first_day_of_week);
        return now.getTime();
    }

    /**
     * 获得所在星期的最后一天.
     */
    public static Date getLastDateByWeek(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int today = now.get(Calendar.DAY_OF_WEEK);
        int first_day_of_week = now.get(Calendar.DATE) + 2 - today; // 星期一
        int last_day_of_week = first_day_of_week + 6; // 星期日
        now.set(Calendar.DATE, last_day_of_week);
        return now.getTime();
    }

    /**
     * 获得所在月份的最后一天.
     *
     * @return 月份的最后一天
     */
    public static Date getLastDateByMonth(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + 1);
        now.set(Calendar.DATE, 1);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - 1);
        now.set(Calendar.HOUR, 11);
        now.set(Calendar.MINUTE, 59);
        now.set(Calendar.SECOND, 59);
        return now.getTime();
    }

    /**
     * 获得所在月份的第一天.
     *
     * @return 月份的第一天
     */
    public static Date getFirstDateByMonth(Date date) {

        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.set(Calendar.DATE, 0);
        now.set(Calendar.HOUR, 12);
        now.set(Calendar.MINUTE, 0);
        now.set(Calendar.SECOND, 0);
        return now.getTime();
    }

    /**
     * 获取现在时间.
     *
     * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
     */
    public static Date getNowDate() {
        return Timestamp.valueOf(new SimpleDateFormat(FORMAT_DATETIME).format(new Date()));
    }

    /**
     * 获取现在时间.
     *
     * @return返回短时间格式 yyyy-MM-dd
     */
    public static Date getNowDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String dateString = formatter.format(currentTime);
        ParsePosition pos = new ParsePosition(8);
        Date currentTime_2 = formatter.parse(dateString, pos);
        return currentTime_2;
    }

    /**
     * 获取现在时间.
     *
     * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
     */
    public static String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取现在时间.
     *
     * @return 返回短时间字符串格式yyyy-MM-dd
     */
    public static String getStringDateShort() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 获取时间 小时:分;秒 HH:mm:ss.
     *
     * @return
     */
    public static String getTimeShort() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date currentTime = new Date();
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss.
     *
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
        ParsePosition pos = new ParsePosition(0);
        if (strDate != null) {
            Date strtodate = formatter.parse(strDate, pos);
            return strtodate;
        }
        return null;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss.
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd.
     *
     * @param dateDate
     * @param k
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        // public static final String FORMAT_DATE = "yyyy-MM-dd";
        // FORMAT_DATE_FORMAT
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd HH.
     */
    public static String dateToStrWithH(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_HH);
        // public static final String FORMAT_DATE = "yyyy-MM-dd";
        // FORMAT_DATE_FORMAT
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy.MM.dd.
     */
    public static String dateToStrDot(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_DOT);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy.MM.dd.HH.
     */
    public static String dateToStrDotHH(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE_DOTHOUR);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd.
     */
    public static Date strToDate(String strDate) {
        if (strDate == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    /**
     * 提取一个月中的最后一天.
     */
    public static Date getLastDate(long day) {
        Date date = new Date();
        long date_3_hm = date.getTime() - 3600000 * 34 * day;
        Date date_3_hm_date = new Date(date_3_hm);
        return date_3_hm_date;
    }

    /**
     * 得到现在时间.
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    public static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 得到现在小时.
     */
    public static String getHour() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
        String dateString = formatter.format(currentTime);
        String hour;
        hour = dateString.substring(11, 13);
        return hour;
    }

    /**
     * 得到现在分钟.
     */
    public static String getTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATETIME);
        String dateString = formatter.format(currentTime);
        String min;
        min = dateString.substring(14, 16);
        return min;
    }

    /**
     * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写.
     *
     * @param sformat yyyyMMddhhmmss
     * @return
     */
    public static String getUserDate(String sformat) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(sformat);
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 二个小时时间间的差值,必须保证二个时间都是"HH:MM"的格式，返回字符型的分钟.
     */
    public static String getTwoHour(String st1, String st2) {
        String[] kk = null;
        String[] jj = null;
        kk = st1.split(":");
        jj = st2.split(":");
        if (Integer.parseInt(kk[0]) < Integer.parseInt(jj[0]))
            return "0";
        else {
            double y = Double.parseDouble(kk[0]) + Double.parseDouble(kk[1]) / 60 + Double.parseDouble(kk[2]);
            double u = Double.parseDouble(jj[0]) + Double.parseDouble(jj[1]) / 60 + Double.parseDouble(jj[2]);
            if ((y - u) > 0)
                return y - u + "";
            else
                return "0";
        }
    }

    /**
     * 得到二个日期间的间隔天数.
     */
    public static String getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat(FORMAT_DATE);
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return "";
        }
        return day + "";
    }

    /**
     * 得到二个日期间的间隔天数.
     */
    public static long getTwoDay(Calendar beginDate, Calendar endDate) {
        try {
            return (endDate.getTime().getTime() - beginDate.getTime().getTime()) / (24 * 60 * 60 * 1000);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 得到二个日期间的间隔的小时数.
     */
    public static long getTwoDayHours(Calendar beginDate, Calendar endDate) {
        try {
            return (endDate.getTime().getTime() - beginDate.getTime().getTime()) / (60 * 60 * 1000);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 得到二个日期间的间隔的秒.
     */
    public static long getTwoDaySecond(Date beginDate, Date endDate) {
        try {
            return (endDate.getTime() - beginDate.getTime()) / (1000);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 得到二个日期间的间隔的豪秒数.
     */
    public static long getTwoDayMiniSecond(Date beginDate, Date endDate) {
        try {
            return (endDate.getTime() - beginDate.getTime()) / (1000);
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * 时间前推或后推分钟,其中JJ表示分钟.
     */
    public static String getPreTime(String sj1, String jj) {
        SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATETIME);
        String mydate1 = "";
        try {
            Date date1 = format.parse(sj1);
            long Time = (date1.getTime() / 1000) + Integer.parseInt(jj) * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 得到一个时间延后或前移几天的时间,nowdate为时间,delay为前移或后延的天数.
     */
    public static String getNextDay(String nowdate, String delay) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(FORMAT_DATE);
            String mdate = "";
            Date d = strToDate(nowdate);
            long myTime = (d.getTime() / 1000) + Integer.parseInt(delay) * 24 * 60 * 60;
            d.setTime(myTime * 1000);
            mdate = format.format(d);
            return mdate;
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 判断是否润年.
     */
    public static boolean isLeapYear(String ddate) {

        /**
         * 详细设计： 1.被400整除是闰年，否则： 2.不能被4整除则不是闰年 3.能被4整除同时不能被100整除则是闰年
         * 3.能被4整除同时能被100整除则不是闰年
         */
        Date d = strToDate(ddate);
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(d);
        int year = gc.get(Calendar.YEAR);
        if ((year % 400) == 0)
            return true;
        else if ((year % 4) == 0) {
            if ((year % 100) == 0)
                return false;
            else
                return true;
        } else
            return false;
    }

    /**
     * 返回美国时间格式 26 Apr 2006.
     */
    public static String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat(FORMAT_DATE);
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return k[2] + k[1].toUpperCase() + k[5].substring(2, 4);
    }

    /**
     * 获取一个月的最后一天.
     */
    public static String getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if (isLeapYear(dat)) {
                str += "29";
            } else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 判断二个时间是否在同一个周.
     */
    public static boolean isSameWeekDates(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        if (0 == subYear) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
            // 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        } else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR))
                return true;
        }
        return false;
    }

    /**
     * 产生周序列,即得到当前时间所在的年度是第几周.
     */
    public static String getSeqWeek() {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
        if (week.length() == 1)
            week = "0" + week;
        String year = Integer.toString(c.get(Calendar.YEAR));
        return year + week;
    }

    /**
     * 获得一个日期所在的周的星期几的日期，如要找出2002年2月3日所在周的星期一是几号.
     */
    public static String getWeek(String sdate, String num) {
        // 再转换为时间
        Date dd = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(dd);
        if ("1".equals(num)) // 返回星期一所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        else if ("2".equals(num)) // 返回星期二所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
        else if ("3".equals(num)) // 返回星期三所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
        else if ("4".equals(num)) // 返回星期四所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
        else if ("5".equals(num)) // 返回星期五所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
        else if ("6".equals(num)) // 返回星期六所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
        else if ("0".equals(num)) // 返回星期日所在的日期
            c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        return new SimpleDateFormat(FORMAT_DATE).format(c.getTime());
    }

    /**
     * 根据一个日期，返回是星期几的字符串.
     */
    public static String getWeek(String sdate) {
        // 再转换为时间
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // int hour=c.get(Calendar.DAY_OF_WEEK);
        // hour中存的就是星期几了，其范围 1~7
        // 1=星期日 7=星期六，其他类推
        return new SimpleDateFormat("EEEE").format(c.getTime());
    }

    /**
     * 两个时间之间的天数.
     */
    public static long getDays(String date1, String date2) {
        if (date1 == null || "".equals(date1))
            return 0;
        if (date2 == null || "".equals(date2))
            return 0;
        // 转换为标准时间
        SimpleDateFormat myFormatter = new SimpleDateFormat(FORMAT_DATE);
        Date date = null;
        Date mydate = null;
        try {
            date = myFormatter.parse(date1);
            mydate = myFormatter.parse(date2);
        } catch (Exception e) {
        }
        long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
        return day;
    }

    /**
     * 形成如下的日历 ， 根据传入的一个时间返回一个结构 星期日 星期一 星期二 星期三 星期四 星期五 星期六 下面是当月的各个时间
     * 此函数返回该日历第一行星期日所在的日期.
     */
    public static String getNowMonth(String sdate) {
        // 取该时间所在月的一号
        sdate = sdate.substring(0, 8) + "01";

        // 得到这个月的1号是星期几
        Date date = strToDate(sdate);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int u = c.get(Calendar.DAY_OF_WEEK);
        String newday = getNextDay(sdate, (1 - u) + "");
        return newday;
    }

    /**
     * 取得数据库主键 生成格式为yyyymmddhhmmss+k位随机数.
     *
     * @param k 表示是取几位随机数，可以自己定
     */

    public static String getNo(int k) {

        return getUserDate("yyyyMMddhhmmss") + getRandom(k);
    }

    /**
     * 返回一个随机数.
     */
    public static String getRandom(int i) {
        Random jjj = new Random();
        // int suiJiShu = jjj.nextInt(9);
        if (i == 0)
            return "";
        String jj = "";
        for (int k = 0; k < i; k++) {
            jj = jj + jjj.nextInt(9);
        }
        return jj;
    }

    /**
     * 判断是否是一定格式正确的日期.
     */
    public static boolean RightDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATETIME);
        if (date == null)
            return false;
        if (date.length() > 10) {
            sdf = new SimpleDateFormat(FORMAT_DATETIME);
        } else {
            sdf = new SimpleDateFormat(FORMAT_DATE);
        }
        try {
            sdf.parse(date);
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    /**
     * 将formBean 里的字符时间(yyyy-MM-dd) 转换成Date类型.
     */
    public static Date formBeanDateToPODate(String formDate) {
        try {
            if (null != formDate && !"".equals(formDate.trim())) {

                log.debug("---------formdate:" + formDate);
                return java.sql.Date.valueOf(formDate);

            }
        } catch (Exception e) {
            System.out.println("DateUtils:时间转换异常");
            return new Date();
        }
        return null;
    }

    public static String getDateString() {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        Date time = new Date();
        String timeString = format.format(time);
        return timeString;
    }

    /**
     * 将天数转换成日期.
     *
     * @param tianshu 天份
     * @param nianfen 年份
     * @return 日期
     */
    public static Date tianshuBecomeDate(int tianshu, int nianfen) {

        Calendar calendar = new GregorianCalendar(nianfen, 0, 0, 0, 0, 0);
        calendar.add(Calendar.DATE, tianshu);
        return calendar.getTime();
    }

    /**
     * 将天数，年份转化为 yyyyMMdd String.
     *
     * @param tianshu day of year
     * @param nianfen year
     * @return
     */
    public static String tianshuBecomeDateString(int tianshu, int nianfen) {
        Date date = tianshuBecomeDate(tianshu, nianfen);
        return new SimpleDateFormat("yyyyMMdd").format(date);
    }

    /**
     * 将天数，年份转化为 yyyyMMdd String.
     *
     * @param tianshu day of year
     * @param nianfen year
     * @return
     */
    public static String dayofyeartoDateString(String tianshu, String nianfen) {
        int day = Integer.parseInt(tianshu);
        int year = Integer.parseInt(nianfen);
        return tianshuBecomeDateString(day, year);
    }

    /**
     * yyyyMMddhhmm 变为Date类型.
     *
     * @param dateTime yyyyMMddhhmm
     * @return
     */
    public static Date dateTimeStringtoDateTime(String dateTime) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmm");
        Date date = null;
        try {
            date = format.parse(dateTime);
        } catch (ParseException e) {
            return null;
        }
        return date;
    }

    /**
     * Date 类型转为 yyyy String.
     *
     * @param date
     * @return yyyy String
     */
    public static String DatetoYearString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        return format.format(date);
    }

    /**
     * Date 类型转为 day of year String.
     *
     * @param date
     * @return day of year
     */
    public static String DatetoDayofYear(Date date) {
        Calendar can = Calendar.getInstance();
        can.setTime(date);
        if (can.get(Calendar.DAY_OF_YEAR) / 10 == 0) // example 006
            return "00" + Integer.toString(can.get(Calendar.DAY_OF_YEAR));
        if (can.get(Calendar.DAY_OF_YEAR) / 100 == 0) // example 017
            return "0" + Integer.toString(can.get(Calendar.DAY_OF_YEAR));
        else
            return Integer.toString(can.get(Calendar.DAY_OF_YEAR));
    }

    /**
     * 将日期按指定格式解析 .
     *
     * @param date    日期
     * @param formate 字符串.
     * @return 返回解析后的字符串
     */
    public static String dateToString(Date date, String formate) {
        return new SimpleDateFormat(formate).format(date);
    }

    /**
     * yyyyMMdd 变为Date类型.
     *
     * @return Date Type
     */
    public static Date DataStringtoDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * date 在5-10月份间 返回 1 ： 夏季 其他时间返回 0 ： 冬季.
     *
     * @param date
     * @return
     */
    public static int GetSeasonCode(Date date) {
        DateFormat df = new SimpleDateFormat(FORMAT_DATE);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        try {
            Date date1 = df.parse(year + "-05-01");
            Date date2 = df.parse(year + "-10-01");
            if (date.after(date1) && date.before(date2))
                return 1;
            else
                return 0;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Date to yyyy-MM-dd HH:mm:ss.
     *
     * @param date
     * @return
     */
    public static String DatetoDateString(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_DATETIME);
        return df.format(date);
    }

    /**
     * yyyyMMdd 变为Date类型.
     *
     * @return Date Type
     */
    public static Date StringToDate(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * yyyyMMdd 变为Date类型.
     *
     * @return Date Type
     */
    public static Date StringToDate(String dateString, String formats) {
        SimpleDateFormat format = new SimpleDateFormat(formats);
        Date date = null;
        try {
            date = format.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成long类型数据.
     */
    public static Long getDateTimeToNum(String dateTimeString) {
        try {
            if (StringUtils.isBlank(dateTimeString)) {
                return null;
            }
            return stringToDate(dateTimeString, FORMAT_DATETIME).getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解析失败返回 null.
     */
    public static Date stringToDate(String dateString, String formatString) {
        SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(formatString);
        localSimpleDateFormat.setLenient(false);
        try {
            return localSimpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds   精确到秒的字符串
     * @return
     */
    public static String timeStampDate(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * EG : 2016年1月14日 .
     */
    public static String getChineseDate(Calendar calendar) {
        int i = calendar.get(1);
        int j = calendar.get(2);
        int k = calendar.get(5);
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append("年");
        sb.append(j + 1);
        sb.append("月");
        sb.append(k);
        sb.append("日");
        return sb.toString();
    }

    /**
     * 根据日期返回是星期几,中文.
     */
    public static String getWeekStr(String sdate) {
        String str = "";
        str = getWeek(sdate);
        if ("1".equals(str)) {
            str = "星期日";
        } else if ("2".equals(str)) {
            str = "星期一";
        } else if ("3".equals(str)) {
            str = "星期二";
        } else if ("4".equals(str)) {
            str = "星期三";
        } else if ("5".equals(str)) {
            str = "星期四";
        } else if ("6".equals(str)) {
            str = "星期五";
        } else if ("7".equals(str)) {
            str = "星期六";
        }
        return str;
    }

    /**
     * eg : 星期四.
     */
    public static String getChineseWeekday(Calendar calendar) {
        String chinessWeekDay = "";
        switch (calendar.get(7)) {
            case 2:
                chinessWeekDay = "星期一";
                break;
            case 3:
                chinessWeekDay = "星期二";
                break;
            case 4:
                chinessWeekDay = "星期三";
                break;
            case 5:
                chinessWeekDay = "星期四";
                break;
            case 6:
                chinessWeekDay = "星期五";
                break;
            case 7:
                chinessWeekDay = "星期六";
                break;
            case 1:
                chinessWeekDay = "星期日";
                break;
            default:
                chinessWeekDay = "未知";
                break;
        }
        return chinessWeekDay;
    }

    /**
     * 获取2个时间的跨度
     *
     * @param date
     * @param date2
     * @return
     */
    public static List<String> statusDateTime(String date, String date2) {
        SimpleDateFormat datetime = new SimpleDateFormat("yyyyMMdd");
        List<String> spark = new ArrayList<String>();
        try {
            String d1 = date.substring(0, 8);
            String d2 = date2.substring(0, 8);
            Date fDate = datetime.parse(d1);
            Date oDate = datetime.parse(d2);
            long days = (oDate.getTime() - fDate.getTime()) / (1000 * 3600 * 24);
            for (Long i = 0L; i <= days; i++) {
                Calendar cal1 = Calendar.getInstance();
                cal1.setTime(fDate);
                cal1.add(Calendar.DAY_OF_YEAR, i.intValue());//日期加10天
                Date dt1 = cal1.getTime();
                spark.add(datetime.format(dt1));
            }
            /*
             * if(days==0) { spark.add(datetime.format(oDate)); }else if(days == 1) {
             * spark.add(datetime.format(fDate)); spark.add(datetime.format(oDate)); }else
             * if(days == 2){ Calendar cal1 = Calendar.getInstance(); cal1.setTime(oDate);
             * cal1.add(Calendar.DAY_OF_YEAR,-1);//日期加10天 Date dt1=cal1.getTime();
             * spark.add(datetime.format(fDate)); spark.add(datetime.format(dt1));
             * spark.add(datetime.format(oDate)); }
             */
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return spark;
    }

    /**
     * 指定格式的字符串转换成long类型数据.
     */
    public static Long getDateTime2ToNum(String dateTimeString) {
        try {
            if (StringUtils.isBlank(dateTimeString)) {
                return null;
            }
            return stringToDate(dateTimeString, FORMAT_DATETIME_ALL).getTime();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 计算当前月有多少天
     *
     * @return
     */
    public static int getDays(int year, int month) {
        int days = 0;
        if (month != 2) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    days = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    days = 30;

            }
        } else {
            // 闰年
            if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
                days = 29;
            } else {
                days = 28;
            }
        }
        return days;
    }


}
