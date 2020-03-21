package com.humorboy.springbootmybatisplus.vo;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class CostApplication extends Model<CostApplication> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 合同编码
     */
    private String contractNum;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 合同金额
     */
    private String contractAmount;

    /**
     * 银行账户名
     */
    private String accountTitle;

    /**
     * 账户卡号
     */
    private String accountNumber;

    /**
     * 状态，提交，审核，审核失败，通过
     */
    private String status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
