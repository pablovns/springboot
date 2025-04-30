package com.pablovns.springboot.handler;

import com.pablovns.springboot.enums.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class ResponseError {

    private final LocalDateTime timestamp;

    private final ResponseStatus status;

    private final int statusCode;

    private final String error;

    private final String code;

    public static ResponseError of(String error, int statusCode) {
        return ResponseError.builder()
                .error(error)
                .statusCode(statusCode)
                .code("UNKNOWN_ERROR")
                .build();
    }
}
