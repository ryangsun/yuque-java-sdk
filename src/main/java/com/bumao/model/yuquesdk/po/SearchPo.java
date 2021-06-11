package com.bumao.model.yuquesdk.po;

import lombok.Data;

@Data
public class SearchPo {
    /**
     *  (必传)	资源类型：
     *  topic - 讨论
     *     repo - 知识库
     *     doc - 文档
     *     artboard - 画板
     *     group - 团队
     *     user - 用户
     *     attachment - 附件
     */
    private String type;

    /**
     * (必传)	关键字
     */
    private String q;
    /**
     * 	分页，1、2...
     */
    private Integer offset;
    /**
     * 	搜索与我相关的传递 true
     */
    private Boolean related;
}
