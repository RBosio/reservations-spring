package com.fiido.reservations.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiido.reservations.domain.entities.FlyEntity;

public interface FlyRepository extends JpaRepository<FlyEntity, Long> {

}
