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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_project_part")
public class ProjectPart implements Serializable {

    @Id
    @GeneratedValue
    private long partId;
    /**
     * 项目id
     */
    private long projectId;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 用户id
     */
    private long userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 参与备注
     */
    private String partRemark;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
