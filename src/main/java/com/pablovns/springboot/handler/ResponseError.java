package com.pablovns.springboot.handler;

import com.pablovns.springboot.enums.ResponseStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseError {

    private LocalDateTime timestamp = LocalDateTime.now();
    private ResponseStatus status = ResponseStatus.ERROR;
    private int statusCode = 400;
    private String error;

}