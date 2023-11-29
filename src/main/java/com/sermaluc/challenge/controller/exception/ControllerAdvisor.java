package com.sermaluc.challenge.controller.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleConflictSaveUserByEmailException(DataIntegrityViolationException exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", "El correo ya esta registrado");
        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleExceptionGeneric(Exception exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRunTimeException(RuntimeException exception, WebRequest request) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("mensaje", exception.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
