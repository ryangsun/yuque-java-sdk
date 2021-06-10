package com.bumao.model.yuquesdk.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 语雀用户的实体类
 * https://www.yuque.com/yuque/developer/userdetailserializer
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
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
     * 仓库数量
     */
    private Integer books_count;
    /**
     * 公开仓库数量
     */
    private Integer public_books_count;
    /**
     * following_count
     */
    private Integer following_count;
    /**
     * followers_count
     */
    private Integer followers_count;

}
