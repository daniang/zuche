package com.example.zuche.tbltynlgeneralcode.dao;

import com.example.zuche.tbltynlgeneralcode.pojo.TblTynlGeneralCode;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统编码表，并发量高，读写表 Mapper 接口
 * </p>
 *
 * @author chengzhang
 * @since 2023-10-08
 */
@Mapper
public interface TblTynlGeneralCodeMapper extends BaseMapper<TblTynlGeneralCode> {

    List<TblTynlGeneralCode> selectParam(@Param("codeValue") String codeValue,@Param("proId") String proId,@Param("codeId") String codeId);
}
