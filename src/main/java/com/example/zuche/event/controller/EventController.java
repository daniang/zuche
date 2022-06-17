package com.example.zuche.event.controller;


import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.zuche.event.dto.DeleteDto;
import com.example.zuche.event.dto.EventAddDto;
import com.example.zuche.event.dto.EventDto;
import com.example.zuche.event.enums.EventEnums;
import com.example.zuche.event.pojo.Event;
import com.example.zuche.event.service.IEventService;
import com.example.zuche.event.vo.EventVo;
import com.example.zuche.exception.CustomizedErrorException;
import com.example.zuche.refondmoney.vo.RefondMoneyVo;
import com.example.zuche.utils.PageResponse;
import com.example.zuche.utils.UUIDUtils;
import com.example.zuche.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    //    @RequiresPermissions("query")
    @PostMapping("getAllEvent")
    @ApiOperation("查看所有的事件")
    public ResultVo<PageResponse<EventVo>> getAllEvent(@RequestBody EventDto dto) {
        log.info("getAllEvent");

//        Map<String, Object> map = new HashMap<>();
//        map.put("orgId", 0);
//        map.put("carNo", 12346);
//        map.put("token", "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaGVuZ3poYW5nIiwidXNlcklkIjoiZjc5MDAxYTJhYjQ2NDFlZTkzN2E0ODY4ZmNjMGY1N2MiLCJjcmVhdGVkIjoxNjUzNDc2MjAzNzUzLCJhdXRob3JpdGllcyI6W3siYXV0aG9yaXR5Ijoic2EifV0sImV4cCI6MTY1MzY1NjIwM30.Ib_0Xf2Yt8vpxUmWrG8TcLvT61fw-OqYrk07hbZ64II");
////        String post = HttpUtil.post("http://192.168.201.49:8081" + "/refondmoney/getList", map);
//        String get = HttpUtil.get("http://192.168.201.49:8221/caoqiong" + "/refondmoney/getList");
////        String post = HttpUtil.post("https://api-carlink-sit.starlinkware.com/api" + "/carManage/list", map);
//
//
//        JSONArray data = JSON.parseObject(get).getJSONArray("data");
//
//        List<RefondMoneyVo> refondMoneyVos = data.toJavaList(RefondMoneyVo.class);
//
//        data.toJavaList(RefondMoneyVo.class);


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

