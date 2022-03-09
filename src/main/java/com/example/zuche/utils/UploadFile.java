package com.example.zuche.utils;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class UploadFile {

    @Resource
    private FastFileStorageClient fastFileStorageClient;
//
//    @Value("${fdfs.perUrl}")
//    private String perUrl;


    @Test
    public void test() throws IOException {
        String data = "C:\\Users\\Administrator\\Desktop\\helloworld.txt";
        File file2 = new File( data);
        String url2 = uploadFile(file2);
        System.out.println(url2);
    }


    public String uploadFile(File file) throws IOException {
        FileInputStream inputStream = new FileInputStream(file);
        StorePath path = fastFileStorageClient.uploadFile(inputStream, file.length(),
                FilenameUtils.getExtension(file.getName()), null);
        inputStream.close();
        return getResAccessUrl(path);

    }

    // 封装图片完整URL地址
    private String getResAccessUrl(StorePath storePath) {
//        String fileUrl = perUrl + storePath.getFullPath();
        return null;
    }


}
