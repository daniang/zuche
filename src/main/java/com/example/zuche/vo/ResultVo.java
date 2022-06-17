package com.example.zuche.vo;

import com.alibaba.fastjson.JSONObject;
import com.example.zuche.pojo.ResponseCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;

/**
 * 响应类，快速调用
 */
@Getter
public class ResultVo<T> implements Serializable {

    @ApiModelProperty(value = "错误编码 0:成功 40000:服务器内部异常 40003:表示给用户提示错误subMsg")
    private Integer code;

    @ApiModelProperty(value = "服务器内部异常子编码(如: 405:Method Not Allowed、401:未认证、未登录)")
    private Integer subCode;

    @ApiModelProperty(value = "前端操作标识,由前后端开发人员自由约定")
    private String tag;

    @ApiModelProperty(value = "当code=0提示:成功, 当code!=0时提示服务器内部异常")
    private String msg;

    @ApiModelProperty(value = "提示信息详情")
    private String subMsg;

    @ApiModelProperty(value = "响应数据")
    private T data;

    @ApiModelProperty(value = "扩展数据")
    @Setter
    @Transient
    private String expandJson;

    public ResultVo() {
    }

    public ResultVo tag(String tag) {
        this.tag = tag;
        return this;
    }

    public ResultVo expandJson(String expandJson) {
        this.expandJson = expandJson;
        return this;
    }

    public ResultVo(ResponseCode.Code code, Integer subCode, T data, String msg) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.subCode = subCode;
        this.subMsg = msg;
        this.data = data == null ? (T) new JSONObject() : data;
    }

    public ResultVo(ResponseCode.Code code, ResponseCode.SubCode subCode, T data) {
        this(code, subCode, data, subCode.getMsg());
    }

    public ResultVo(ResponseCode.Code code, ResponseCode.SubCode subCode, T data, String msg) {
        this(code, subCode.getCode(), data, msg);
    }

    public static <T> ResultVo<T> success() {
        return new ResultVo<T>(ResponseCode.Code.SUCCESS, ResponseCode.SubCode.SUCCESS, null);
    }

    public static <T> ResultVo<T> success(String msg) {
        return new ResultVo<T>(ResponseCode.Code.SUCCESS, ResponseCode.SubCode.SUCCESS, null, msg);
    }

    public static <T> ResultVo<T> success(T data) {
        return new ResultVo<T>(ResponseCode.Code.SUCCESS, ResponseCode.SubCode.SUCCESS, data);
    }

    /**
     * 业务执行失败调用此方法
     *
     * @param subCode 失败描述信息
     */
    public static <T> ResultVo<T> fail(ResponseCode.SubCode subCode) {
        return new ResultVo<T>(ResponseCode.Code.BIZ_FAIL, subCode, null, subCode.getMsg());
    }

    /**
     * 业务执行失败调用此方法
     *
     * @param msg 失败描述信息
     */
    public static <T> ResultVo<T> fail(String msg) {
        return new ResultVo<T>(ResponseCode.Code.BIZ_FAIL, ResponseCode.SubCode.FAIL_RESPONSE, null, msg);
    }

    /**
     * 业务执行失败调用此方法
     */
    public static <T> ResultVo<T> fail() {
        return new ResultVo<T>(ResponseCode.Code.BIZ_FAIL, ResponseCode.SubCode.FAIL_RESPONSE, null, ResponseCode.SubCode.FAIL_RESPONSE.getMsg());
    }

    /**
     * 服务器内部异常或系统错误调用此方法
     *
     * @param subCode 错误子编码
     * @param msg     描述
     */
    public static <T> ResultVo<T> error(int subCode, String msg) {
        return new ResultVo<T>(ResponseCode.Code.SYS_ERROR, subCode, null, msg);
    }

    /**
     * 设备特殊编码
     *
     * @param msg 描述
     */
    public static <T> ResultVo<T> warn(String msg) {
        return new ResultVo<T>(ResponseCode.Code.SYS_WARN, ResponseCode.SubCode.SYS_WARN, null, msg);
    }
}
