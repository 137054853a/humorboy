package com.practise.springbootes.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(indexName = "es_user_info")
public class UserInfo {
    @Id
    private String id;
    private String name;
    private String deptNo;
    private Integer salary;
    private String fromArea;

}
