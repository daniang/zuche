package com.example.zuche.event.dto;

import com.example.zuche.utils.BaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * @desc :  查询事件列表
 * @Author : chengzhang
 * @Date : 2022/1/18 17:03
 */
@Data
@ApiModel("查询时间列表")
public class EventDto extends BaseRequest {

    @ApiModelProperty("事件名称")
    @Length(max = 3)
    private String eventName;


}
