package com.fiido.reservations.infrastructure.interfaces;

public interface SimpleCrudService<RQ, RS, ID> {
  RS create(RQ request);

  RS read(ID id);

  void delete(ID id);
}
