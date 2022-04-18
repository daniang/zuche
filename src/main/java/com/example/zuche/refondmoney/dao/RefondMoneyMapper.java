package com.example.zuche.refondmoney.dao;

import com.example.zuche.refondmoney.pojo.RefondMoney;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-17
 */
@Mapper
public interface RefondMoneyMapper extends BaseMapper<RefondMoney> {


    List<Map<String, Object>> getAllMap();

}
