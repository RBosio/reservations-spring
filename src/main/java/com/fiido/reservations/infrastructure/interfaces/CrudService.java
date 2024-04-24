package com.fiido.reservations.infrastructure.interfaces;

public interface CrudService<RQ, RS, ID> {
  RS create(RQ request);

  RS read(ID id);

  RS update(ID id, RQ request);

  void delete(ID id);
}
