package com.pablovns.springboot.enums;

import lombok.Getter;

@Getter
public enum ResponseStatus {

    ERROR("error"),
    SUCCESS("success"),
    ;

    private final String value;

    ResponseStatus(String value) {
        this.value = value;
    }

}
