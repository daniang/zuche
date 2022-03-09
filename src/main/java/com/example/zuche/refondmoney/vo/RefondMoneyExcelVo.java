package com.example.zuche.refondmoney.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadStyle;
import com.alibaba.excel.enums.poi.FillPatternTypeEnum;
import com.example.zuche.event.pojo.Event;
import com.example.zuche.refondmoney.pojo.RefondMoney;
import com.example.zuche.utils.CommonUtils;
import com.example.zuche.utils.DateUtil;
import com.example.zuche.utils.LocalDateTimeUtils;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

@Data
@ContentRowHeight(15)
@HeadStyle(fillPatternType = FillPatternTypeEnum.SOLID_FOREGROUND, fillForegroundColor = 9)
public class RefondMoneyExcelVo {


    /**
     * 还礼的人
     */
    @ExcelProperty("人名")
    private String name;

    /**
     * 还款的金额
     */
    @ExcelProperty("金额")
    private BigDecimal money;

    /**
     * 是否归还 0为为归还1为归还
     */
    @ExcelProperty("是否归还 0：为没有 1为还了")
    private Integer isFond;

    /**
     * 归还的时间
     */
    @ExcelProperty("归还时间")
    private String backTime;

    /**
     * 备注
     */
    @ExcelProperty("备注")
    private String remark;

    /**
     * 事件
     */
    @ExcelProperty("事件")
    private String eventName;

    @ExcelProperty(value = "事件开始的时间")
    private String eventTime;


    public RefondMoneyExcelVo(RefondMoney money, Map<String, Event> map) {

        if (!CollectionUtils.isEmpty(map) && map.containsKey(money.getEventId())) {
            this.eventName = map.get(money.getEventId()).getName();

            LocalDate createTime = map.get(money.getEventId()).getCreateTime();

            this.eventTime = LocalDateTimeUtils.localDateToString(createTime, DateUtil.FORMAT_DATE);
        }
        if (!CommonUtils.isEmpty(money.getBackTime())) {
            this.backTime = LocalDateTimeUtils.localDateToString(money.getBackTime(), DateUtil.FORMAT_DATE);
        }
        this.isFond = money.getIsFond();
        this.money = money.getMoney();
        this.name = money.getName();
        this.remark = money.getRemark();
    }
}
