package com.bumao.model.yuquesdk.domain;

import lombok.Data;

@Data
public class DocCreatePo {
    /**
     * 标题
     */
    private String title;
    /**
     * 文档 Slug
     */
    private String slug;
    /**
     * 	0 - 私密，1 - 公开
     */
    private Integer public_id;
    /**
     * 	支持 markdown 和 lake，默认为 markdown
     */
    private String format;
    /**
     * 	format 描述的正文内容，最大允许 5MB
     */
    private String body;
}
