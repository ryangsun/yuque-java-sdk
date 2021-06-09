package com.bumao.model.yuquesdk.domain;

import lombok.Data;

@Data
public class HttpRespVo {
    private Integer httpCode;
    private String httpStatusLine;
    private String httpContent;
}
