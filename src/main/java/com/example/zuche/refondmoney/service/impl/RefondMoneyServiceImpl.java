package com.example.zuche.refondmoney.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zuche.event.pojo.Event;
import com.example.zuche.event.service.IEventService;
import com.example.zuche.refondmoney.dto.RefondMoneyDto;
import com.example.zuche.refondmoney.pojo.RefondMoney;
import com.example.zuche.refondmoney.dao.RefondMoneyMapper;
import com.example.zuche.refondmoney.service.IRefondMoneyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zuche.refondmoney.vo.RefondMoneyExcelVo;
import com.example.zuche.refondmoney.vo.RefondMoneyVo;
import com.example.zuche.utils.CommonUtils;
import com.example.zuche.utils.IbdUtils;
import com.example.zuche.vo.ResultVo;
import org.apache.catalina.WebResourceRoot;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
public class RefondMoneyServiceImpl extends ServiceImpl<RefondMoneyMapper, RefondMoney> implements IRefondMoneyService {

    @Resource
    IEventService eventService;


    @Override
    public List<RefondMoneyVo> getList() {
        List<RefondMoney> refondMonies = this.list();
        List<String> collect = refondMonies.stream().map(x -> x.getEventId()).distinct().collect(Collectors.toList());
        List<Event> events1 = CommonUtils.emptyCondThenReturnEmptyList(collect, () -> {
            List<Event> events = eventService.listByIds(collect);
            return events;
        });
        Map<String, Event> map = events1.stream().collect(Collectors.toMap(Event::getEventId, x -> x));
        List<RefondMoneyVo> collect1 = refondMonies.stream().map(x -> new RefondMoneyVo(x, map)).collect(Collectors.toList());
        return collect1;
    }

    @Override
    public List<RefondMoneyExcelVo> downloadExcel() {

        List<RefondMoney> list = this.list();

        List<String> collect = list.stream().map(RefondMoney::getEventId).distinct().collect(Collectors.toList());

        if (CollectionUtils.isEmpty(collect)) {
            ResultVo.fail("无数据");
        }
        List<Event> events = eventService.listByIds(collect);

        Map<String, Event> collect1 = events.stream().collect(Collectors.toMap(Event::getEventId, x -> x));

        List<RefondMoneyExcelVo> collect2 = list.stream().map(x -> new RefondMoneyExcelVo(x, collect1)).collect(Collectors.toList());
        return collect2;
    }

    @Override
    public List<RefondMoneyVo> getDetails(RefondMoneyDto dto) {

        List<RefondMoney> refondMonies = this.baseMapper.selectList(new QueryWrapper<RefondMoney>().lambda()
                .like(!StringUtils.isEmpty(dto.getName()), RefondMoney::getName, dto.getName()));

        List<Event> events = eventService.getBaseMapper().selectList(new QueryWrapper<Event>().lambda()
                .like(!StringUtils.isEmpty(dto.getEventName()), Event::getName, dto.getEventName()));

        Map<String, Event> eventMap = events.stream().collect(Collectors.toMap(Event::getEventId, x -> x));

        //获取查询出来的事件id
        List<String> eventIds = events.stream().map(x -> x.getEventId()).collect(Collectors.toList());

        List<RefondMoney> collect = refondMonies.stream().filter(x -> eventIds.contains(x.getEventId())).collect(Collectors.toList());

        List<RefondMoneyVo> refondMoneyVos = collect.stream().map(x -> new RefondMoneyVo(x, eventMap)).collect(Collectors.toList());


        return refondMoneyVos;
    }
}
