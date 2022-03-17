package com.example.zuche.utils.encrypt;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/3/17 11:00
 */
@Slf4j
public class MD5Util {


    public static final String KEY_MD5 = "MD5";

    public static byte[] encryMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);
        return md5.digest();
    }


    public static void main(String[] args) throws Exception {
        String str = "I Love You!";
        byte[] bytes = encryMD5(str.getBytes());





        String s = new String(bytes);

        log.info("s = {}", s);
    }
}
