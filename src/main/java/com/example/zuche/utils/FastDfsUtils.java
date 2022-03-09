package com.example.zuche.utils;


import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

/**
 * fastdfs 文件工具类
 */
@Component
@Slf4j
public class FastDfsUtils {

    @Resource
    private FastFileStorageClient storageClient;

//    @Value("${fdfs.perUrl}")
//    private String perUrl;


    /**
     * 上传文件
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String upload(MultipartFile multipartFile) throws IOException {

        String originalFilename = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);

        StorePath storePath = storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(), multipartFile.getSize(), originalFilename, null);

        ApplicationHome home = new ApplicationHome(getClass());
        File path = home.getSource().getParentFile();
        log.info("path{}",path);
//        return perUrl + storePath.getFullPath();
        return storePath.getFullPath();
    }


}
