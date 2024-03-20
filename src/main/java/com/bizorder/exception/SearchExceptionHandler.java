package com.bizorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SearchExceptionHandler {

    @ExceptionHandler(value = {SearchNotFoundException.class})
    public ResponseEntity<Object> handleCustomerNotFoundException(SearchNotFoundException customerNotFoundException){
        SearchException customerException = new SearchException(
            customerNotFoundException.getMessage(),
            customerNotFoundException.getCause(),
            HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(customerException, HttpStatus.NOT_FOUND);
    }
}
