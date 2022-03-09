package com.example.zuche.users.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.zuche.users.pojo.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-16
 */
@Mapper
public interface UsersMapper extends BaseMapper<Users> {

    @Select("SELECT id,username,age,phone,email FROM users")
    List<Users> getUsers();


    List<Users> getAll();

    List<Users> getByAge(Integer age);

    List<Integer> selectCountList();
}

