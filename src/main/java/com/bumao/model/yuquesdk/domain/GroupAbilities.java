package com.bumao.model.yuquesdk.domain;

import lombok.Data;

@Data
public class GroupAbilities {
    private Boolean read;
    private Boolean update;
    private Boolean destroy;
    private Boolean create;
    private AbilitiesItem group_user;
    private AbilitiesItem repo;
}
