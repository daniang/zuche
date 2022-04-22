package com.example.zuche.postgresql.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.zuche.config.DSConfig;
import com.example.zuche.postgresql.pojo.UserTest;
import com.example.zuche.postgresql.dao.UserTestMapper;
import com.example.zuche.postgresql.service.IUserTestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chengzhang
 * @since 2022-04-20
 */
@Service
@DS(DSConfig.POSTGRESQL)
public class UserTestServiceImpl extends ServiceImpl<UserTestMapper, UserTest> implements IUserTestService {

}
