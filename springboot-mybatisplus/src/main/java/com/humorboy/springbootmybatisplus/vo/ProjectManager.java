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
public class ProjectManager extends Model<ProjectManager> {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 项目名称
     */
    private String name;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
