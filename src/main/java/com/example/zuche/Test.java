package com.example.zuche;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @version : 2022/7/28 17:14
 * @desc :  TODO
 * @Author : chengzhang
 * @since JDK8
 */
public class Test {


    @org.junit.Test
    public void Test1() {
//        String str = "南方一中心&gt;%gt;西南区域呼叫中心>>测试新增部门182";

        String str = "南方一中心&gt;&gt;西南区域呼叫中心>>测试新增部门181";

        String[] split = str.replaceAll("&gt;", ">").split(">>");

        List<String> reqOrgPathList = new ArrayList<>(Arrays.asList(split));

//        reqOrgPathList.subList(0,"");

    }



    @org.junit.Test
    public void Test() {
        //原路径
        String path = "南方一中心>>西南区域呼叫中心>>测试新增部门108>>测试新增部门53";

        List<String> paths = Arrays.asList(path.split(">>"));

        List<String> pathList = new ArrayList<>(paths);

        String str = "西南区域呼叫中心>>西南区域呼叫中心>>测试新增部门108";

        String[] split = str.split(">>");

        List<String> strings = Arrays.asList(split);

        List<String> arrList = new ArrayList(strings);

        if (split[0].equals(split[1])) {

            arrList.remove(split[0]);

//            strings.remove(0);
            List<String> strings1 = pathList.subList(0, pathList.indexOf(split[0]));

            String lastPath = strings1.stream().collect(Collectors.joining(">>")) + arrList.stream().collect(Collectors.joining(">>"));
            System.out.println("lastPath = " + lastPath);

        }
    }

    public static final String ENCODE = "UTF-8";
    public static final String CIPHER_ALGORITHM = "AES";
    private static final String KEY_MAC = "HmacMD5";
    public static final String CIPHER_ALGORITHM_INS = "AES/ECB/PKCS5Padding";
    /**
     * AES加密
     * @param keyStr 密钥
     * @param dataStr 原始数据
     */
    public static String encrypt(String keyStr,String dataStr) throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        SecretKey secretKey = new SecretKeySpec(decoder.decodeBuffer(keyStr), CIPHER_ALGORITHM);
        //Cipher完成加密或解密工作类
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_INS);
        //对Cipher初始化，解密模式
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //加密data
        byte[] cipherByte = cipher.doFinal(dataStr.getBytes(ENCODE));
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return  base64Encoder.encode(cipherByte);
    }




}
