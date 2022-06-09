package com.wan.door.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: create by xiaocai-xing
 * @TODO: todo页面元素
 * @description: com.wan.door.dao
 * @date:2022/6/6
 */
@Data
public class PageElement implements Serializable {
    /**
     * id
     */
    private Long id;

    private Long pageId;

    /**
     * 页面名称
     */
    private String pageName;

    /**
     * 元素名称
     */
    private String elementName;

    /**
     * 定位方式
     */
    private String byType;

    /**
     * 定位值
     */
    private String byValue;

    /**
     * 1：有效
     */
//    private String isEnable;

    /**
     * 备注
     */
    private String remark;

    /**
     * createBy
     */
    private String createBy;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * updateBy
     */
    private String updateBy;

    /**
     * updateTime
     */
    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
