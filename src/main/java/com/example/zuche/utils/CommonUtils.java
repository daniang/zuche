package com.example.zuche.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class CommonUtils {
    public CommonUtils() {
    }

    public static Boolean isEmpty(Object... info) {
        Object[] var1 = info;
        int var2 = info.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Object param = var1[var3];
            if (param instanceof Integer) {
                Integer var5 = (Integer)param;
            } else if (param instanceof String) {
                String s = (String)param;
                if (StringUtils.isBlank(s)) {
                    return true;
                }
            } else if (param instanceof Double) {
                Double var7 = (Double)param;
            } else if (param instanceof Float) {
                Float var8 = (Float)param;
            } else if (param instanceof Long) {
                Long var9 = (Long)param;
            } else if (param instanceof Boolean) {
                Boolean var10 = (Boolean)param;
            } else if (param instanceof Date) {
                Date var11 = (Date)param;
            } else if (param instanceof LocalDateTime) {
                LocalDateTime var12 = (LocalDateTime)param;
            } else {
                if (param == null) {
                    return true;
                }

                if (param instanceof String[]) {
                    String[] b = (String[])((String[])param);
                    if (b.length == 0) {
                        return true;
                    }
                } else if (param instanceof Integer[]) {
                    Integer[] b = (Integer[])((Integer[])param);
                    if (b.length == 0) {
                        return true;
                    }
                } else if (param instanceof Double[]) {
                    Double[] b = (Double[])((Double[])param);
                    if (b.length == 0) {
                        return true;
                    }
                } else if (param instanceof Float[]) {
                    Float[] b = (Float[])((Float[])param);
                    if (b.length == 0) {
                        return true;
                    }
                } else if (param instanceof Long[]) {
                    Long[] b = (Long[])((Long[])param);
                    if (b.length == 0) {
                        return true;
                    }
                } else if (param instanceof Boolean[]) {
                    Boolean[] b = (Boolean[])((Boolean[])param);
                    if (b.length == 0) {
                        return true;
                    }
                } else if (param instanceof Date[]) {
                    Date[] b = (Date[])((Date[])param);
                    if (b.length == 0) {
                        return true;
                    }
                } else if (param instanceof LocalDateTime[]) {
                    LocalDateTime[] b = (LocalDateTime[])((LocalDateTime[])param);
                    if (b.length == 0) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public static Map<String, Object> getPageMap(List list, Long totalCount) {
        Map<String, Object> map = new HashMap(2);
        map.put("date", list != null && !list.isEmpty() ? list : new ArrayList());
        map.put("total", totalCount != null && totalCount >= 0L ? totalCount : 0L);
        return map;
    }

    public static List<String> split(String str, String symbol) {
        String[] split = str.split(symbol);
        return new ArrayList(Arrays.asList(split));
    }


    public static String Encoder(String value) {
        String base64 = null;

        try {
            base64 = Base64.getEncoder().encodeToString(value.getBytes("utf-8"));
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return base64;
    }

    public static String Encoder(byte[] value) {
        String base64 = Base64.getEncoder().encodeToString(value);
        return base64;
    }

    public static String Decoder(String value) {
        String base64 = null;

        try {
            base64 = new String(Base64.getDecoder().decode(value), "UTF-8");
        } catch (UnsupportedEncodingException var3) {
            var3.printStackTrace();
        }

        return base64;
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString().replace("-", "");
        return randomString;
    }

    public static String removeTrim(String str) {
        if (str.indexOf(".") > 0) {
            str = str.replaceAll("0+?$", "");
            str = str.replaceAll("[.]$", "");
        }

        return str;
    }

    public static Integer removeTrimByInteger(String str) {
        if (str.indexOf(".") > 0) {
            str = str.replaceAll("0+?$", "");
            str = str.replaceAll("[.]$", "");
        }

        return Integer.parseInt(str);
    }

    public static void main(String[] args) {
        String qwe1234 = Encoder("Qwe1234");
        System.out.println(qwe1234);
    }


    public static boolean isChinaPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[3,5,6,7,8])|(18[0-9])|(19[8,9]))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isHKPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^(5|6|8|9)\\d{7}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    public static boolean isNumeric(String string) {
        Pattern pattern = Pattern.compile("[0-9]+");
        return pattern.matcher(string).matches();
    }

    public static String sendGet(String url, Map<String, ?> paramMap) {
        String result = "";
        BufferedReader in = null;
        String param = "";

        String urlNameString;
        for(Iterator it = paramMap.keySet().iterator(); it.hasNext(); param = param + urlNameString + "=" + paramMap.get(urlNameString) + "&") {
            urlNameString = (String)it.next();
        }

        try {
            urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Accept-Charset", "utf-8");
            connection.connect();
            Map<String, List<String>> map = connection.getHeaderFields();
            Iterator var10 = map.keySet().iterator();

            while(var10.hasNext()) {
                String key = (String)var10.next();
                System.out.println(key + "--->" + map.get(key));
            }

            String line;
            for(in = new BufferedReader(new InputStreamReader(connection.getInputStream())); (line = in.readLine()) != null; result = result + line) {
            }
        } catch (Exception var20) {
            var20.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception var19) {
                var19.printStackTrace();
            }

        }

        return result;
    }

    public static <T, V> List<V> emptyCondThenReturnEmptyList(List<T> condList, Supplier<List<V>> fun) {
        return (List)(CollectionUtils.isEmpty(condList) ? new ArrayList() : (List)fun.get());
    }

    public static <T, V> List<V> emptyCondThenReturnEmptyList(String str, Supplier<List<V>> fun) {
        return (List)(StringUtils.isBlank(str) ? new ArrayList() : (List)fun.get());
    }

    public static <T, V> List<V> emptyCondThenReturnEmptyList(Object obj, Supplier<List<V>> fun) {
        if (obj == null) {
            return new ArrayList();
        } else {
            return obj instanceof String ? emptyCondThenReturnEmptyList(obj.toString(), fun) : (List)fun.get();
        }
    }
}
