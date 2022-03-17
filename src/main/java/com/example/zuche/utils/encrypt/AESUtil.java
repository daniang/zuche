package com.example.zuche.utils.encrypt;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @desc :  高级加密标准
 * @Author : chengzhang
 * @Date : 2022/3/17 16:43
 */
public class AESUtil {

    public static final String algorithm = "AES";
    //AES/CBC/NoPaddin
    //AES 默认模式

    //使用CBC模式，在初始化Cipher 对象时,需要增加参数,初始化向量IV:IvParameterSec iv = new IvParameterSpec(key.getBytes());
    //NoPadding: 使用NOPadding模式时,原文长度必须是8Byte 的整数倍
    public static final String transformation = "AES/CBC/NOPadding";
    public static final String key = "1234567812345678";

    /**
     * 加密
     *
     * @param original 需要加密的参数(注意必须是16位)
     * @return
     * @throws Exception
     */
    public static String encryptByAES(String original) throws Exception {
        //获取Cipher
        Cipher cipher = Cipher.getInstance(transformation);
        //生成密钥
        SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(), algorithm);
        //指定模式(加密)和密钥
        //创建初始化向量
        IvParameterSpec iv = new IvParameterSpec(key.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);
        //加密
        byte[] bytes = cipher.doFinal(original.getBytes());

        return Base64Util.encrytBASE64(bytes);
    }

    public static String decrytByAES(String encrypted){

        return null;
    }

}
