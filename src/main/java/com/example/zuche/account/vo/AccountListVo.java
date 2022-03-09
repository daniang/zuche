package com.example.zuche.account.vo;

import com.example.zuche.account.pojo.Account;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @desc :   账号listvo
 * @Author : chengzhang
 * @Date : 2022/1/21 14:22
 */
@Data
@ApiModel("账号listvo")
public class AccountListVo {

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("账号")
    private String account;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty("链接")
    private String url;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("修改时间")
    private LocalDateTime updateTime;

    public AccountListVo(Account account) {
        this.name = account.getName();
        this.account = account.getAccount();
        this.password = account.getPassword();
        this.createTime = account.getCreateTime();
        this.url = account.getUrl();
        this.remark = account.getRemark();
        this.updateTime = account.getUpdateTime();


    }
}
