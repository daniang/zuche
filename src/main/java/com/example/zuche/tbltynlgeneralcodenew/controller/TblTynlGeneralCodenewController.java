package com.example.zuche.tbltynlgeneralcodenew.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zuche.account.dto.AccountListDto;
import com.example.zuche.account.vo.AccountListVo;
import com.example.zuche.tbltynlgeneralcode.dao.TblTynlGeneralCodeMapper;
import com.example.zuche.tbltynlgeneralcode.pojo.TblTynlGeneralCode;
import com.example.zuche.tbltynlgeneralcodenew.dao.TblTynlGeneralCodenewMapper;
import com.example.zuche.tbltynlgeneralcodenew.pojo.TblTynlGeneralCodenew;
import com.example.zuche.tbltynlgeneralcodenew.service.ITblTynlGeneralCodenewService;
import com.example.zuche.tbltynlgeneralcodeyy.dao.TblTynlGeneralCodeyyMapper;
import com.example.zuche.tbltynlgeneralcodeyy.pojo.TblTynlGeneralCodeyy;
import com.example.zuche.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统编码表，并发量高，读写表 前端控制器
 * </p>
 *
 * @author chengzhang
 * @since 2023-10-08
 */
@RestController
@Api(tags = "特殊号码测试")
@RequestMapping("/tbltynlgeneralcodenew")
public class TblTynlGeneralCodenewController {

    @Resource
    private TblTynlGeneralCodeMapper tblTynlGeneralCodeMapper;


    @Resource
    private TblTynlGeneralCodeyyMapper tblTynlGeneralCodeyyMapper;


    @Resource
    private TblTynlGeneralCodenewMapper tblTynlGeneralCodenewMapper;


    @Resource
    private ITblTynlGeneralCodenewService tblTynlGeneralCodenewService;




    @ApiOperation("测试特殊号码")
    @GetMapping("getList")
    public ResultVo<List<TblTynlGeneralCodenew>> getList() {
        List<TblTynlGeneralCodeyy> tblTynlGeneralCodeyys = tblTynlGeneralCodeyyMapper.selectList(new QueryWrapper<>());
        List<TblTynlGeneralCodeyy> updateCollect = tblTynlGeneralCodeyys.stream().filter(x-> StringUtils.isNotEmpty(x.getCodeValue())).filter(x -> {

            //查询是否存在 value codetype proId  是否相同 。
            List<TblTynlGeneralCode> tblTynlGeneralCodes = tblTynlGeneralCodeMapper.selectParam(x.getCodeValue(),x.getProId(),x.getCodeId());
            if (CollectionUtils.isEmpty(tblTynlGeneralCodes)) {
//                为空 。查询不到 。就返回。
                return true;
            }
            return false;
        }).collect(Collectors.toList());

        List<TblTynlGeneralCodenew> collectNews = updateCollect.stream().map(x -> {
            TblTynlGeneralCodenew tblTynlGeneralCodenew = new TblTynlGeneralCodenew();
            BeanUtils.copyProperties(x, tblTynlGeneralCodenew);
            tblTynlGeneralCodenew.setTenantId("UNICOM");
            return tblTynlGeneralCodenew;
        }).collect(Collectors.toList());

        boolean b = tblTynlGeneralCodenewService.saveBatch(collectNews);

        return ResultVo.success(collectNews);

    }




}

