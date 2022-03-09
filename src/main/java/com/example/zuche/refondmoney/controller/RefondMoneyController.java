package com.example.zuche.refondmoney.controller;


import com.alibaba.excel.EasyExcel;
import com.example.zuche.refondmoney.dto.RefondAddDto;
import com.example.zuche.refondmoney.dto.RefondMoneyDto;
import com.example.zuche.refondmoney.pojo.RefondMoney;
import com.example.zuche.refondmoney.service.IRefondMoneyService;
import com.example.zuche.refondmoney.vo.RefondMoneyExcelVo;
import com.example.zuche.refondmoney.vo.RefondMoneyVo;
import com.example.zuche.utils.IbdUtils;
import com.example.zuche.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-17
 */
@RestController
@Api(tags = "礼钱")
@RequestMapping("/refondmoney")
@Slf4j
public class RefondMoneyController {

    @Resource
    IRefondMoneyService refondMoneyService;


    @PostMapping("saveOne")
    @ApiOperation("新增还礼记录")
    public ResultVo<Void> saveOne(@RequestBody RefondAddDto dto) {


//        ExcelListener<MoneyExcel> excelListener = new ExcelListener<>();
//        File file = new File("C:\\Users\\Administrator\\Desktop\\礼钱.xlsx");
//        EasyExcel.read(file, MoneyExcel.class, excelListener).sheet().doRead();
//        Map<Integer, MoneyExcel> data = excelListener.getData();
//        List<MoneyExcel> collect = data.values().stream().collect(Collectors.toList());
//        List<RefondMoney> collect1 = collect.stream().map(RefondMoney::new).collect(Collectors.toList());
//        refondMoneyService.saveBatch(collect1);

        RefondMoney refondMoney = new RefondMoney(dto);
        refondMoneyService.save(refondMoney);
        return ResultVo.success();
    }

    @ApiOperation("导出所有的礼钱")
    @PostMapping("downloadExcel")
    public void downloadExcel(HttpServletResponse response) {

        log.info("downloadExcel方法");
        List<RefondMoneyExcelVo> collect2 = refondMoneyService.downloadExcel();
        String fileName = "退礼的金额".concat(LocalDate.now().toString());
        EasyExcel.write(IbdUtils.getOutputStream(fileName, response), RefondMoneyExcelVo.class).sheet(fileName).doWrite(collect2);

    }

    @ApiOperation("获得所有的礼钱")
    @GetMapping("getList")
    public ResultVo<List<RefondMoneyVo>> getList() {
        List<RefondMoneyVo> vos = refondMoneyService.getList();
        return ResultVo.success(vos);
    }


    @ApiOperation("查询一个礼钱")
    @PostMapping("getDetails")
    public ResultVo<List<RefondMoneyVo>> getDetails(@RequestBody RefondMoneyDto dto) {


        List<RefondMoneyVo> vos = refondMoneyService.getDetails(dto);


        return ResultVo.success(vos);
    }

}

