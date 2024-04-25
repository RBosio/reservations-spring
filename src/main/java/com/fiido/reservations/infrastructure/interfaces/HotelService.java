package com.fiido.reservations.infrastructure.interfaces;

import java.util.Set;

import com.fiido.reservations.api.models.responses.HotelResponse;

public interface HotelService extends CatalogService<HotelResponse> {

  Set<HotelResponse> findRatingGreaterThan(Integer rating);
}
