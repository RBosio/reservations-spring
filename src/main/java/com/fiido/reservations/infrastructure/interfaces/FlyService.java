package com.fiido.reservations.infrastructure.interfaces;

import java.util.Set;

import com.fiido.reservations.api.models.responses.FlyResponse;

public interface FlyService extends CatalogService<FlyResponse> {
  Set<FlyResponse> readByOriginDestiny(String origin, String destiny);
}
