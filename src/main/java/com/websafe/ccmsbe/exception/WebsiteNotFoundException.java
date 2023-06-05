package com.websafe.ccmsbe.exception;

public class WebsiteNotFoundException extends RuntimeException{
    public WebsiteNotFoundException(String message) {
        super(message);
    }
    public WebsiteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
