package com.example.zuche.event.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class EventAddDto {

    @ApiModelProperty("事件id")
    private String eventId;

    @ApiModelProperty("事件名称")
    private String name;

    @ApiModelProperty("事件发生时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate createDate;

    @ApiModelProperty("是否删除 0是未删 1是删除")
    private Integer delete;


}
