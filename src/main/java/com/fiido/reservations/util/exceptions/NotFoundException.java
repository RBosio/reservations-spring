package com.fiido.reservations.util.exceptions;

public class NotFoundException extends RuntimeException {

  private static final String MESSAGE = "%s not found";

  public NotFoundException(String table) {
    super(String.format(MESSAGE, table));
  }
}
