package com.example.zuche.refondmoney.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.example.zuche.pojo.MoneyExcel;
import com.example.zuche.refondmoney.dto.RefondAddDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author chengzhang
 * @since 2021-12-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RefondMoney extends Model<RefondMoney> {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "refond_id", type = IdType.AUTO)
    private Integer refondId;

    /**
     * 还礼的人
     */
    private String name;

    /**
     * 还款的金额
     */
    private BigDecimal money;

    /**
     * 是否归还 0为为归还1为归还
     */
    private Integer isFond;

    /**
     * 归还的时间
     */
    private LocalDate backTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 事件
     */
    private String eventId;


    /**
     * 单次新增
     * @param dto
     */
    public RefondMoney(RefondAddDto dto){
        this.name = dto.getName();
        this.eventId = dto.getEventId();
        this.money = dto.getMoney();
        this.remark = dto.getRemark();
        this.isFond = 0;
    }


    @Override
    public Serializable pkVal() {
        return this.refondId;
    }

}
