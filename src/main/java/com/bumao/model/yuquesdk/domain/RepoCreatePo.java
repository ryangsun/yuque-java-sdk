package com.bumao.model.yuquesdk.domain;

import lombok.Data;

@Data
public class RepoCreatePo {
    /**
     * 仓库名称
     */
    private String name;
    /**
     * slug
     */
    private String slug;
    /**
     * 说明
     */
    private String description;
    /**
     * public
     * 0 私密,
     * 1 所有人可见,
     * 2 空间成员可见，
     * 3 空间所有人（含外部联系人）可见
     * 4 知识库成员可见
     */
    private Integer public_id;
    /**
     * ’Book 文库, ‘Design’ 画板, 请注意大小写
     */
    private String type;
}
