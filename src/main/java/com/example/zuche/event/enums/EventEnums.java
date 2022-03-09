package com.example.zuche.event.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum EventEnums {
    未删除(0, "删除"),
    已删除(1, "已删除");

    @EnumValue
    private final int index;

    @JsonValue
    private final String desc;

    EventEnums(int index, String desc) {
        this.index = index;
        this.desc = desc;
    }
}
