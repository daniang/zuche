package com.example.zuche.account.controller;


import com.example.zuche.account.dto.AccountDto;
import com.example.zuche.account.dto.AccountListDto;
import com.example.zuche.account.service.IAccountService;
import com.example.zuche.account.vo.AccountListVo;
import com.example.zuche.account.vo.AccountVo;
import com.example.zuche.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chengzhang
 * @since 2022-01-20
 */
@RestController
@RequestMapping("/account")
@Slf4j
@Api(tags = "账号")
public class AccountController {


    @Resource
    IAccountService accountService;

    @PostMapping("saveOrUpdate")
    @ApiOperation("新增或修改")
    public ResultVo<AccountVo> saveOrUpdate(@RequestBody AccountDto dto) {
        AccountVo vo = accountService.saveOrUpdate(dto);
        return ResultVo.success(vo);
    }


    @PostMapping("getAccountList")
    @ApiOperation("查看")
    public ResultVo<List<AccountListVo>> getAccountList(@RequestBody AccountListDto dto) {

        List<AccountListVo> vos = accountService.getAccountList(dto);

        return ResultVo.success(vos);

    }




}

