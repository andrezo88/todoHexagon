package com.abreu.todoHexagonal.api.controller;

import com.abreu.todoHexagonal.api.dto.ErrorResponse;
import com.abreu.todoHexagonal.business.exception.BadRequestException;
import com.abreu.todoHexagonal.business.exception.IdNotFoundException;
import com.abreu.todoHexagonal.business.exception.IllegalArgumentException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(getErrors(ex))
                .path(ex.getBindingResult().getObjectName())
                .build();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIdNotFoundException(IdNotFoundException e, HttpServletRequest request) {
        ErrorResponse error = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse err = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    List<String> getErrors(MethodArgumentNotValidException e) {
        return e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
    }
}
