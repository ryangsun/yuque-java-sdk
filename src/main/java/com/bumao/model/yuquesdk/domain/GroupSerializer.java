package com.bumao.model.yuquesdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 语雀团队的实体类
 */
@Data
public class GroupSerializer {
    /**
     * 用户编号
     */
    private String id;
    /**
     * Login
     */
    private String login;
    /**
     * 名称
     */
    private String name;
    /**
     * 头像 URL
     */
    private String avatar_url;
    /**
     * 该组织包含几个repo
     */
    private Integer books_count;
    /**
     * 该组织包含几个公开的repo
     */
    private Integer public_books_count;
    /**
     * topics_count
     */
    private Integer topics_count;
    /**
     * public_topics_count
     */
    private Integer public_topics_count;
    /**
     * members_count
     */
    private Integer members_count;
    /**
     * 类型 [`User`  - 用户, Group - 团队]
     */
    private String type;
    /**
     * public
     */
    @JSONField(name ="public")
    private Integer public_id;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    private LocalDateTime created_at;
    /**
     * 更新时间
     */
    private LocalDateTime updated_at;


}
