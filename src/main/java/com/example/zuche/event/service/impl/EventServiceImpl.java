package com.example.zuche.event.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.zuche.event.dto.EventAddDto;
import com.example.zuche.event.dto.EventDto;
import com.example.zuche.event.pojo.Event;
import com.example.zuche.event.dao.EventMapper;
import com.example.zuche.event.service.IEventService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zuche.event.vo.EventVo;
import com.example.zuche.utils.PageResponse;
import com.example.zuche.utils.PageResponseUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-17
 */
@Service
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements IEventService {


    @Override
    public PageResponse<EventVo> getAllEvent(EventDto dto) {

        IPage<EventVo> page = new Page<>(dto.getPage(), dto.getSize());

        List<Event> vos = this.baseMapper.getAllEvent(page, dto);

        List<EventVo> collect = vos.stream().map(x -> new EventVo(x)).collect(Collectors.toList());


        page.setRecords(collect);

        return PageResponseUtil.getPageResponse(page);
    }
}
