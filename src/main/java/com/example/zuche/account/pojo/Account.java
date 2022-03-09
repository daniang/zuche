package com.example.zuche.account.pojo;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.UUID;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.example.zuche.account.dto.AccountDto;
import com.example.zuche.utils.UUIDUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author chengzhang
 * @since 2022-01-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@NoArgsConstructor
@ApiModel(value = "Account对象", description = "")
public class Account extends Model<Account> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "账号")
    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "登录链接")
    private String url;

    @ApiModelProperty(value = "是否有效 0无效1有效")
    private Boolean isInvalid;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否删除 0已删除1未删除")
    private Boolean isDelete;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime updateTime;

    public void saveOrUpdate(AccountDto dto) {
        this.id = dto.getId();

        if (StringUtils.isEmpty(dto.getId())) {
            this.id = UUIDUtils.getUUID();
            this.createTime = LocalDateTime.now();

        }

        this.name = dto.getName();
        this.account = dto.getAccount();
        this.password = dto.getPassword();
        this.updateTime = LocalDateTime.now();
        this.url = dto.getUrl();
        this.isInvalid = Boolean.TRUE;
        this.remark = dto.getRemark();
        this.isDelete = Boolean.TRUE;

    }
}
