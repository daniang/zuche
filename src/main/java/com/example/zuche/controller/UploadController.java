package com.example.zuche.controller;

import cn.hutool.http.HttpUtil;
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
import java.util.Map;

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
        minioUtils.upload(file, bucketName, null);

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
        minioUtils.downloadObject(response, bucketName, fileName);
    }

    @GetMapping("/getUrl")
    public ResultVo<String> getUrl() {

        return ResultVo.success(minioUtils.getUrl());

    }

    @GetMapping(value = "pullVideoStream")
    ResultVo<Void> pullVideoStream(String host, String ip, String port, String deviceId, String channelNo, String streamType) {

        //POST http://172.30.1.61:30012/api/video/pull?deviceId=013304521793&ip=120.202.27.102&port=9918&channelNo=1&dataType=0&streamType=0


        Map<String, Object> map = new HashMap<>();
        map.put("ip", ip);
        map.put("port", port);
        map.put("deviceId", deviceId);
        map.put("channelNo", channelNo);
        map.put("streamType", streamType);
        map.put("dataType", 0);
        String post = HttpUtil.post(host + "/api/video/pull", map);
        return ResultVo.success(post);
    }

    @GetMapping(value = "closedVideoStream")
    ResultVo<Void> closedVideoStream(String host, String deviceId, String channelNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", deviceId);
        map.put("channelNo", channelNo);
        String post = HttpUtil.post(host + "/api/video/stop", map);
        return ResultVo.success(post);
    }


    @GetMapping(value = "/replay")
    public ResultVo<Void> replay(String ip, Integer port, String deviceId, Integer channelNo, String startTime, String endTime) {
        Map<String, Object> map = new HashMap<>();
        map.put("ip", ip);
        map.put("port", port);
        map.put("deviceId", deviceId);
        map.put("channelNo", channelNo);
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        String get = HttpUtil.get(" http://172.30.1.61:30012/api/video/replay", map);
        return ResultVo.success(get);
    }

    @GetMapping(value = "/replayStop")
    public ResultVo<Void> replayStop(String deviceId, Integer channelNo) {
        Map<String, Object> map = new HashMap<>();
        map.put("deviceId", deviceId);
        map.put("channelNo", channelNo);
        String get = HttpUtil.get(" http://172.30.1.61:30012/api/video/replayStop", map);
        return ResultVo.success(get);
    }

}
