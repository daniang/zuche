package com.example.zuche.myexception;


public interface BaseErrorInfoInterface {

    /**
     * 错误码
     */
    String getResultCode();


    /**
     * 错误描述
     *
     * @return
     */
    String getResultMsg();
}
