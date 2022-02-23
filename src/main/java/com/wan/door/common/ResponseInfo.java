package com.wan.door.common;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ResponseInfo {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //响应状态
    private int code;

    //总数
    private Integer count;

    //响应错误消息
    private String msg;

    //响应错误消息
    private  String error;

    //响应业务参数
    private Object data;

    public static ObjectMapper getMapper(){
        return MAPPER;
    }

    public Integer getCount(){
        return count;
    }

    public void setCount(Integer count){
        this.count=count;
    }

    public int getCode(){
        return code;
    }

    public void setCode(int code){
        this.code=code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg=msg;
    }

    public String getError(){
        return error;
    }

    public void setError(String error){
        this.error=error;
    }

    public Object getData(){
        return data;
    }

    public void setData(Object data){
        this.data=data;
    }
}
