package com.wan.door.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserDao {


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate (JdbcTemplate JdbcTemplate) {
        jdbcTemplate = JdbcTemplate;
    }

    /*用户名查询*/
    public boolean QueryName(String username){
        String Key ="SELECT COUNT(*) FROM `user` WHERE NAME=? ;";
        return !Objects.equals(jdbcTemplate.queryForObject(Key, String.class, username), "0");
    }

    /*用户名和密码查询*/
    public boolean QueryPass(String username,String password){
        String Key ="SELECT COUNT(*) FROM `user` WHERE NAME=? and `password`=?;";
        return !Objects.equals(jdbcTemplate.queryForObject(Key, String.class, username,password), "0");
    }

    /*用户名查询角色*/
    public List<Map<String, Object>> QueryNameRole(String username){
        String Key ="SELECT * FROM `user` WHERE NAME=? ;";
        return jdbcTemplate.queryForList(Key,username);
    }

    /*查询用户列表 */
    public List<Map<String, Object>> QueryUser(){
        int page = 1;
        int limit = 50;
        String Key = "SELECT `name`,`department`,`rank`,`email`,`CreationTime` FROM `user` LIMIT ? , ?;";
        return jdbcTemplate.queryForList(Key,(page-1)*limit,page*limit);
    }

    /*新增用户 */
    public boolean UserAdd(Map<String, String> UserData){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String Key = "INSERT into`user` (`name`,`password`,`CreationTime`,`department`,`rank`,`email`) VALUES(?,?,?,?,?,?);";
        return jdbcTemplate.update(Key,UserData.get("name"),UserData.get("password"),formatter.format(date),
                UserData.get("department"),UserData.get("rank"),UserData.get("email"))>0;
    }

    /*删除用户 */
    public boolean UserDel(String User){
        String Key = "DELETE FROM `user` WHERE `name`=? ;";
        return jdbcTemplate.update(Key, User) != 0;

    }
}
