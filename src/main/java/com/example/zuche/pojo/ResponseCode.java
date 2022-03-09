package com.example.zuche.pojo;

import lombok.Getter;

/**
 * @author: yzj
 * create at 2020/7/14 16:11
 * @description: 状态码枚举类
 */
public class ResponseCode {

   @Getter
   public enum Code {
       /** Code编码及含义 */
        SUCCESS(0, "成功"),
        SYS_ERROR(40000, "服务器内部异常"),
        BIZ_FAIL(40003, "操作失败"),
        SYS_WARN(40013, "操作提示"),
        ;
        Code(int code, String msg) {
          this.code = code;
          this.msg = msg;
        }
       private String msg;
       private int code;
    }

    @Getter
    public enum SubCode {
        /** SubCode编码及含义 */
        SUCCESS(0, "成功"),
        UNSUCCESS(204, "响应成功，无数据"),
        SERVER_ERROR(500, "服务器内部异常"),
        NOT_LOGIN_ERROR(10000,"未登录或会话失效，请重新登录"),
        Locked_ERROR(10001,"账户已被禁用，请联系管理员"),
        ACCOUNT_AUTHENTICATION_ERROR(10005,"账号或密码错误"),
        PERMISSION_AUTHENTICATION_ERROR(10010,"无法访问，您可能没有权限"),
        SYS_WARN(40013, "操作提示"),
        FAIL_RESPONSE(40002, "操作失败"),
        SIGN_ERROR(50000, "参数验签错误"),
        ;
        SubCode(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
        private String msg;
        private int code;
    }

    //成功
    public static final Integer SUCCESS = 200;
    //响应成功，无数据
    public static final Integer UNSUCCESS = 204;
    //调用其他模块，服务被降级
    public static final Integer FALLBACK = 1000;
    //服务器报错
    public static final Integer SERVER_ERROR = 500;
    //未认证、未登录
    public static final Integer UNAUTHENTICATION = 401;
    //未鉴权、无权限
    public static final Integer UNAUTHORIZED = 403;
    //找不到服务
    public static final Integer NOT_FOUND = 404;
    //指令下发不在线
    public static final Integer TERMINAL_NOT_ONLINON = 601;
    //指令下发成功
    public static final Integer TERMINAL_SUCCESS = 602;
    //超过并发数，报错
    public static final Integer SENTINEL_FOLLW_ERROR = 1001;
    //服务器维护
    public static final Integer SERVER_MAINTAIN = 2000;
    //需要在页面提示的错误码
    public static final Integer WARN_EXCEPTION = 40003;
    //返回错误的时候页面提示
    public static final Integer FAIL_RESPONSE = 40002;
    //需要在页面提示并处理错误码
    public static final Integer WARN_HANDLE_EXCEPTION = 40013;
    //验签失败
    public static final Integer SIGN_ERROR = 50000;

}
