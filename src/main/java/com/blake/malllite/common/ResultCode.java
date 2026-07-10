package com.blake.malllite.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(200, "success"),
    FAILED(500, "failed");
    private final Integer code;
    private final String message;



}
