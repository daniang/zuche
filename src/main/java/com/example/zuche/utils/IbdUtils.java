package com.example.zuche.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.zuche.enums._808AlarmType;
import com.example.zuche.exception.CustomizedErrorException;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.PropertyDescriptor;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 通用 工具类
 */
public class IbdUtils {

    /**
     * @return 得到12位的imei
     */
    public static String getImei(String imei) {
        return imei.length() == 15 ? imei.substring(3) : imei;
    }

    /**
     * 验证是否是基站定位
     *
     * @param stateCode
     * @return
     */
    public static Boolean isBaseStationPositioning(String stateCode) {
        int i1 = Integer.parseInt(stateCode.substring(stateCode.length() - 2, stateCode.length() - 1));
        if (i1 == 0) {
            //基站定位
            return true;
        }
        //卫星定位
        return false;
    }

    /**
     * @param info 传入的参数
     * @return ture表示传入的参数有空 false表示有参数没有空
     */
    public static Boolean isEmpty(Object... info) {
        for (Object param : info) {
            if (param instanceof Integer) {
                Integer value = (Integer) param;
                if (value == null) {
                    return true;
                }
            } else if (param instanceof String) {
                String s = (String) param;
                if (StringUtils.isBlank(s)) {
                    return true;
                }
            } else if (param instanceof Double) {
                Double d = (Double) param;
                if (d == null) {
                    return true;
                }
            } else if (param instanceof Float) {
                Float f = (Float) param;
                if (f == null) {
                    return true;
                }
            } else if (param instanceof Long) {
                Long l = (Long) param;
                if (l == null) {
                    return true;
                }
            } else if (param instanceof Boolean) {
                Boolean b = (Boolean) param;
                if (b == null) {
                    return true;
                }
            } else if (param instanceof Date) {
                Date d = (Date) param;
                if (d == null) {
                    return true;
                }
            } else if (param instanceof LocalDateTime) {
                LocalDateTime d = (LocalDateTime) param;
                if (d == null) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * 用于分页数据封装
     *
     * @param list
     * @param totalCount
     * @return
     */
    public static Map<String, Object> getPageMap(List list, Long totalCount) {
        Map<String, Object> map = new HashMap<>(2);
        map.put("date", list == null || list.isEmpty() ? new ArrayList<>() : list);
        map.put("total", totalCount == null || totalCount < 0L ? 0L : totalCount);
        return map;
    }

    /**
     * 字符串根据符号切割得到集合
     *
     * @param str
     * @param symbol
     * @return
     */
    public static List<String> split(String str, String symbol) {
        List<String> menuIdList = new ArrayList<>();
        String[] split = str.split(symbol);
        for (String id : split) {
            menuIdList.add(id);
        }
        return menuIdList;
    }


    /**
     * 文件流写到前端
     *
     * @param response
     * @param inputStream
     */
    public static void setResponseHeader(HttpServletResponse response, String fileName, InputStream inputStream) {
        ServletOutputStream servletOutputStream = null;
        try {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            servletOutputStream = response.getOutputStream();
            IOUtils.copy(inputStream, servletOutputStream);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                servletOutputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 分页
     *
     * @param arr
     * @param page
     * @param size
     * @return
     * @throws CustomizedErrorException
     */
    public static JSONObject limitMehtod(List arr, Integer page, Integer size) throws CustomizedErrorException {
        JSONObject json = new JSONObject();
        List list = new ArrayList<>();

        if (page == 0 || page == null) {
            page = 1;
        }

        if (size == 0 || size == null) {
            size = 10;
        }

        if (arr.size() == 0) {
            throw new CustomizedErrorException("分页的数组不能没有元素");
        }

        // 算出总页数并向上取整
        long countPageSize = 0;

        if (countPageSize == 0) {
            if (arr.size() > 0) {
                int size1 = arr.size();
                double i = (double) size1 / size;
                countPageSize = (long) (Math.ceil(i));
            }
        }

        if (page > countPageSize) {
            throw new CustomizedErrorException("超出总页数,请设置合理的页数和条数");
        }

        for (int i = (page - 1) * size; i < (page - 1) * size + size; i++) {
            if (i == arr.size() - 1) {
                list.add(arr.get(i));
                // 终止循环，索引越界
                break;
            }
            list.add(arr.get(i));
        }
        json.put("list", list);
        json.put("total", arr.size());
        return json;
    }

    /**
     * base64加密
     *
     * @param value
     * @return
     */
    public static String Encoder(String value) {
        String base64 = null;
        try {
            base64 = Base64.getEncoder().encodeToString(value.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64;
    }

    public static String Encoder(byte[] value) {
        String base64 = Base64.getEncoder().encodeToString(value);
        return base64;
    }

    /**
     * base64解密
     *
     * @param value
     * @return
     */
    public static String Decoder(String value) {
        String base64 = null;
        try {
            base64 = new String(Base64.getDecoder().decode(value), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return base64;
    }

    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
     * <p>
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
     * <p>
     * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
     * 192.168.1.100
     * <p>
     * 用户真实IP为： 192.168.1.110
     *
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 解析报警
     */
//    public static List<String> alarmDecoder(String alarmCode) {
//        StringBuffer sb = new StringBuffer();
//        sb.append(alarmCode);
//        sb = sb.reverse();
//        List<String> strings = new ArrayList<>();
//        for (int i = 0; i < sb.toString().toCharArray().length; i++) {
//            if (sb.toString().toCharArray()[i] == '1') {
//                String desc = _808AlarmType.getDesc(i);
//                strings.add(desc);
//            }
//        }
//        return strings;
//    }

    /**
     * 计算唤醒时间(奇数天唤醒)
     */
    public static LocalDateTime getRouseTime(String imei) {
        LocalDateTime dateTime = getRouseDateByToday(imei);
        if (dateTime == null) return null;
        //已过唤醒时间计算下次唤醒时间
        if (LocalDateTime.now().compareTo(dateTime) >= 0) {
            dateTime = getNextRouseTime(dateTime);
        }
        //唤醒时间
        return dateTime;

    }

    public static LocalDateTime getRouseDateByToday(String imei) {
        if (StringUtils.isEmpty(imei) || imei.length() != 15) {
            return null;
        }
        //根据imei计算唤醒小时，分钟
        int hour = Integer.parseInt(imei.substring(11, 13)) % 24;
        int minute = Integer.parseInt(imei.substring(13, 15)) % 60;

        LocalTime sleepTime = LocalTime.of(hour, minute);
        //今天日期
        LocalDate date = LocalDate.now();
        LocalDateTime dateTime = date.atTime(sleepTime);
        if (date.getDayOfMonth() % 2 == 0) {
            //偶数天加1
            dateTime = dateTime.plusDays(1);
        }
        return dateTime;
    }


    /**
     * 根据当前唤醒时间计算下次唤醒时间
     */
    public static LocalDateTime getNextRouseTime(LocalDateTime dateTime) {
        int dayOfMonth = dateTime.plusDays(1).getDayOfMonth();
        if (dayOfMonth % 2 != 0) {
            dateTime = dateTime.plusDays(1);
        } else {
            dateTime = dateTime.plusDays(2);
        }
        return dateTime;
    }

    /**
     * imei格式验证
     */
    public static Boolean imeiFormatCheck(String imei) {
        if (StringUtils.isBlank(imei)) {
            return false;
        }
        return imei.length() == 15;
    }

    public static boolean voltageIsNormal(String voltage) {
        if (StringUtils.isBlank(voltage)) {
            return true;
        }
        double aDouble = Double.parseDouble(voltage.toLowerCase().replace("v", ""));
        if (aDouble < 3.1d) {
            return false;
        }
        return true;
    }

    /**
     * 导出文件时为Writer生成OutputStream.
     *
     * @param fileName 文件名
     * @param response response
     * @return ""
     */
    public static OutputStream getOutputStream(String fileName, HttpServletResponse response) {
        try {
//            response.setHeader("Pragma", "public");
//            response.setHeader("Cache-Control", "no-store");
//            response.addHeader("Cache-Control", "max-age=0");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));

            return response.getOutputStream();
        } catch (IOException e) {
            throw new CustomizedErrorException("导出失败!");
        }
    }

    /**
     * 发送请求
     *
     * @param url
     * @param paramMap
     * @return
     */
    public static String sendGet(String url, Map<String, ?> paramMap) {
        String result = "";
        BufferedReader in = null;
        String param = "";
        Iterator<String> it = paramMap.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            param += key + "=" + paramMap.get(key) + "&";
        }
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.connect();

            //接收核心返回过来的数据 xml  需要解析
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 获取空值字段
     */
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<String>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 毫秒转换为天时分
     */
    public static String formatDateTime(long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("小时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        if (milliSecond > 0) {
            sb.append(milliSecond).append("毫秒");
        }
        return sb.toString();
    }

    /**
     * 里程计算
     */

//    public static BigDecimal getMileage(List<TraceVo> ts) {
//        List<Point2D> point2DS = new ArrayList<>();
//        ts.forEach(f -> point2DS.add(new Point2D(Double.parseDouble(f.getLng()), Double.parseDouble(f.getLat()))));
//        return AddressUtils.getMileage(point2DS);
//    }

    /**
     * 时间转换
     */
    public static Double timeConversion(LocalTime localTime) {
        if (localTime.compareTo(LocalTime.MAX) == 0) {
            return 24.0D;
        }
        int hour = localTime.getHour();
        double m = localTime.getMinute() / 60d;
        return hour + (double) Math.round(m * 100) / 100;
    }

    /**
     * 端口号验证
     */
    public static boolean portVerify(Integer port) {
        //端口号验证 1 ~ 65535
        return null != port && port > 0 && port <= 65535;
    }

    /**
     * 判断是否为合法IP * @return the ip
     */
    public static boolean ipVerify(String ipAddress) {
        if (StringUtils.isBlank(ipAddress)) {
            return false;
        }
        String ip = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
        Pattern pattern = Pattern.compile(ip);
        Matcher matcher = pattern.matcher(ipAddress);
        return matcher.matches();
    }

    public static List<String> convertImei(String imei) {
        List<String> imeiList = new ArrayList<>();
        if (StringUtils.isNotBlank(imei)) {
            imeiList = Stream.of(imei.replace(" ", ",").split(",")).filter(StringUtils::isNotBlank).collect(Collectors.toList());
        }
        return imeiList;
    }

    public static List<String> convertId(String imei) {
        List<String> imeiList = new ArrayList<>();
        if (StringUtils.isNotBlank(imei)) {
            imeiList = Stream.of(imei.replace(" ", ",").split(",")).filter(StringUtils::isNotBlank).map(m->{
                if (m.length()==15) {
                    return m.substring(3);
                }
                return m;
            }).collect(Collectors.toList());
        }
        return imeiList;
    }

}
