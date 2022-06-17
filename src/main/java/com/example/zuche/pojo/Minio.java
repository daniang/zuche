package com.example.zuche.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * minio配置
 */
@Data
@Component
public class Minio {

    @Value("${minio.Url}")
    public String Url;

    @Value("${minio.AccessKey}")
    public String AccessKey;

    @Value("${minio.SecretKey}")
    public String SecretKey;

    /*文件夹*/
    public String BucketName = "file";

}
