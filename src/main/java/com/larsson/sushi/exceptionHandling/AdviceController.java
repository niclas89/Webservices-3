package com.larsson.sushi.exceptionHandling;

import com.larsson.sushi.service.BookingServiceImpl;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

@ControllerAdvice
public class AdviceController {

    private final SimpleDateFormat FORMATTER = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");

    private static final Logger adviceLogger = Logger.getLogger(AdviceController.class);






    @ExceptionHandler
    public ResponseEntity<String> handleException(BusinessException exc){
        //ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),getTimeStamp());
        //return  new ResponseEntity<>(response,HttpStatus.resolve(response.getStatus()));
        adviceLogger.warn(exc.getMessage() + " "  + exc.getCause());
        return  ResponseEntity.badRequest().body(exc.getMessage());

    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(NoSuchElementException exc){
        //ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), exc.getMessage(),getTimeStamp());
        //return  new ResponseEntity<>(response,HttpStatus.resolve(response.getStatus()));
        adviceLogger.warn(exc.getMessage());
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleException(DataIntegrityViolationException exc){
        //ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), exc.getMessage(),getTimeStamp());
        //return  new ResponseEntity<>(response,HttpStatus.resolve(response.getStatus()));
        adviceLogger.warn(exc.getMessage());
        return  ResponseEntity.badRequest().body("Check input data");
    }


    /*public ResponseEntity<Object> handleException(IOException exc){
        System.out.println(exc.getCause());
        return ResponseEntity.internalServerError().body("");
    }

     */


    @ExceptionHandler
    public ResponseEntity<Object> handleException(HibernateException exc){

        return ResponseEntity.badRequest().body("Entity not correctly built");
    }

    private String getTimeStamp(){
        return FORMATTER.format(new Date(System.currentTimeMillis()));
    }

}
