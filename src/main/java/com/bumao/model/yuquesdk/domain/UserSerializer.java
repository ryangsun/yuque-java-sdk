package com.bumao.model.yuquesdk.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 语雀用户的实体类
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


}
