package com.example.zuche.controller;

import cn.hutool.http.HttpResponse;
import com.example.zuche.utils.FastDfsUtils;
import com.example.zuche.utils.MinioUtils;
import com.example.zuche.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("upload")
@Slf4j
@Api(tags = "上传下载")
public class UploadController {


    @Resource
    private FastDfsUtils fastDfsUtils;

    @Resource
    private MinioUtils minioUtils;


    @RequiresAuthentication
    @ResponseBody
    @PostMapping("/upload")
    @SneakyThrows
    public String upload(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String upload = fastDfsUtils.upload(file);
//        FastDfsUtils.upload(file);

//        MinioUtils.upload(fileName, bucketName, file);
//
//        File dest = new File(filePath + fileName);

//        try{
//            file.transferTo(dest);
//            log.info("上传成功");
//            return "上传成功";
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "上传失败!";

        return upload;
    }

    @ResponseBody
    @RequiresAuthentication
    @PostMapping("/uploadMinio")
    @SneakyThrows
    @ApiOperation("上传文件到minio")
    public ResultVo<Void> uploadMinio(@RequestParam("file") MultipartFile file, String bucketName) {
        if (file.isEmpty()) {
            throw new Exception("未传递文件");
        }
        minioUtils.upload(file, bucketName,null);

        return ResultVo.success();
    }

    @ResponseBody
    @PostMapping("/downloadMinio")
    @SneakyThrows
    public String downloadMinio(String fileName, String bucketName) {

        return minioUtils.download(fileName, bucketName);
    }


    @GetMapping("/downloadObject")
    public void downloadObject(HttpServletResponse response, String bucketName, String fileName) {
        minioUtils.downloadObject(response , bucketName,fileName);
    }

    @GetMapping("/getUrl")
    public ResultVo<String> getUrl( ) {

        return ResultVo.success(minioUtils.getUrl());

    }

}
