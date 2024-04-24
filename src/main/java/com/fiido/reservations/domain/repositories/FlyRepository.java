package com.fiido.reservations.domain.repositories;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiido.reservations.domain.entities.FlyEntity;

public interface FlyRepository extends JpaRepository<FlyEntity, Long> {

  Set<FlyEntity> findByPriceLessThan(BigDecimal price);

  Set<FlyEntity> findByPriceIsBetween(BigDecimal min, BigDecimal max);

  Set<FlyEntity> findByOriginNameAndDestinyName(String origin, String destiny);

}
