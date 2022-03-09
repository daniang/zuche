package com.example.zuche.event.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.zuche.event.pojo.Event;
import com.example.zuche.utils.BaseRequest;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EventVo {

    /**
     * 事件表主键id
     */
    private String eventId;

    /**
     * 事件名称
     */
    private String name;

    /**
     * 事件发生时间
     */
    private LocalDate createTime;

    /**
     * 事件状态 0为正常1 为已经删除
     */
    private Integer isDelete;


    public EventVo(Event x) {
        this.eventId = x.getEventId();
        this.name = x.getName();
        this.createTime = x.getCreateTime();
        this.isDelete = x.getIsDelete();
    }
}
