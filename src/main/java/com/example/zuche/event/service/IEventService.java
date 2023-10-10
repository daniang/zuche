package com.example.zuche.event.service;

import com.example.zuche.event.dto.EventAddDto;
import com.example.zuche.event.dto.EventDto;
import com.example.zuche.event.pojo.Event;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.zuche.event.vo.EventVo;
import com.example.zuche.utils.PageResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-17
 */
public interface IEventService extends IService<Event> {

    PageResponse<EventVo> getAllEvent(EventDto dto);

    void test();
}
