package com.example.zuche.account.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @desc :  账号list dto
 * @Author : chengzhang
 * @Date : 2022/1/21 14:25
 */
@Data
@ApiModel("账号listdto")
public class AccountListDto {

    @ApiModelProperty("名字")
    private String name;






}
