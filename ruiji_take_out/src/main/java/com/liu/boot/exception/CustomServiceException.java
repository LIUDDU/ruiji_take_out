package com.liu.boot.exception;

/**
 * @Author
 * @Date 2023/9/5 9:55
 * @Description 自定义业务异常类
 */
public class CustomServiceException extends RuntimeException {

    public CustomServiceException(String message){
        super(message);
    }
}
