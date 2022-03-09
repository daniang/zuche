package com.example.zuche.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @desc :  需要到处的字段实体
 * @Author : chengzhang
 * @Date : 2022/1/18 16:16
 */
@ApiModel("需要导出的字段实体")
@Data
public class ExportField {

    @ApiModelProperty(value = "实体映射的字段", required = true)
    @NotBlank(message = "实体映射的字段不能为空")
    private String fieldName;


    @ApiModelProperty(value = "导出字段中午描述", required = true)
    @NotBlank(message = "导出字段中文描述不能为空")
    private String fieldDesc;
}
