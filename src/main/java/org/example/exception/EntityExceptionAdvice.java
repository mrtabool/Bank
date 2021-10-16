package org.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class EntityExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ExceptionResponse> entityAlreadyExistHandler(EntityNotFoundException ex) {
        return new ResponseEntity<ExceptionResponse>(
                ExceptionResponse.builder().code("400").text(ex.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ExceptionResponse> entityAlreadyExistHandler(ConstraintViolationException ex) {
        return new ResponseEntity<ExceptionResponse>(
                ExceptionResponse.builder().code("400").text(ex.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<ExceptionResponse> entityAlreadyExistHandler(MethodArgumentNotValidException ex) {
        return new ResponseEntity<ExceptionResponse>(
                ExceptionResponse.builder().code("400").text(ex.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

}
