package com.bumao.model.yuquesdk.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 语雀团队的实体类
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class GroupDetailSerializer extends GroupSerializer {
    /**
     * space_id
     */
    private String space_id;
    /**
     * organization_id
     */
    private String organization_id;
    /**
     * owner_id
     */
    private String owner_id;
    /**
     * grains_sum
     */
    private String grains_sum;
}
