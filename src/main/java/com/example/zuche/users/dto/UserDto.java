package com.example.zuche.users.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc :
 * @Author : chengzhang
 * @Date : 2021/12/27 14:33
 */
@Data
@ApiModel(value = "用户登录")
public class UserDto {

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private  String password;

}
