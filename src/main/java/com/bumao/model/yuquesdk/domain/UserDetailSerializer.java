package com.bumao.model.yuquesdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 语雀用户的实体类
 * https://www.yuque.com/yuque/developer/userdetailserializer
 */
@Data
public class UserDetailSerializer {
    /**
     * 用户编号
     */
    private String id;
    /**
     *  企业空间编号
     */
    private String space_id;
    /**
     * 用户账户编号
     */
    private String account_id;
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
     *  - 团队创建人，仅适用于 type - 'Group'
     */
//    private String owner_id;
    /**
     * 头像 URL
     */
    private String avatar_url;
    /**
     * 仓库数量
     */
    private Integer books_count;
    /**
     * 公开仓库数量
     */
    private Integer public_books_count;
    /**
     *  - 团队成员数量
     */
//    private Integer members_count;
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

}
