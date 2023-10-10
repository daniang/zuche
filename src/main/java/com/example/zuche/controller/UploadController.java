package com.example.zuche.controller;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.system.UserInfo;
import com.example.zuche.users.pojo.Users;
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
import java.util.HashMap;

@RestController
@RequestMapping("upload")
@Slf4j
@Api(tags = "上传下载")
public class UploadController {


    @Resource
    private FastDfsUtils fastDfsUtils;

    @Resource
    private MinioUtils minioUtils;



    @PostMapping("/upload")
    @SneakyThrows
    public String upload(@RequestParam("file") MultipartFile file, Users userInfo) {
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

    @GetMapping("/shiPing")
    public ResultVo<String> shiPing( Integer streamType,Integer channelNo,Integer dataType) {
        log.info("shiPing参数{},{},{}",streamType,channelNo,dataType);
        String post = HttpUtil.post("http://172.30.1.104:3014/api/video/pull?deviceId=013304521793&ip=120.202.27.102&port=9918&channelNo="+channelNo+"&dataType="+dataType+"&streamType="+streamType, new HashMap<>());
        System.out.println(post);
        return ResultVo.success(post);

    }
    @GetMapping("/shiPing2")
    public ResultVo<String> shiPing2(Integer streamType ) {
        String post = HttpUtil.post("http://172.30.1.104:3014/api/video/pull?deviceId=013304521793&ip=120.202.27.102&port=9918&channelNo=2&dataType=0&streamType=0", new HashMap<>());
        return ResultVo.success(post);

    }


}
