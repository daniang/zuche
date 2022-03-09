package com.example.zuche.event.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.zuche.event.dto.EventDto;
import com.example.zuche.event.pojo.Event;
import com.example.zuche.event.vo.EventVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-17
 */
@Mapper
public interface EventMapper extends BaseMapper<Event> {

    List<Event> getAllEvent(IPage<EventVo> page, @Param("dto") EventDto dto);
}
