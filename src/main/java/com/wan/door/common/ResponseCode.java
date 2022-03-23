package com.wan.door.common;

public class ResponseCode {
    /*成功返回状态*/
    private int SUCCESS_CODE = 0;
    /*失败返回状态*/
    private int FAIL_CODE = 1;

    private String SUCCESS = "SUCCESS";

    private String USER_NAME_NOT_FOUND = "用户名不存在，请确认后重试！";

    private String USER_PASSWORD_ERROR = "用户密码错误，请确认后重试！";

    private String USER_NOT_LOGIN = "用户未登录，请登录后重试！";

    private int USER_NOT_LOGIN_CODE = 10001;

    private int LOGIN_TIMEOUT_CODE = 10002;

    private String USER_NAME_FOUND = "用户名已存在，请登录后重试！";

    private String LOGIN_TIMEOUT = "用户登录已失效，请重新登录！";

    private String MISS_HEADER = "缺失token参数，请确认请求参数";

    public int getSUCCESS_CODE(){
        return SUCCESS_CODE;
    }

    public void setSUCCESS_CODE(int SUCCESS_CODE){
        this.SUCCESS_CODE=SUCCESS_CODE;
    }

    public int getFAIL_CODE(){
        return FAIL_CODE;
    }

    public void setFAIL_CODE(int FAIL_CODE){
        this.FAIL_CODE=FAIL_CODE;
    }

    public String getSUCCESS(){
        return SUCCESS;
    }

    public void setSUCCESS(String SUCCESS){
        this.SUCCESS=SUCCESS;
    }

    public String getUSER_NAME_NOT_FOUND(){
        return USER_NAME_NOT_FOUND;
    }

    public void setUSER_NAME_NOT_FOUND(String USER_NAME_NOT_FOUND){
        this.USER_NAME_NOT_FOUND=USER_NAME_NOT_FOUND;
    }

    public String getUSER_PASSWORD_ERROR(){
        return USER_PASSWORD_ERROR;
    }

    public void setUSER_PASSWORD_ERROR(String USER_PASSWORD_ERROR){
        this.USER_PASSWORD_ERROR=USER_PASSWORD_ERROR;
    }

    public String getUSER_NOT_LOGIN(){
        return USER_NOT_LOGIN;
    }

    public void setUSER_NOT_LOGIN(String USER_NOT_LOGIN){
        this.USER_NOT_LOGIN=USER_NOT_LOGIN;
    }

    public String getUSER_NAME_FOUND(){
        return USER_NAME_FOUND;
    }

    public void setUSER_NAME_FOUND(String USER_NAME_FOUND){
        this.USER_NAME_FOUND=USER_NAME_FOUND;
    }

    public int getUSER_NOT_LOGIN_CODE(){
        return USER_NOT_LOGIN_CODE;
    }

    public void setUSER_NOT_LOGIN_CODE(int USER_NOT_LOGIN_CODE){
        this.USER_NOT_LOGIN_CODE=USER_NOT_LOGIN_CODE;
    }

    public String getLOGIN_TIMEOUT(){
        return LOGIN_TIMEOUT;
    }

    public void setLOGIN_TIMEOUT(String LOGIN_TIMEOUT){
        this.LOGIN_TIMEOUT=LOGIN_TIMEOUT;
    }

    public int getLOGIN_TIMEOUT_CODE(){
        return LOGIN_TIMEOUT_CODE;
    }

    public void setLOGIN_TIMEOUT_CODE(int LOGIN_TIMEOUT_CODE){
        this.LOGIN_TIMEOUT_CODE=LOGIN_TIMEOUT_CODE;
    }

    public String getMISS_HEADER(){
        return MISS_HEADER;
    }

    public void setMISS_HEADER(String MISS_HEADER){
        this.MISS_HEADER=MISS_HEADER;
    }



}
