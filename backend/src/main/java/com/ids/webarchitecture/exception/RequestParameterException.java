package com.ids.webarchitecture.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( value = HttpStatus.BAD_REQUEST )
public class RequestParameterException extends RuntimeException {
    public RequestParameterException(String msg) {
        super(msg);
    }

    public RequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
