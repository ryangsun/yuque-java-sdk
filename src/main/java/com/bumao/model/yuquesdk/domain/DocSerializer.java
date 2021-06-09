package com.bumao.model.yuquesdk.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: yuque
 * @description: 文档列表
 * @author: zhangchaozhen
 * @create: 2021-01-07 19:25
 **/
@Data
public class DocSerializer {
    /**
     * 文档编号
     */
    private String id;
    /**
     * 文档路径
     */
    private String slug;
    /**
     * 标题
     */
    private String title;
    /**
     * 文档创建人 user_id
     */
//    @JsonProperty("user_id")
    private String userId;
    /**
     * 描述了正文的格式 [asl, markdown]
     */
    private String format;
    /**
     * 是否公开 [1 - 公开, 0 - 私密]
     */
//    @JsonProperty("public")
    private Integer publicI;
    /**
     * 状态 [1 - 正常, 0 - 草稿]
     */
    private Integer status;
    /**
     * 喜欢数量
     */
//    @JsonProperty("likes_count")
    private Integer likesCount;
    /**
     * 评论数量
     */
//    @JsonProperty("comments_count")
    private Integer commentsCount;
    /**
     * 文档内容更新时间
     */
//    @JsonProperty("content_updated_at")
    private LocalDateTime contentUpdatedAt;
    /**
     * 仓库编号，就是 repo_id
     */
//    @JsonProperty("book_id")
    private String bookId;
    /**
     * 所属知识库
     */
    private BookDetailSerializer book;
    /**
     * 所属团队（个人）
     */
    private UserSerializer user;
    /**
     * 最后修改人
     */
//    @JsonProperty("last_editor")
    private UserSerializer lastEditor;
    /**
     * 创建时间
     */
//    @JsonProperty("created_at")
    private LocalDateTime createdAt;
    /**
     * 更新时间
     */
//    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
    /**
     *删除时间，未删除为 null
     */
//    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    /**
     * 正文 Markdown 源代码
     */
    private String body;
    /**
     * 草稿 Markdown 源代码
     */
//    @JsonProperty("body_draft")
    private String bodyDraft;
    /**
     * 转换过后的正文 HTML
     */
//    @JsonProperty("body_html")
    private String bodyHtml;
    /**
     * 语雀 lake 格式的文档内容
     */
//    @JsonProperty("body_lake")
    private String bodyLake;
}
