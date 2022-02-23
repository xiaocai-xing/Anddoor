package com.wan.door;
import com.alibaba.fastjson.JSON;
import com.wan.door.common.ResponseCode;
import com.wan.door.common.ResponseInfo;
import com.wan.door.dao.UserDao;
import com.wan.door.service.token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class AuthInterceptor extends ResponseCode implements HandlerInterceptor{

    private UserDao userService;

    @Autowired
    public void setUserService (UserDao userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        ResponseInfo result = new ResponseInfo();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out;
        HttpSession session = request.getSession();
        if(session.getAttribute("NameUser") == null){
            try{
                result.setCode(getUSER_NOT_LOGIN_CODE());
                result.setError(getUSER_NOT_LOGIN());
                out = response.getWriter();
                out.append(JSON.toJSONString(result));
                return false;
            }catch (Exception e){
                e.printStackTrace();
                response.sendError(500);
                return false;
            }
        }
        String name = token.ParsingToken(request.getHeader("token")).get("name");
        System.out.println(name);
        System.out.println("111111111"+ userService.QueryName(name));
//        if(!userService.QueryName(token.ParsingToken(request.getHeader("token")).get("name"))){
//            result.setCode(getUSER_NOT_LOGIN_CODE());
//            result.setError(getUSER_NOT_LOGIN());
//            out = response.getWriter();
//            out.append(JSON.toJSONString(result));
//            return false;
//        }
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(df);
//        if(userService.QueryName(token.ParsingToken(request.getHeader("token")).get("timeOut")>)){
//
//        }

        return true;
    }
}
