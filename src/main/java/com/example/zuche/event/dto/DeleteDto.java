package com.example.zuche.event.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : chengzhang
 * @Date : 2022/1/19 10:40
 */
@Data
@ApiModel("删除事件dto")
public class DeleteDto {

    @ApiModelProperty("事件id")
    private String eventId;
}
