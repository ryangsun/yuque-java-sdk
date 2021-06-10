package com.bumao.model.yuquesdk.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *  https://www.yuque.com/yuque/developer/docdetailserializer
 **/
@Data
public class DocSerializer {
    /**
     * 文档编号
     */
    private String id;
    /**
     * 文档路径
     */
    private String slug;
    /**
     * 标题
     */
    private String title;
    /**
     * 用户/团队编号
     */
    private String user_id;
    /**
     * 描述了正文的格式 [asl, markdown]
     */
    private String format;
    /**
     *  - 公开级别 [0 - 私密, 1 - 公开]
     */
    @JSONField(name="public")
    private Integer public_id;
    /**
     * 状态 [1 - 正常, 0 - 草稿]
     */
    private Integer status;
    /**
     * 赞数量
     */
    private Integer likes_count;
    /**
     * 评论数量
     */
    private Integer comments_count;
    /**
     * 文档内容更新时间
     */
    private LocalDateTime content_updated_at;
    /**
     *  仓库信息 <BookSerializer>，就是 repo 信息
     */
    private BookSerializer book;
    /**
     * 用户/团队信息 <UserSerializer>
     */
    private UserSerializer user;
    /**
     * <UserSerializer> 最后修改人
     */
    private UserSerializer last_editor;
    /**
     * 创建时间
     */
    private LocalDateTime created_at;
    /**
     * 更新时间
     */
    private LocalDateTime updated_at;
}
