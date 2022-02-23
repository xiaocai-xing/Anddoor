package com.wan.door.controller;

import com.wan.door.common.ResponseCode;
import com.wan.door.common.ResponseInfo;
import com.wan.door.dao.UserDao;
import com.wan.door.data.User.AccountLogin;
import com.wan.door.data.User.UserAdd;
import com.wan.door.data.User.UserDel;
import com.wan.door.service.token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@ResponseBody
public class User extends ResponseCode{

    private UserDao userService;

    @Autowired
    public void setUserService (UserDao userService) {
        this.userService = userService;
    }

    @RequestMapping("/AccountLogin")
    public ResponseInfo Login(@RequestBody @Validated AccountLogin accountLogin, HttpSession session){
        ResponseInfo result = new ResponseInfo();
        if(!userService.QueryName(accountLogin.getNameUser())){
            result.setCode(getFAIL_CODE());
            result.setError(getUSER_NAME_NOT_FOUND());
        }else {
            if(!userService.QueryPass(accountLogin.getNameUser(),accountLogin.getPasswordUser())){
                result.setCode(getFAIL_CODE());
                result.setError(getUSER_PASSWORD_ERROR());
            }else {
                List<Map<String, Object>> roleList = userService.QueryNameRole(accountLogin.getNameUser());
                session.setAttribute("NameUser",accountLogin.getNameUser());
                session.setAttribute("role",roleList.get(0).get("rank"));
                Map<String,String> temp = new HashMap<>();
                temp.put("token", token.CreateToken(session.getId(),accountLogin.getNameUser(), (String) roleList.get(0).get("rank")));
                result.setData(temp);
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getSUCCESS());
            }
        }
        return result;
    }

    @RequestMapping("/UserList")
    public ResponseInfo UserList(){
        ResponseInfo result = new ResponseInfo();
        List<Map<String, Object>> Userinfo = userService.QueryUser();
        result.setData(Userinfo);
        result.setCode(getSUCCESS_CODE());
        result.setMsg(getSUCCESS());
        return result;
    }

    @RequestMapping("/UserAdd")
    public ResponseInfo UserAdd(@RequestBody @Validated UserAdd userAdd){
        ResponseInfo result = new ResponseInfo();
        if(userService.QueryName(userAdd.getName())) {
            result.setCode(getFAIL_CODE());
            result.setError(getUSER_NAME_FOUND());
        }else {
            Map<String,String> userInfo = new HashMap<>();
            userInfo.put("name",userAdd.getName());
            userInfo.put("password",userAdd.getPassword());
            userInfo.put("department",userAdd.getDepartment());
            userInfo.put("rank",userAdd.getRank());
            userInfo.put("email",userAdd.getEmail());
            boolean user = userService.UserAdd(userInfo);
            if(!user){
                result.setCode(getFAIL_CODE());
                result.setError("添加用户失败！");
            }else {
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getSUCCESS());
            }
        }
        return result;
    }

    @RequestMapping("/UserDel")
    public ResponseInfo UserDel(@RequestBody @Validated UserDel userDel){
        ResponseInfo result = new ResponseInfo();
        if(!userService.QueryName(userDel.getName())) {
            result.setCode(getFAIL_CODE());
            result.setError(getUSER_NAME_NOT_FOUND());
        }else {
            boolean user = userService.UserDel(userDel.getName());
            if(!user){
                result.setCode(getFAIL_CODE());
                result.setError("删除用户失败！");
            }else {
                result.setCode(getSUCCESS_CODE());
                result.setMsg(getSUCCESS());
            }
        }
        return result;
    }

    @RequestMapping("/LoginOut")
    public ResponseInfo loginOut(HttpSession session, SessionStatus sessionStatus){
        ResponseInfo result = new ResponseInfo();
        session.invalidate();
        sessionStatus.setComplete();
        result.setCode(getSUCCESS_CODE());
        result.setMsg(getSUCCESS());
        return result;
    }
}
