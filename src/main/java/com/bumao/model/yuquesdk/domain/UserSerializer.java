package com.bumao.model.yuquesdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 语雀用户/团队的实体类
 * https://www.yuque.com/yuque/developer/userserializer
 */
@Data
public class UserSerializer {
    /**
     * 用户编号
     */
    private String id;
    /**
     * 类型 [`User`  - 用户, Group - 团队]
     */
    private String type;
    /**
     * 用户个人路径
     */
    private String login;
    /**
     * 昵称
     */
    private String name;
    /**
     * 头像 URL
     */
    private String avatar_url;
    /**
     * 创建时间
     */
    private LocalDateTime created_at;
    /**
     * 更新时间
     */
    private LocalDateTime updated_at;
    /**
     * 描述
     */
    private String description;
    /**
     * books_count
     */
    private Integer books_count;
    /**
     * public_books_count
     */
    private Integer public_books_count;
    /**
     * account_id
     */
    private Long account_id;
    /**
     * public
     */
    @JSONField(name = "public")
    private Integer public_id;
    /**
     * following_count
     */
    private Integer following_count;
    /**
     * followers_count
     */
    private Integer followers_count;
    /**
     * space_id
     */
    private Long space_id;
}
