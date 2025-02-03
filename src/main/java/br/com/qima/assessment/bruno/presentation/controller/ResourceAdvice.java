package br.com.qima.assessment.bruno.presentation.controller;

import br.com.qima.assessment.bruno.domain.exception.ExpiredJwtException;
import br.com.qima.assessment.bruno.domain.exception.InvalidUserException;
import br.com.qima.assessment.bruno.domain.exception.UserNotFoundException;
import br.com.qima.assessment.bruno.presentation.dto.ErrorInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceAdvice {

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<ErrorInfoDto> handleUserNotFoundException(UserNotFoundException e) {
    return new ResponseEntity<>(createErrorInfoDto(e, 404), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({InvalidUserException.class, ExpiredJwtException.class})
  public ResponseEntity<ErrorInfoDto> handleInvalidUserException(InvalidUserException e) {
    return new ResponseEntity<>(createErrorInfoDto(e, 401), HttpStatus.UNAUTHORIZED);
  }

  private ErrorInfoDto createErrorInfoDto(Exception e, int status) {
    return ErrorInfoDto.builder()
        .message(e.getMessage() != null ? e.getMessage()
            : "No message from: " + e.getClass().getSimpleName())
        .status(status)
        .build();
  }
}
