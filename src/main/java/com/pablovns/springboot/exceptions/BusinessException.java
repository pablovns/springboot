package com.pablovns.springboot.exceptions;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String mensagem, Object ... params) {
        super(String.format(mensagem, params));
    }

    public BusinessException(String mensagem, Throwable cause) {
        super(mensagem, cause);
    }
}
