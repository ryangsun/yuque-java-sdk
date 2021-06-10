package com.bumao.model.yuquesdk.domain;

import lombok.Data;

@Data
public class TocSerializer {
    /**
     * 	节点id
     */
    private String id;
    /**
     * 	节点类型
     */
    private String type;
    /**
     * 	节点名称
     */
    private String title;
    /**
     * 	节点唯一 id
     */
    private String uuid;
    /**
     * 	链接或文档 slug
     */
    private String url;
    /**
     * 	上一个节点 uuid
     */
    private String prev_uuid;
    /**
     * 	下一个节点 uuid
     */
    private String sibling_uuid;
    /**
     * 	第一个子节点 uuid
     */
    private String child_uuid;
    /**
     * 	父亲节点 uuid
     */
    private String parent_uuid;
    /**
     * 	仅文档类型节点，doc id
     */
    private String doc_id;
    /**
     * 	节点层级
     */
    private Integer level;
    /**
     * 	链接是否在新窗口打开，0 在当前页面打开，1 在新窗口打开
     */
    private Integer open_window;
    /**
     * 	节点是否可见，0 不可见，1 可见
     */
    private Integer visible;
}
