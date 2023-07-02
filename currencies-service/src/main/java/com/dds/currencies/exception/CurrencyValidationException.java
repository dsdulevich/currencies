package com.dds.currencies.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CurrencyValidationException extends RuntimeException {

    public CurrencyValidationException(String message) {
        super(message);
    }
}
