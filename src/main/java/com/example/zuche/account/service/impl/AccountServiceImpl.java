package com.example.zuche.account.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zuche.account.dto.AccountDto;
import com.example.zuche.account.dto.AccountListDto;
import com.example.zuche.account.pojo.Account;
import com.example.zuche.account.dao.AccountMapper;
import com.example.zuche.account.service.IAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.zuche.account.vo.AccountListVo;
import com.example.zuche.account.vo.AccountVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author chengzhang
 * @since 2022-01-20
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {


    @Override
    public AccountVo saveOrUpdate(AccountDto dto) {

        Account account = new Account();

        if (!StringUtils.isEmpty(dto.getId())) {
            account = this.getById(dto.getId());
        }
        account.saveOrUpdate(dto);
        this.saveOrUpdate(account);
        return new AccountVo(account.getId(), account.getName());
    }

    @Override
    public List<AccountListVo> getAccountList(AccountListDto dto) {

        List<Account> accounts = this.list(new QueryWrapper<Account>().lambda()
                .like(StringUtils.isNotBlank(dto.getName()), Account::getName, dto.getName()).or().like(StringUtils.isNotBlank(dto.getName()), Account::getRemark, dto.getName()));
        List<AccountListVo> accountListVos = accounts.stream().map(x -> new AccountListVo(x)).collect(Collectors.toList());


        return accountListVos;
    }




}
