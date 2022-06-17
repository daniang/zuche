package com.example.zuche.users.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.zuche.permission.pojo.Permissions;
import com.example.zuche.permission.pojo.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private Integer age;

    private Long phone;

    private String email;

    private String account;

    private String password;

    @TableField(exist = false)
    private String smallName;


    /**
     * 用户对应的角色集合
     */
    @TableField(exist = false)
    private Set<Role> roles;

    @TableField(exist = false)
    private Set<Permissions> permissions;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
