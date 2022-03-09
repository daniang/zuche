package com.example.zuche.event.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.zuche.event.dto.DeleteDto;
import com.example.zuche.event.dto.EventAddDto;
import com.example.zuche.event.dto.EventDto;
import com.example.zuche.event.enums.EventEnums;
import com.example.zuche.event.pojo.Event;
import com.example.zuche.event.service.IEventService;
import com.example.zuche.event.vo.EventVo;
import com.example.zuche.exception.CustomizedErrorException;
import com.example.zuche.utils.PageResponse;
import com.example.zuche.utils.UUIDUtils;
import com.example.zuche.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-17
 */
@RestController
@Slf4j
@Api(tags = "事件")
@RequestMapping("/event")
public class EventController {

    @Resource
    private IEventService eventService;

    @Resource
    private ModelMapper modelMapper;


    @ApiOperation("新增事件或修改")
    @PostMapping("eventAddOne")
    public ResultVo<Void> eventAddOne(@RequestBody EventAddDto dto) {
        log.info("eventAddOne方法的参数是{}", dto);
        Event event = new Event();
        if (StringUtils.isEmpty(dto.getEventId())) {
            dto.setEventId(UUIDUtils.getUUID());
            dto.setCreateDate(LocalDate.now());
            dto.setDelete(EventEnums.未删除.getIndex());
        } else {
            event = eventService.getById(dto.getEventId());
        }

        event.saveOrUpdate(dto);

        eventService.saveOrUpdate(event);
        return ResultVo.success();
    }

    @RequiresPermissions("query")
    @PostMapping("getAllEvent")
    @ApiOperation("查看所有的事件")
    public ResultVo<PageResponse<EventVo>> getAllEvent(@Valid @RequestBody EventDto dto) {
        log.info("getAllEvent");

//        List<Event> list = eventService.list(new QueryWrapper<Event>().lambda().like(!StringUtils.isEmpty(dto.getEventName()), Event::getName, dto.getEventName()));
//        List<EventVo> collect = list.stream().map(x -> modelMapper.map(x, EventVo.class)).collect(Collectors.toList());

        PageResponse<EventVo> vos = eventService.getAllEvent(dto);
        return ResultVo.success(vos);
    }

    @ApiOperation("根据id删除事件")
    @DeleteMapping("delEvent")
    public ResultVo<Void> delEvent(@RequestBody DeleteDto deleteDto) {
        log.info("删除事件");
//        eventService.remove(new QueryWrapper<Event>().lambda().eq(Event::getEventId, eventId));

        boolean update = eventService.update(new UpdateWrapper<Event>().lambda()
                .eq(Event::getEventId, deleteDto.getEventId()).set(Event::getIsDelete, EventEnums.已删除.getIndex()));

        if (!update) {
            throw new CustomizedErrorException("删除失败");
        }
        return ResultVo.success();
    }


}

