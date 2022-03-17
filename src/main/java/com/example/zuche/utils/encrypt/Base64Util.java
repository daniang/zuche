package com.example.zuche.utils.encrypt;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/3/17 10:41
 */
@Slf4j
public class Base64Util {

    /**
     * BASE64解密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static byte[] decryBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64 加密
     *
     * @param key
     * @return
     * @throws Exception
     */
    public static String encrytBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encode(key);

    }

    public static void main(String[] args) throws Exception {

        String str = "I Love You!";
        String encrytStr = encrytBASE64(str.getBytes());

        log.info("encrytStr = {}", encrytStr);

        byte[] bytes = decryBASE64(encrytStr);


        String decryStr = new String(bytes);


        log.info("decryStr = {}", decryStr);
    }
}
