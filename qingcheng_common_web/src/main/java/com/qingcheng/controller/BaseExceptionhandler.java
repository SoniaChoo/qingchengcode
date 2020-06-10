package com.qingcheng.controller;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.qingcheng.entity.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionhandler {

    @ExceptionHandler(Exception.class)
//    加上responseBody这个注解之后,就会把异常信息通过json返回
    @ResponseBody
    public  Result error(Exception e) {
        e.printStackTrace();
        System.out.println("调用了异常处理");
        return new Result(1,e.getMessage());
    }
}
