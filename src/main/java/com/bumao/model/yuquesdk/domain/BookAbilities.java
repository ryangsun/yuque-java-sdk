package com.bumao.model.yuquesdk.domain;

import lombok.Data;

@Data
public class BookAbilities {
    private Boolean read;
    private Boolean update;
    private Boolean destroy;
    private Boolean create;
    private AbilitiesItem doc;
}
