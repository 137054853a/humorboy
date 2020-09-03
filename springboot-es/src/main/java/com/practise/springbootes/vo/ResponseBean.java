package com.practise.springbootes.vo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ResponseBean {
    @Builder.Default
    private int code = 200;
    @Builder.Default
    private String msg = "操作成功";
    private Object data;
}
