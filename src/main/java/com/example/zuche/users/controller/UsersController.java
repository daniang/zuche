package com.example.zuche.users.controller;


import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.zuche.myexception.BizException;
import com.example.zuche.postgresql.pojo.UserTest;
import com.example.zuche.postgresql.service.IUserTestService;
import com.example.zuche.users.dto.UserDto;
import com.example.zuche.users.pojo.Users;
import com.example.zuche.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-16
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/users/users")
@Slf4j
public class UsersController {


    @Resource

    private IUserTestService userTestService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto) {

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getAccount(), userDto.getPassword());

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "账号或密码错误";
        }

        subject.getSession();

        subject.isAuthenticated();
        subject.getSession().setTimeout(1800000);

        return "login success";
    }


    @PostMapping("/user")
    public boolean insert(@RequestBody Users users) {
        log.info("开始新增");

        if (users.getUsername() == null) {
            throw new BizException("-1", "用户姓名不能为空");
        }
        return true;
    }


    @PutMapping("/user")
    public boolean update(@RequestBody Users users) {
        log.info("开始更新");
        //这里故意造成一个空指针的异常，并且不进行处理
        String str = null;
        str.equals("111");

        return true;
    }


    @DeleteMapping("user")
    public boolean delete(@RequestBody Users users) {
        log.info("开始删除");
        //这里故意造成一个异常，并且不进行处理
        Integer.parseInt("abc123");
        return true;
    }

    @GetMapping("/user")
    public List<Users> findByUser(Users users) {
        log.info("开始查询");
        List<Users> userList = new ArrayList<>();

        Users users1 = new Users();
        users1.setId(1);
        users1.setUsername("xiaoming");
        users1.setAge(18);
        userList.add(users1);

        return userList;

    }


    @GetMapping("/testPost")
    public ResultVo<List<UserTest>> testPost(Users users) {


        List<UserTest> list = userTestService.list();

        return ResultVo.success(list);

    }
}

