package com.bumao.model.yuquesdk.domain;

import lombok.Data;

/**
 * @program: yuque
 * @description: 文档详情返回类
 * @author: zhangchaozhen
 * @create: 2021-01-09 21:49
 **/
@Data
public class DocDetailRes {
    private DocSerializer data;
    private Abilitie abilities;

    @Data
    public static class Abilitie {
        private boolean update;
        private boolean destroy;
    }
}
