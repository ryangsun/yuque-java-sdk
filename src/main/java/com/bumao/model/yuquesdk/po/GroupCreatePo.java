package com.bumao.model.yuquesdk.po;

import lombok.Data;

@Data
public class GroupCreatePo {
    /**
     * 组织名称
     */
    private String name;
    /**
     * login
     */
    private String login;
    /**
     * 介绍
     */
    private String description;
}
