package com.example.zuche.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class MD5Util {

    /**
     * 16进制字符集
     */
    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**
     * 指定算法为MD5的MessageDigest
     */
    private static MessageDigest messageDigest = null;


    /**
     * 初始化messageDigest的加密算法MD5
     */
    static {
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 获取文件的MD5的值，限制文件大小：小于2G
     *
     * @param file 目标文件
     * @return MD5字符串
     */
    public static String getFileMD5String(File file) {
        String ret = "";
        FileInputStream in = null;
        FileChannel ch = null;

        try {
            in = new FileInputStream(file);
            ch = in.getChannel();

            ByteBuffer byteBuffer = ch.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            messageDigest.update(byteBuffer);
            ret = bytesToHex(messageDigest.digest());
        } catch (IOException e) {
            log.info(e.getMessage(), e);
        } finally {
            if (in != null) {
                try {
                    in.close();

                } catch (IOException e) {
                    log.error("关闭流出错：{}", e.getMessage());
                }
            }
        }
        if (ch != null) {
            try {
                ch.close();
            } catch (IOException e) {

                log.info("关闭流出错：{}{}", e.getMessage(), e);
            }
        }
        return ret;
    }

    /**
     * 获取文件的MD5值
     *
     * @param fileName 目标文件的完成名称
     * @return
     */
    public static String getFileMD5String(String fileName) {

        return getFileMD5String(new File(fileName));
    }

    /**
     * MD5加密字符串
     *
     * @param str 目标字符串
     * @return MD5加密后的字符串
     */
    public static String getMD5String(String str) {
        return getMD5String(str.getBytes());
    }

    /**
     * MD5加密字符串
     *
     * @param str 目标字符串
     * @return MD5加密后的字符串
     */
    public static String getMD5Lower(String str) {
        return getMD5String(str.getBytes()).toLowerCase();
    }

    public static String getMD5String(byte[] bytes) {
        messageDigest.update(bytes);
        return bytesToHex(messageDigest.digest());

    }

    /**
     * 效验密码与其MD5是否一致
     * @param pwd pwd  密码字符串
     * @param md5 基准MD5值
     * @return 检验结果
     */
    public static boolean checkPassword(String pwd , String md5){
        return getMD5String(pwd).equalsIgnoreCase(md5);
    }


    /**
     * 将字节数组转换成16进制字符串
     *
     * @param bytes
     *            目标字节数组
     * @return 转换结果
     */
    public static String bytesToHex(byte bytes[]) {
        return bytesToHex(bytes, 0, bytes.length);
    }

    /**
     * 将字节数组中指定区间的子数组转换成16进制字符串
     *
     * @param bytes
     *            目标字节数组
     * @param start
     *            起始位置（包括该位置）
     * @param end
     *            结束位置（不包括该位置）
     * @return 转换结果
     */
    public static String bytesToHex(byte bytes[], int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i < start + end; i++ ) {
            sb.append(byteToHex(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 将单个字节码转换成16进制字符串
     *
     * @param bt
     *            目标字节
     * @return 转换结果
     */
    public static String byteToHex(byte bt) {
        return HEX_DIGITS[(bt & 0xf0) >> 4] + "" + HEX_DIGITS[bt & 0xf];
    }


    /**
     * base64+md5双重加密
     * @param str 待加密字符串
     * @return  加密后的字符串
     */
//    public static String md5EncryptBase64(String str){
//        //先进行MD5加密,加密完成字符串继续进行base64加密。
//        byte[] bytesMd5 = getMD5String)
//        Base64 base64 = new Base64();
//        //base64 加密
//        byte[] byteBase64 = base64.encode(bytesMd5);
//        return new String(byteBase64).replace("==", "").trim().toString();
//    }


}
