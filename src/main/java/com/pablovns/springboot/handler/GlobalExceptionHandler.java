package com.pablovns.springboot.handler;

import com.pablovns.springboot.enums.ResponseStatus;
import com.pablovns.springboot.exceptions.BusinessException;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.UndeclaredThrowableException;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private HttpHeaders headers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }

    private ResponseError responseError(String message, HttpStatus statusCode, String code) {
        return ResponseError.builder()
                .error(message)
                .statusCode(statusCode.value())
                .status(ResponseStatus.ERROR)
                .code(code)
                .timestamp(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<Object> handleGeneral(Exception e, WebRequest request) {
        if (e instanceof UndeclaredThrowableException exception) {
            return handleBusinessException((BusinessException) exception.getUndeclaredThrowable(), request);
        } else {
            String message = messageSource.getMessage("error.server", new Object[]{e.getMessage()}, Locale.getDefault());
            logger.error("Server error: ", e);  // Logando o erro
            ResponseError error = responseError(message, HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
            return handleExceptionInternal(e, error, headers(), HttpStatus.INTERNAL_SERVER_ERROR, request);
        }
    }

    @ExceptionHandler({BusinessException.class})
    private ResponseEntity<Object> handleBusinessException(BusinessException e, WebRequest request) {
        logger.warn("Business exception: ", e);
        ResponseError error = responseError(e.getMessage(), HttpStatus.BAD_REQUEST, "BUSINESS_ERROR");
        return handleExceptionInternal(e, error, headers(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    private ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException e, WebRequest request) {
        logger.error("Data integrity violation: ", e);
        String message = messageSource.getMessage("error.dataIntegrityViolation", new Object[]{e.getMessage()}, Locale.getDefault());
        ResponseError error = responseError(message, HttpStatus.UNPROCESSABLE_ENTITY, "DATA_INTEGRITY_VIOLATION");
        return handleExceptionInternal(e, error, headers(), HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
