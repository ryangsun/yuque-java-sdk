package com.bumao.model.yuquesdk.domain;

//import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @program: yuque
 * @description: 知识库详情
 * @author: zhangchaozhen
 * @create: 2021-01-07 17:03
 **/
@Data
public class BookDetailSerializer {
    /**
     * 仓库编号
     */
    private String id;
    /**
     * 类型 [Book - 文档]
     */
    private String type;
    /**
     * 仓库路径
     */
    private String slug;
    private String name;
    private String namespace;
//    @JsonProperty("user_id")
    private String userId;
    private UserSerializer user;
    private String description;
    /**
     * 目录原文
     */
//    @JsonProperty("toc_yml")
    private String tocYml;
    /**
     * 创建人 User Id
     */
//    @JsonProperty("creator_id")
    private String createId;
    /**
     * 公开状态 [1 - 公开, 0 - 私密]
     */
//    @JsonProperty("public")
    private Integer publicI;
//    @JsonProperty("items_count")
    private Integer itemsCount;
//    @JsonProperty("likes_count")
    private Integer likeCount;
//    @JsonProperty("watches_count")
    private Integer watchesCount;
//    @JsonProperty("created_at")
    private LocalDateTime createAt;
//    @JsonProperty("updated_at")
    private LocalDateTime updateAt;
}
