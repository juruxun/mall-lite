package com.blake.malllite.common;


import com.blake.malllite.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Type;
import java.nio.charset.CoderResult;
@Data
@NoArgsConstructor

public class Result<T> {
    private Integer code;
    private String message;
    private T data;

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(
                ResultCode.SUCCESS.getCode(),
                ResultCode.SUCCESS.getMessage(),
                data
        );
    }

    public static <T> Result<T> failed (String message){
        return new Result<>(
                ResultCode.FAILED.getCode(),
                message,
                null
        );
    }



}
