package com.example.zuche.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @desc :  新增 账号vo
 * @Author : chengzhang
 * @Date : 2022/1/20 17:25
 */
@Data
@AllArgsConstructor
@ApiModel("新增账号vo")
public class AccountVo {

    @ApiModelProperty("账号id")
    private String id;

    @ApiModelProperty("名字")
    private String name;


}
