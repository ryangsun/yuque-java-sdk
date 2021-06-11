package com.bumao.model.yuquesdk.domain;

import lombok.Data;

@Data
public class AbilitiesItem {
    private Boolean read;
    private Boolean update;
    private Boolean destroy;
    private Boolean create;
}
