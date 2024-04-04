package com.bizorder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ProblemDetail;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.security.access.AccessDeniedException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {SearchNotFoundException.class})
    public ResponseEntity<Object> handleSearchNotFoundException(SearchNotFoundException searchNotFoundException) {
        SearchException searchException = new SearchException(
            searchNotFoundException.getMessage(),
            searchNotFoundException.getCause(),
            HttpStatus.NOT_FOUND
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(searchException);
    }

    @ExceptionHandler(value = {BadCredentialsException.class, AccountStatusException.class,
        AccessDeniedException.class, SignatureException.class, ExpiredJwtException.class})
    public ResponseEntity<ProblemDetail> handleSecurityException(Exception exception) {
        ProblemDetail errorDetail;

        // NOT PRINTING
        exception.printStackTrace();

        if (exception instanceof BadCredentialsException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "The username or password is incorrect");
            errorDetail.setProperty("Error", "The username or password is incorrect");
        } else if (exception instanceof AccountStatusException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "The account is locked");
            errorDetail.setProperty("Error", "The account is locked");
        } else if (exception instanceof AccessDeniedException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "You are not authorized to access this resource");
            errorDetail.setProperty("Error", "You are not authorized to access this resource");
        } else if (exception instanceof SignatureException || exception instanceof ExpiredJwtException) {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, "The JWT signature is invalid or the token has expired");
            errorDetail.setProperty("Error", "The JWT signature is invalid or the token has expired");
        } else {
            errorDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown internal server error.");
            errorDetail.setProperty("Error", "Internal server error");
        }

        return ResponseEntity.status(errorDetail.getStatus()).body(errorDetail);
    }
}

