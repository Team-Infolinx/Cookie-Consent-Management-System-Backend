package com.websafe.ccmsbe.exception;

public class CookieNotFoundException extends RuntimeException{
    public CookieNotFoundException(String message) {
        super(message);
    }
    public CookieNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
