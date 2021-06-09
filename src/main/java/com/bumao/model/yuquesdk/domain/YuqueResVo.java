package com.bumao.model.yuquesdk.domain;

import lombok.Data;
import netscape.javascript.JSObject;

@Data
public class YuqueResVo {
    private Integer code;
    private String msg;
    private HttpRespVo respVo;
    private JSObject jsonData;
}
