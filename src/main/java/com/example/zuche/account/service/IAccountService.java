package com.example.zuche.account.service;

import com.example.zuche.account.dto.AccountDto;
import com.example.zuche.account.dto.AccountListDto;
import com.example.zuche.account.pojo.Account;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.zuche.account.vo.AccountListVo;
import com.example.zuche.account.vo.AccountVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chengzhang
 * @since 2022-01-20
 */
public interface IAccountService extends IService<Account> {

    AccountVo saveOrUpdate(AccountDto dto);

    List<AccountListVo> getAccountList(AccountListDto dto);
}
