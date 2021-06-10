package com.bumao.model.yuquesdk.vo;

import lombok.Data;

/**
 * http响应接驳实体
 */
@Data
public class HttpRespVo {
    private Integer httpCode;
    private String httpStatusLine;
    private String httpContent;
}
