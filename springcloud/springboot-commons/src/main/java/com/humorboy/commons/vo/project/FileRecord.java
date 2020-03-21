package com.humorboy.commons.vo.project;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_file_record")
public class FileRecord implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "file_id", nullable = false, length = 36)
    private String fileId;
    /**
     * 报销id
     */
    private String expenseId;
    /**
     * 文件上传路径
     */
    private String fileUrl;
    /**
     * 文件大小
     */
    private long fileSize;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
}
