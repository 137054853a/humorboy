package com.humorboy.commons.vo.project;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_project")
public class ProjectManage implements Serializable {

    /**
     * 项目id
     */
    @Id
    @GeneratedValue
    private long projectId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 0-跟进 1-确定
     */
    private int projectType;
    /**
     * 项目总资金
     */
    private int projectMoney;
    /**
     * 项目阶段
     */
    private String projectSpeed;
    /**
     * 项目负责人
     */
    private String chargePersion;
    /**
     * 项目到期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date maturityDate;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    /**
     * 创建人工号
     */
    private String createUser;
    /**
     * 创建人姓名
     */
    private String createName;
    /**
     * 最后操作时间
     */
    private LocalDateTime operationTime;
    /**
     * 最后操作人工号
     */
    private String operationUser;
    /**
     * 最后操作人姓名
     */
    private String operationName;

}
