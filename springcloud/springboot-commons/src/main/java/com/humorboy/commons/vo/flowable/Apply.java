package com.humorboy.commons.vo.flowable;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
 * @author yl
 * @since 2019-09-20
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_apply")
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;
    
    public static final String APPLY_STATUS_ING = "审批中";
    public static final String APPLY_STATUS_SUCCESS = "通过审批";
    public static final String APPLY_STATUS_REFUSE = "未通过";

    @Id
    @GeneratedValue
    private Integer applyId;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endDate;
    
    private Integer days;
    private String type;
    private Integer excuteId;
    private Integer applyUserId;
    private String applyUserName;
    private String status;
    private String remark;
    private Date applyDate;

}
