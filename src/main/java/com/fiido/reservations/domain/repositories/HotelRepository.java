package com.fiido.reservations.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiido.reservations.domain.entities.HotelEntity;

public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

}
