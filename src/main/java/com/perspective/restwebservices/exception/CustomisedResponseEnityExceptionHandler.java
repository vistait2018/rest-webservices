package com.perspective.restwebservices.exception;

import net.bytebuddy.description.modifier.MethodArguments;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@RestController
public class CustomisedResponseEnityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<Object> handleUsrNotFoundException(UserNotFoundException ex, WebRequest request){
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(), ex.getMessage(),request.getDescription(false));
        return new ResponseEntity<>(er, HttpStatus.NOT_FOUND);
    }

    @Override
    protected  ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
                                 HttpHeaders headers,HttpStatus status,
                                 WebRequest request){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ExceptionResponse er = new ExceptionResponse(LocalDateTime.now(),"Validation Error" ,errors.toString());
       return new ResponseEntity<>( er,HttpStatus.BAD_REQUEST);
    }


}
