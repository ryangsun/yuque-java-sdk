package com.bumao.model.yuquesdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 仓库信息
 * https://www.yuque.com/yuque/developer/bookdetailserializer
 * https://www.yuque.com/yuque/developer/bookserializer
 * 以上两个整合
 */
@Data
public class BookSerializer {
    /**
     *  - 仓库编号
     */
    private String id;
    /**
     *  - 类型 [Book - 文档]
     */
    private String type;
    /**
     *  - 仓库路径
     */
    private String slug;
    /**
     *  - 名称
     */
    private String name;
    /**
     *  - 仓库完整路径 user.login/book.slug
     */
    private String namespace;
    /**
     *  - 所属的团队/用户编号
     */
    private String user_id;
    /**
     *  - <UserSerializer>
     */
    private UserSerializer user;
    /**
     *  - 介绍
     */
    private String description;
    /**
     * 目录原文
     */
    private String toc_yml;
    /**
     *  - 创建人 User Id
     */
    private String creator_id;
    /**
     *  - 公开状态 [1 - 公开, 0 - 私密]
     */
    @JSONField(name="public")
    private Integer public_id;
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
    /**
     *  - 创建时间
     */
    private LocalDateTime created_at;
    /**
     *  - 更新时间
     */
    private LocalDateTime updated_at;
}
