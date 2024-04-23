package com.fiido.reservations.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fiido.reservations.domain.entities.TourEntity;

public interface TourRepository extends CrudRepository<TourEntity, Long> {

}
