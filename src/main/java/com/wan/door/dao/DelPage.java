package com.wan.door.dao;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: create by xiaocai-xing
 * @TODO: todo
 * @description: com.wan.door.dao
 * @date:2022/6/9
 */

@Data
public class DelPage implements Serializable {

    //删除的页面元素

    private String  Page_name;
}
