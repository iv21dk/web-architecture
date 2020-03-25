package com.ids.webarchitecture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.LOCKED )
public class LockedException extends RuntimeException {
    public LockedException(String message) {
        super(message);
    }
}
