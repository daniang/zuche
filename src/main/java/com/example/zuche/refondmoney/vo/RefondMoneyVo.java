package com.example.zuche.refondmoney.vo;

import com.example.zuche.event.pojo.Event;
import com.example.zuche.refondmoney.enums.FondMoneyEnum;
import com.example.zuche.refondmoney.pojo.RefondMoney;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @desc :  礼钱
 * @Author : chengzhang
 * @Date : 2022/1/20 14:23
 */
@Data
@ApiModel("礼钱信息")
public class RefondMoneyVo {

    @ApiModelProperty("人名")
    private String name;

    @ApiModelProperty("金额")
    private BigDecimal money;

    @ApiModelProperty("是否归还")
    private String isFond;

    @ApiModelProperty("事件名")
    private String eventName;


    public RefondMoneyVo(RefondMoney x, Map<String, Event> map) {
        this.name = x.getName();
        this.money = x.getMoney();

        switch (x.getIsFond()) {
            case 0:
                this.isFond = FondMoneyEnum.notFond.getDesc();
                break;
            case 1:
                this.isFond = FondMoneyEnum.isFond.getDesc();
                break;
        }

        if (!CollectionUtils.isEmpty(map) && map.containsKey(x.getEventId())) {

            this.eventName = map.get(x.getEventId()).getName();
        }


    }
}
