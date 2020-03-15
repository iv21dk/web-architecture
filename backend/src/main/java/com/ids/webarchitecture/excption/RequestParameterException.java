package com.ids.webarchitecture.excption;

public class RequestParameterException extends RuntimeException {
    public RequestParameterException(String msg) {
        super(msg);
    }

    public RequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
