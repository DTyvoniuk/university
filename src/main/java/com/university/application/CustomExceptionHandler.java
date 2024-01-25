package com.university.application;

import com.university.department.exception.DepartmentException;
import com.university.lector.exception.LectorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  @ExceptionHandler(DepartmentException.class)
  public ResponseEntity<String> handleDepartmentException(DepartmentException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(LectorException.class)
  public ResponseEntity<String> handleLectorException(LectorException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
