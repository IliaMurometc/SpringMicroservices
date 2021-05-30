package com.example.demo.exceptions;

import com.example.demo.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler( value = {Exception.class})
    public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(
            ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //TODO Need to write endpoints and/or tests to demonstrate the work each of @ExceptionHandler
    @ExceptionHandler( value = {NullPointerException.class})
    public ResponseEntity<Object> handleAnyException_rewrite(Exception ex, WebRequest request) {
        String errorMessageDescription = ex.getLocalizedMessage();
        if(errorMessageDescription == null) {
            errorMessageDescription = ex.toString();
        }

        ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);
        return new ResponseEntity<>(errorMessage,
                                        new HttpHeaders(),
                                        HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
