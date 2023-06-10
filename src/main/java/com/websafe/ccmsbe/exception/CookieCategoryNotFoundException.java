package com.websafe.ccmsbe.exception;

public class CookieCategoryNotFoundException extends RuntimeException{

    public CookieCategoryNotFoundException(String message) {
        super(message);
    }
    public CookieCategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
