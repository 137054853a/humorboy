package com.humorboy.commons.vo.flowable;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author weibo
 * @since 2018-11-21
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_approve")
public class Approve implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Integer id;
    private Integer userId;
    private Date approveDate;
    private String comment;
    private Integer applyId;
    /**
     * 申请是否通过  1   0
     */
    private Boolean approveValue;



    @Override
    public String toString() {
        return "Approve{" +
        ", id=" + id +
        ", userId=" + userId +
        ", approveDate=" + approveDate +
        ", comment=" + comment +
        ", applyId=" + applyId +
        ", approveValue=" + approveValue +
        "}";
    }
}
