package com.fiido.reservations.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fiido.reservations.domain.entities.TicketEntity;

public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

}
