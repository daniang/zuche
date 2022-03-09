package com.example.zuche.myexception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/2/10 11:06
 */
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(Exception e) {

        log.info("未知异常!原因是{}", e);

        return e.getMessage();
    }

}
