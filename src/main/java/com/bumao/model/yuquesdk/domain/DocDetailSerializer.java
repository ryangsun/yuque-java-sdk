package com.bumao.model.yuquesdk.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 *  https://www.yuque.com/yuque/developer/docdetailserializer
 **/
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class DocDetailSerializer extends DocSerializer {
    /**
     * 仓库编号，就是 repo_id
     */
    private String book_id;
    /**
     * 仓库信息 <BookSerializer>，就是 repo 信息
     */
    private BookSerializer book;
    /**
     * 正文 Markdown 源代码
     */
    private String body;
    /**
     * 草稿 Markdown 源代码
     */
    private String body_draft;
    /**
     * 转换过后的正文 HTML
     */
    private String body_html;
    /**
     * 语雀 lake 格式的文档内容
     */
    private String body_lake;
    /**
     * 文档创建人 User Id
     */
    private String creator_id;
    /**
     * 删除时间，未删除为 null
     */
    private LocalDateTime deleted_at;
}
