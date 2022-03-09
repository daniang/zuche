package com.example.zuche.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

/**
 * 运用高德api的一些方法
 * 控制台
 * https://console.amap.com/dev/flow/manage/bcba517f00c6981dbcbdf3a40e4c52c4
 * web端的api
 * https://lbs.amap.com/api/webservice/guide/api/georegeo
 */
@Slf4j
public class GaodeUtils {

    //我的生活的key
    private static final String GAO_DE_KEY = "bcba517f00c6981dbcbdf3a40e4c52c4";


    public static void main(String[] args) throws IOException {


//        String str = geo("武汉市新洲区联盟里118号|北京市朝阳区阜通东大街6号");
//        log.info("str={}", str);

        String lng = "116.310003";

        String lat = "39.991957";

        String address = address(lng, lat);

        log.info("address={}", address);

    }


    /**
     * 通过 地址 查询信息
     *
     * @param address
     * @return
     * @throws IOException
     */
    public static String geo(String address) throws IOException {
        String urlString = "https://restapi.amap.com/v3/geocode/geo?address=" + address + "&key=" + GAO_DE_KEY + "&batch=true";
        StringBuffer add = new StringBuffer();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8")
        );
        String line;

        while ((line = in.readLine()) != null) {
            add.append(line);
        }
        in.close();
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(add.toString());
        JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("geocodes"));
        if (jsonArray.size() > 0) {
            JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.get(0).toString());

            String formatted_address = jsonObject1.getString("formatted_address");
            return formatted_address;

        }
        return null;
    }


    public static String address(String lng, String lat) throws IOException {
        String location = lng.concat(",").concat(lat);

        String urlString = "https://restapi.amap.com/v3/geocode/regeo?location=" + location + "&key=" + GAO_DE_KEY + "&radius = 500" + "&extensions = all";

        StringBuffer add = new StringBuffer();
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        URLConnection urlConnection = url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), "UTF-8")
        );
        String line;

        while ((line = in.readLine()) != null) {
            add.append(line);
        }
        in.close();
        Map<String, String> map = new HashMap<>();
        JSONObject jsonObject = JSONObject.parseObject(add.toString());

        JSONObject regeocode = jsonObject.getJSONObject("regeocode");


        return regeocode.getString("formatted_address");

//        JSONArray jsonArray = JSONArray.parseArray(regeocode.getString("formatted_address "));
//        if (jsonArray.size() > 0) {
//            JSONObject jsonObject1 = JSONObject.parseObject(jsonArray.get(0).toString());
//
//            String formatted_address = jsonObject1.getString("formatted_address");
//            return formatted_address;
//
//        }
//
//        return null;
    }


}
