package com.bumao.model.yuquesdk.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * https://www.yuque.com/yuque/developer/groupuserserializer
 */
@Data
public class GroupUserSerializer {
    /**
     * GroupUser Id
     */
    private String id;
    /**
     *  - 团队编号
     */
    private String group_id;
    /**
     *  - 团队信息 <UserSerializer>
     */
    private GroupSerializer group;
    /**
     * - 用户编号
     */
    private String user_id;
    /**
     *  - 用户信息 <UserSerializer>
     */
    private UserSerializer user;
    /**
     *  - 角色 [0 - Owner, 1 - Member]
     */
    private String role;
    /**
     * status
     */
    private Integer status;
    /**
     *  - 创建时间
     */
    private LocalDateTime created_at;
    /**
     *  - 更新时间
     */
    private LocalDateTime updated_at;
}
