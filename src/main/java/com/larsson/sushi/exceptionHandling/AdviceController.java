package com.larsson.sushi.exceptionHandling;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class AdviceController {

    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");






    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(BusinessException exc){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),getTimeStamp());
        System.out.println(HttpStatus.resolve(HttpStatus.BAD_REQUEST.value()));
        return  new ResponseEntity<>(response,HttpStatus.resolve(response.getStatus()));

    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(NoSuchElementException exc){
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),getTimeStamp());
        return  new ResponseEntity<>(response,HttpStatus.resolve(response.getStatus()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException exc){
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),getTimeStamp());
        return  new ResponseEntity<>(response,HttpStatus.resolve(response.getStatus()));
    }

    private String getTimeStamp(){
        return FORMATTER.format(new Date(System.currentTimeMillis()));
    }

}
