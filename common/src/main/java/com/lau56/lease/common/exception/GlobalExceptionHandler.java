package com.lau56.lease.common.exception;

import com.lau56.lease.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Michael
 * @date 2025/6/20 15:54
 * @description 全局异常处理器
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

     @ExceptionHandler(Exception.class)
    public Result handle(Exception e){
         log.error("全局异常处理：", e);
         return Result.fail();
     }
    @ExceptionHandler(LeaseException.class)
    public Result handle(LeaseException e){
        log.error("LeaseException异常处理：", e);
        return Result.fail(e.getCode(),e.getMessage());
    }
}
