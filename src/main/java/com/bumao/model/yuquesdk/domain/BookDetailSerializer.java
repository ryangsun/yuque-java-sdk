package com.bumao.model.yuquesdk.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 仓库信息
 * https://www.yuque.com/yuque/developer/bookserializer
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BookDetailSerializer extends BookSerializer {
    /**
     * 目录原文
     */
    private String toc_yml;
    /**
     * - 文档数量
     */
    private Integer items_count;
    /**
     *  - 喜欢数量
     */
    private Integer likes_count;
    /**
     *  - 订阅数量
     */
    private Integer watches_count;
}
