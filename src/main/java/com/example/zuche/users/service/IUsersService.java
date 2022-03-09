package com.example.zuche.users.service;

import com.example.zuche.users.pojo.Users;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-16
 */
public interface IUsersService extends IService<Users> {



    List<Users> getAll();

    List<Users> getByAge(Integer age);

    Users getUserByName(String name);


    Integer testUsers();



}
