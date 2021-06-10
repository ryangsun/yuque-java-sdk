package com.bumao.model.yuquesdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *  https://www.yuque.com/yuque/developer/docdetailserializer
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
     * book_id - 仓库编号，就是 repo_id
     */
    private String book_id;
    /**
     *  仓库信息 <BookSerializer>，就是 repo 信息
     */
    private BookSerializer book;
    /**
     * 用户/团队编号
     */
    private String user_id;
    /**
     * 用户/团队信息 <UserSerializer>
     */
    private UserSerializer user;
    /**
     * 描述了正文的格式 [asl, markdown]
     */
    private String format;
    /**
     *  - 正文 Markdown 源代码
     */
    private String body;
    /**
     *  - 草稿 Markdown 源代码
     */
    private String body_draft;
    /**
     *  - 转换过后的正文 HTML
     */
    private String body_html;
    /**
     *  - 语雀 lake 格式的文档内容
     */
    private String body_lake;
    /**
     *  - 文档创建人 User Id
     */
    private String creator_id;
    /**
     *  - 公开级别 [0 - 私密, 1 - 公开]
     */
    @JSONField(name="public")
    private Integer public_id;
    /**
     * 状态 [1 - 正常, 0 - 草稿]
     */
    private Integer status;
    /**
     * 赞数量
     */
    private Integer likes_count;
    /**
     * 评论数量
     */
    private Integer comments_count ;
    /**
     * 文档内容更新时间
     */
    private LocalDateTime content_updated_at;
    /**
     * 删除时间，未删除为 null
     */
    private LocalDateTime deleted_at;
    /**
     * 创建时间
     */
    private LocalDateTime created_at;
    /**
     * 更新时间
     */
    private LocalDateTime updated_at;
}
