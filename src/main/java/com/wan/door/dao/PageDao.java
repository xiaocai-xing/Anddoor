package com.wan.door.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * @author: create by xiaocai-xing
 * @TODO: todo
 * @description: com.wan.door.dao
 * @date:2022/6/6
 */

@Service
public class PageDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate (JdbcTemplate JdbcTemplate) {
        jdbcTemplate = JdbcTemplate;
    }

    /*新增页面 */
    public  boolean pageadd(Map<String, String> PageData){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Key = "INSERT into`page_element` (`page_name`,`element_name`,`by_type`,`by_value`,`remark`,`create_by`,`create_time`,`update_by`,`update_time`) VALUES(?,?,?,?,?,?,?,?,?);";
        return jdbcTemplate.update(Key,PageData.get("pageName"),PageData.get("elementName"),PageData.get("byType"),PageData.get("byValue"),PageData.get("remark"),PageData.get("createBy"),formatter.format(date),PageData.get("updateBy"),formatter.format(date))>0;
    }


}
