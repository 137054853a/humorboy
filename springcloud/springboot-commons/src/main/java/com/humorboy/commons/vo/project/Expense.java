package com.humorboy.commons.vo.project;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_expense")
public class Expense implements Serializable {

    @Id
    @GeneratedValue
    private Long expenseId;
    /**
     * 项目id
     */
    private long projectId;
    /**
     * 出差地址
     */
    private String expenseArea;
    /**
     * 费用总金额
     */
    private int expenseTotal;
    /**
     * 总金额中文大写
     */
    private String totalChinese;
    /**
     * 备注
     */
    private String expenseRemark;
    /**
     * 是否审核通过
     */
    private int expenseStatus;
    /**
     * 创建人id
     */
    private long createId;
    /**
     * 创建人姓名
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
