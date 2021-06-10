package com.bumao.model.yuquesdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 语雀用户的实体类
 * https://www.yuque.com/yuque/developer/userdetailserializer
 */
@Data
public class UserDetailSerializer extends UserSerializer {
    /**
     *  企业空间编号
     */
    private String space_id;
    /**
     * 用户账户编号
     */
    private String account_id;
    /**
     * 团队创建人，仅适用于 type - 'Group'
     */
    private String owner_id;

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
    private Integer members_count;
    /**
     * 描述
     */
    private String description;
    /**
     * following_count
     */
    private Integer following_count;
    /**
     * followers_count
     */
    private Integer followers_count;

}
