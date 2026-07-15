package com.blake.malllite.exception;

import com.blake.malllite.common.Result;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    public Result<String> handlerException(Exception e){

        return Result.error(500,e.getMessage());
    }
}
