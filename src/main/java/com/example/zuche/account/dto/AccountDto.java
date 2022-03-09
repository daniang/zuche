package com.example.zuche.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @desc :  新增账号密码 dto
 * @Author : chengzhang
 * @Date : 2022/1/20 17:29
 */
@Data
@ApiModel("新增账号dto")
public class AccountDto {

    @ApiModelProperty("id")
    private String id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("url")
    private String url;

    @ApiModelProperty("备注")
    private String remark;


}
