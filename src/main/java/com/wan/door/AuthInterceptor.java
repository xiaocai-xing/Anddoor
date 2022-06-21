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
import java.text.SimpleDateFormat;
import java.util.Date;

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
        try{
            if(!userService.QueryName(token.ParsingToken(request.getHeader("token")).get("name"))){
                result.setCode(getUSER_NOT_LOGIN_CODE());
                result.setError(getUSER_NOT_LOGIN());
                out = response.getWriter();
                out.append(JSON.toJSONString(result));
                return false;
            }
            SimpleDateFormat timeNow = new SimpleDateFormat("yyyyMMddHHmmss");
            Date date = new Date();
            String timeOut = token.ParsingToken(request.getHeader("token")).get("timeOut");
            String time = timeNow.format(date);
            if(timeOut.compareTo(time)<0){
                result.setCode(getLOGIN_TIMEOUT_CODE());
                result.setError(getLOGIN_TIMEOUT());
                out = response.getWriter();
                out.append(JSON.toJSONString(result));
                return false;
            }
        }catch (Exception e){
            result.setCode(getFAIL_CODE());
            result.setError(getMISS_HEADER());
            out = response.getWriter();
            out.append(JSON.toJSONString(result));
            return false;
        }

        return true;
    }
}
