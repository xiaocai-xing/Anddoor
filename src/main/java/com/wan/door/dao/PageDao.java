package com.wan.door.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
        return jdbcTemplate.update(Key,PageData.get("page_name"),PageData.get("element_name"),PageData.get("by_type"),PageData.get("by_value"),PageData.get("remark"),PageData.get("create_by"),formatter.format(date),PageData.get("update_by"),formatter.format(date))>0;
    }

    public List<Map<String, Object>> QueryPageElementList(){
        int page = 1;
        int limit = 50;
        String Key = "SELECT `page_name`,`element_name`,`by_type`,`by_value`,`remark`,`create_by`,`create_time`,`update_by`,`update_time` FROM `page_element` LIMIT ? , ?;";
        return jdbcTemplate.queryForList(Key,(page-1)*limit,page*limit);
    }


    public boolean QueryPageName(String pagename) {
        String Key ="SELECT COUNT(*) FROM `page_element` WHERE NAME=? ;";
        return !Objects.equals(jdbcTemplate.queryForObject(Key, String.class, pagename), "0");
    }

    public boolean DelpageElements(String pagename) {
        String Key = "DELETE FROM `page_element` WHERE `name`=? ;";
        return jdbcTemplate.update(Key, pagename) != 0;
    }
}
