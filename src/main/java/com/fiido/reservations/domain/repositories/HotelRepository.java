package com.fiido.reservations.domain.repositories;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiido.reservations.domain.entities.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {
  Set<HotelEntity> findByPriceLessThan(BigDecimal price);

  Set<HotelEntity> findByPriceIsBetween(BigDecimal min, BigDecimal max);

  Set<HotelEntity> findByRatingGreaterThan(Integer rating);
}
