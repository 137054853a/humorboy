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
@Table(name = "t_project_speed")
public class ProjectSpeed implements Serializable {

    @Id
    @GeneratedValue
    private long speedId;
    /**
     * 项目id
     */
    private String projectId;
    /**
     * 阶段名称
     */
    private String speedName;
    /**
     * 阶段排序
     */
    private int speedOrder;
    /**
     * 是否完成
     */
    private boolean speedStatus;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 完成时间
     */
    private LocalDateTime finishTime;
}
