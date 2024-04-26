package com.fiido.reservations.api.controllers.error_handlers;

import java.util.ArrayList;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fiido.reservations.api.models.responses.ErrorResponse;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse badRequest(MethodArgumentNotValidException ex) {
    var errors = new ArrayList<String>();
    ex.getAllErrors().forEach(error -> {
      errors.add(error.getDefaultMessage());
    });
    return ErrorResponse
        .builder()
        .errors(errors)
        .code(HttpStatus.BAD_REQUEST
            .value())
        .status(HttpStatus.BAD_REQUEST.name()).build();
  }

}
