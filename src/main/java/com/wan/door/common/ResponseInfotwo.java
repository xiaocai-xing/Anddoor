package com.wan.door.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: create by xiaocai-xing
 * @TODO: todo
 * @description: com.wan.door.common
 * @date:2022/6/6
 */
@Data
public class ResponseInfotwo implements Serializable {

    //返回结果
    private Boolean success;
    private Object result;
    private ErrorInfo error;
    public ResponseInfotwo(Boolean success, ErrorInfo error) {
        this.success = success;
        this.error = error;
    }

    public ResponseInfotwo(Boolean success, Object result) {
        this.success = success;
        this.result = result;
    }
}
