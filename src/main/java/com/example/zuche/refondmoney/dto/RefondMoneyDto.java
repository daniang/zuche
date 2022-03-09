package com.example.zuche.refondmoney.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @desc :  查询 礼钱
 * @Author : chengzhang
 * @Date : 2022/1/20 15:31
 */
@Data
@ApiModel("查询礼钱")
public class RefondMoneyDto {

    @ApiModelProperty("名字")
    private String name;


    @ApiModelProperty("事件名字")
    private String eventName;

}
