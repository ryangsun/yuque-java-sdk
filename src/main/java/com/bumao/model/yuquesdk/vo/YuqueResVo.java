package com.bumao.model.yuquesdk.vo;

import lombok.Data;
import netscape.javascript.JSObject;

/**
 * 接驳用中间实体
 */
@Data
public class YuqueResVo {
    private Integer code;
    private String msg;
    private HttpRespVo respVo;
    private JSObject jsonData;
}
