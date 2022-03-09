package com.example.zuche.refondmoney.enums;

import lombok.Getter;

@Getter
public enum FondMoneyEnum {
    notFond(0, "未归还"),

    isFond(1, "已归还");


    private Integer index;

    private String desc;

    FondMoneyEnum(Integer index, String desc) {
        this.index = index;
        this.desc = desc;
    }


}
