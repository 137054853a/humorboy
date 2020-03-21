package com.humorboy.springbootmybatisplus.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2020-03-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("contract_manager")
public class Manager extends Model<Manager> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 合同编号
     */
    private String contractNum;

    /**
     * 签订日期
     */
    private LocalDateTime signDate;

    /**
     * 甲方
     */
    private String partyA;

    /**
     * 乙方
     */
    private String partyB;

    /**
     * 合同周期
     */
    private String projectCycle;

    /**
     * 合同金额
     */
    private BigDecimal contractAmount;

    /**
     * 费用类型，劳务费，服务费
     */
    private String amountType;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
