package com.fiido.reservations.api.controllers.error_handlers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fiido.reservations.api.models.responses.ErrorResponse;
import com.fiido.reservations.util.exceptions.NotFoundException;

@RestControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundController {

  @ExceptionHandler(NotFoundException.class)
  public ErrorResponse notFound(NotFoundException ex) {
    var errors = new ArrayList<String>();
    errors.add(ex.getMessage());

    return ErrorResponse
        .builder()
        .errors(errors)
        .code(HttpStatus.NOT_FOUND.value())
        .status(HttpStatus.NOT_FOUND.name())
        .build();
  }
}
