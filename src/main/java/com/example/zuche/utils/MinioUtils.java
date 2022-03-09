package com.example.zuche.utils;

import com.example.zuche.pojo.Minio;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import io.minio.messages.Bucket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * minio minio工具类
 */
@Slf4j
@Component
public class MinioUtils {

    private static MinioClient minioClient;


    @Value("${minio.Url}")
    private String Url;

    @Value("${minio.AccessKey}")
    private String AccessKey;

    @Value("${minio.SecretKey}")
    private String SecretKey;


    private String BucketName = "file";


    /**
     * 初始化minio配置
     */
    @PostConstruct
    public void init() {
        try {
            log.info("Minio Initialize .............");

            minioClient = MinioClient.builder().endpoint(Url).credentials(AccessKey, SecretKey).build();

            log.info("Minio Initialize .............................successful");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("初始化minio配置异常:{}", e.fillInStackTrace());
        }

    }


    /**
     * @param bucketName 桶名
     * @return
     */
    @SneakyThrows(Exception.class)
    public static boolean bucketExists(String bucketName) {

        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

    }

    /**
     * 创建桶名
     * <p>
     * //     * @param bucketName
     */
    @SneakyThrows(Exception.class)
    public static void createBucket(String bucketName) {
        boolean b = minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());

        if (!b) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
        }
    }

    @SneakyThrows(Exception.class)
    public static List<Bucket> getAllBuckets() {

        return minioClient.listBuckets();
    }

    /**
     * object 为储存的名字
     *
     * @param file
     */
    @SneakyThrows(Exception.class)
    public void upload(MultipartFile file, String bucketName,String folder) {
        createBucket(bucketName);
        init();
        String fileName = file.getOriginalFilename();
//        String name = fileName.substring(0, fileName.lastIndexOf(".") + 1);
//        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
//        String object = name + LocalDateTime.now() + "." + type;
        if (!StringUtils.isEmpty(folder)) {
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).
                    object(folder+"/"+fileName).stream(file.getInputStream(), -1, 10485760).contentType(file.getContentType()).build());
        }else{
            minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).
                    object(fileName).stream(file.getInputStream(), -1, 10485760).contentType(file.getContentType()).build());
        }




//        minioClient.uploadObject(UploadObjectArgs.builder().bucket(BucketName).object(fileName+ LocalDateTime.now()).filename("C:\\Users\\Administrator\\Desktop\\"+fileName).build());

    }





    @SneakyThrows
    public String download(String fileName, String bucketName) {
        createBucket(bucketName);
        init();
        FilterInputStream stream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).
                object(fileName).build());


        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = stream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }
        outSteam.close();
        stream.close();
        log.info("读取成功");
//        return outSteam.toByteArray().toString();
        return outSteam.toString();

    }

    @SneakyThrows
    public void downloadObject(HttpServletResponse response, String bucketName, String fileName) {
        createBucket(bucketName);

        InputStream object = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName).
                object(fileName).build());
        byte buf[] = new byte[1024];

        int length = 0;
        response.reset();

        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("utf-8");
        OutputStream outputStream = response.getOutputStream();

        while ((length = object.read(buf)) > 0) {
            outputStream.write(buf, 0, length);
        }
        outputStream.close();
//        minioClient.downloadObject(DownloadObjectArgs.builder().bucket(bucketName).object(fileName).build());

    }


    @SneakyThrows
    public String getUrl(){
        Map<String, String> reqParams = new HashMap<String, String>();
        reqParams.put("response-content-type", "application/json");

        String url = minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .method(Method.GET)
                        .bucket("images")
                        .object("放假通知.png")
                        .expiry(2, TimeUnit.HOURS)
                        .extraQueryParams(reqParams)
                        .build());
        return url;

    }







    public static void main(String[] args) throws IOException, InvalidKeyException, InvalidResponseException, InsufficientDataException, NoSuchAlgorithmException, ServerException, InternalException, XmlParserException, ErrorResponseException {

        Map<String, String> reqParams = new HashMap<String, String>();
        reqParams.put("response-content-type", "application/json");

        minioClient = MinioClient.builder().endpoint("http://116.62.165.204:9000").credentials("minioadmin", "minioadmin").build();

        /**
         * minio:
         *   Url: http://116.62.165.204:9000
         *   AccessKey: minioadmin
         *   SecretKey: minioadmin
         */

        String url = minioClient.getPresignedObjectUrl(
                        GetPresignedObjectUrlArgs.builder()
                                .method(Method.GET)
                                .bucket("images")
                                .object("IMG_6336.jpg")
                                .expiry(2, TimeUnit.HOURS)
                                .extraQueryParams(reqParams)
                                .build());
        System.out.println(url);

    }
}
