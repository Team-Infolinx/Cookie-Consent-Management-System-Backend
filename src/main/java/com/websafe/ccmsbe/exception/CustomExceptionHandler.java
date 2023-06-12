package com.websafe.ccmsbe.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.swing.text.html.parser.Entity;
import java.time.ZonedDateTime;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {WebsiteNotFoundException.class})
    public ResponseEntity<Object> handleWebsiteNotFoundException(
            WebsiteNotFoundException exception
    ) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(
                exception.getMessage(),
                notFound,
                ZonedDateTime.now());
        return new ResponseEntity<>(customException, notFound);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<Object> handleAccessDeniedException(
            AccessDeniedException exception
    ) {
        HttpStatus forbidden = HttpStatus.FORBIDDEN;
        CustomException customException = new CustomException(
                exception.getMessage(),
                forbidden,
                ZonedDateTime.now());
        return new ResponseEntity<>(customException, forbidden) ;
    }

    @ExceptionHandler(value = {CookieCategoryNotFoundException.class})
    public ResponseEntity<Object> handleCookieCategoryNotFoundException(
            CookieCategoryNotFoundException exception
    ) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(
                exception.getMessage(),
                notFound,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(customException, notFound);
    }

    @ExceptionHandler(value = {CookieNotFoundException.class})
    public ResponseEntity<Object> handleCookieNotFoundException(
            CookieNotFoundException exception
    ) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(
                exception.getMessage(),
                notFound,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(customException, notFound);
    }

    @ExceptionHandler(value = {TemplateNotFoundException.class})
    public ResponseEntity<Object> handleTemplateNotFoundException(
            TemplateNotFoundException exception
    ){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        CustomException customException = new CustomException(
                exception.getMessage(),
                notFound,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(customException,notFound);
    }

    @ExceptionHandler(value = {BannerNotFoundException.class})
    public ResponseEntity<Object> handleBannerNotFoundException(
            BannerNotFoundException exception
    ){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        CustomException customException =new CustomException(
                exception.getMessage(),
                notFound,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(customException,notFound);

    }

}
