package com.example.zuche.users.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class UserVo {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String username;

    private Integer age;

    private Long phone;

    private String email;
}
