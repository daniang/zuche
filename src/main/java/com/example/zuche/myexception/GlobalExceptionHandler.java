package com.example.zuche.myexception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @desc :  自定义全局异常处理的类
 * @Author : chengzhang
 * @Date : 2022/2/10 16:28
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ResponseBody
    @ExceptionHandler(value = BizException.class)
    public ResultBody bizExceptionHandler(HttpServletRequest req, BizException e) {

        log.error("发生业务异常! 原因是：{}", e.getErrorMsg());
        return ResultBody.error(e.getErrorCode(), e.getErrorMsg());
    }

    /**
     * 处理空指针的异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = NullPointerException.class)
    @ResponseBody
    public ResultBody exceptionHandler(HttpServletRequest req, NullPointerException e) {
        log.error("发生空指针异常! 原因是{}", e);
        return ResultBody.error(CommonEnum.BODY_NOT_MATCH);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultBody exceptionHandler(HttpServletRequest req, Exception e) {
        log.error("未知异常,原因是{}", e);
        return ResultBody.error(CommonEnum.INTERNAL_SERVER_ERROR);
    }


}
