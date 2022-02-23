package com.wan.door;

import com.wan.door.common.ResponseCode;
import com.wan.door.common.ResponseInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;


@ControllerAdvice
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseCode {


    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseInfo exceptionHandler(MethodArgumentNotValidException e){
        BindingResult bindingResult = e.getBindingResult();
        ResponseInfo resultMessage = new ResponseInfo();
        if(bindingResult.hasErrors()){
            for (ObjectError error : bindingResult.getAllErrors()) {
                resultMessage.setError(error.getDefaultMessage());
                resultMessage.setCode(getFAIL_CODE());
            }
        }
        return resultMessage;
    }

    @ExceptionHandler(Exception.class)
    public ResponseInfo exception(Exception e){
        ResponseInfo resultMessage = new ResponseInfo();
        resultMessage.setError("未知原因错误:"+e);
        resultMessage.setCode(getFAIL_CODE());
        return resultMessage;
    }
}
