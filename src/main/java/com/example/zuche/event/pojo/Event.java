package com.example.zuche.event.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import com.example.zuche.event.dto.EventAddDto;
import com.example.zuche.utils.CommonUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.junit.Test;
import org.springframework.util.StringUtils;

/**
 * <p>
 *
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Event extends Model<Event> {

    private static final long serialVersionUID = 1L;

    /**
     * 事件表主键id
     */
    @TableId(value = "event_id")
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


    @Override
    public Serializable pkVal() {
        return this.eventId;
    }

    public void saveOrUpdate(EventAddDto dto) {

        if (!StringUtils.isEmpty(dto.getEventId())) {
            this.eventId = dto.getEventId();
        }
        if (!CommonUtils.isEmpty(dto.getCreateDate())) {
            this.createTime = dto.getCreateDate();
        }
        if (!StringUtils.isEmpty(dto.getName())) {
            this.name = dto.getName();
        }
        if (!CommonUtils.isEmpty(dto.getDelete())) {
            this.isDelete = dto.getDelete();
        }
    }




}
