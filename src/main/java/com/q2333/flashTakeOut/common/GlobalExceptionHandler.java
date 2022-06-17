package com.q2333.flashTakeOut.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 * 底层基于代理,AOP代理Controller等
 * @author q2333
 * @date 2022/06/14 11:32
 **/
//拦截加了RestController,Controller
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody//封装结果为JSON返回给前端
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Return<String> exceptionHandler
            (SQLIntegrityConstraintViolationException ex){
        log.error(ex.getMessage());
        // ex.getMessage():Duplicate entry '1000' for key 'employee.idx_username'
        if (ex.getMessage().contains("Duplicate entry")){
            String[] split=ex.getMessage().split(" ");//空格
            String msg= "账号"+split[2]+"已存在";
            return Return.error(msg);//传给前端SQL失败

        }
        return Return.error("未知错误");
    }
    @ExceptionHandler(CustomException.class)
    public Return<String> exceptionHandler
            (CustomException ex){
        log.error(ex.getMessage());

        return Return.error(ex.getMessage());
    }
}
