package com.liu.boot.exception;

import com.liu.boot.utils.R;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author
 * @Date 2023/9/4 11:07
 * @Description 全局异常处理
 */
@RestControllerAdvice(annotations = {RestController.class, Controller.class, Service.class})
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MySQLIntegrityConstraintViolationException.class)
    public R<String> mySQLIntegrityConstraintViolationExceptionHandler(MySQLIntegrityConstraintViolationException exception){

        log.info("异常:{}",exception.getMessage());

        String message = exception.getMessage();


        if (message.contains("Duplicate entry")){
            String[] split = message.split(" ");
            String msg = "账号" + split[2] + "已存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }

    @ExceptionHandler(CustomServiceException.class)
    public R<String> CustomServiceExceptionHandler(CustomServiceException exception){

        log.info("异常:{}",exception.getMessage());

        return R.error(exception.getMessage());
    }

}
