package com.bumao.model.yuquesdk.domain;

import lombok.Data;

import java.util.List;

/**
 * @program: yuque
 * @description: 文档列表返回类
 * @author: zhangchaozhen
 * @create: 2021-01-09 18:11
 **/
@Data
public class DocListRes {
    private List<DocSerializer> data;
}
