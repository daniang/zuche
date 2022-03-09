package com.example.zuche.utils.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2022/1/24 13:41
 */
@Data
@AllArgsConstructor
public class MyResponse implements Serializable {

    private static final long serialVersionUID = -2L;

    private String status;

    private String message;


}
