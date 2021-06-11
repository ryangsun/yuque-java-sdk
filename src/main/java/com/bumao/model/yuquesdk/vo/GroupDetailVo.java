package com.bumao.model.yuquesdk.vo;

import com.bumao.model.yuquesdk.domain.GroupAbilities;
import com.bumao.model.yuquesdk.domain.GroupDetailSerializer;
import lombok.Data;

@Data
public class GroupDetailVo {
    private GroupAbilities abilities;
    private GroupDetailSerializer data;
}
