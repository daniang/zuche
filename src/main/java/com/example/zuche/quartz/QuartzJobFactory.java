package com.example.zuche.quartz;


import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.logging.Logger;

/**
 * @desc :  实现job接口，重写execute
 * @Author : chengzhang
 * @Date : 2022/2/24 9:39
 */
@Slf4j
public class QuartzJobFactory implements Job {


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //todo 这里是定时任务逻辑

        log.info("=====================我是定时任务，每隔5秒执行一次=====================================");
    }





}
