package com.humorboy.practise.vo;

import com.humorboy.practise.annotation.Column;
import lombok.Data;

@Data
public class ExcelInfo {
    @Column("表头字段1")
    private String field1;
    @Column("表头字段1")
    private String field11;
    @Column("表头字段1")
    private String field12;
    @Column("表头字段1")
    private String field13;
    @Column("表头字段1")
    private String field14;
}
