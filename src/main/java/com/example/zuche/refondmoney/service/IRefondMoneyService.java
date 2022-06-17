package com.example.zuche.refondmoney.service;

import com.example.zuche.refondmoney.dto.RefondMoneyDto;
import com.example.zuche.refondmoney.pojo.RefondMoney;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.zuche.refondmoney.vo.RefondMoneyExcelVo;
import com.example.zuche.refondmoney.vo.RefondMoneyVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-17
 */
public interface IRefondMoneyService extends IService<RefondMoney> {


    List<RefondMoneyVo> getList();

    List<RefondMoneyExcelVo> downloadExcel();

    List<RefondMoneyVo> getDetails(RefondMoneyDto dto);
}
