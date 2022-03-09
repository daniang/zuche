package com.example.zuche.refondmoney.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class RefondAddDto {

    @ApiModelProperty("参加婚礼的人")
    private String name;

    @ApiModelProperty("随礼的钱")
    private BigDecimal money;


    @ApiModelProperty("产生的事件")
    private String eventId;

    @ApiModelProperty("备注")
    private String remark;


}
