package com.bumao.model.yuquesdk.vo;

import com.bumao.model.yuquesdk.domain.DocAbilities;
import com.bumao.model.yuquesdk.domain.DocDetailSerializer;
import lombok.Data;

@Data
public class DocDetailVo {
    private DocAbilities abilities;
    private DocDetailSerializer data;
}
