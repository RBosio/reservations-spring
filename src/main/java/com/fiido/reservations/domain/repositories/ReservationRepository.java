package com.fiido.reservations.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fiido.reservations.domain.entities.ReservationEntity;

public interface ReservationRepository extends CrudRepository<ReservationEntity, Long> {

}
