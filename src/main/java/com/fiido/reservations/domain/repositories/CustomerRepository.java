package com.fiido.reservations.domain.repositories;

import org.springframework.data.repository.CrudRepository;

import com.fiido.reservations.domain.entities.CustomerEntity;

public interface CustomerRepository extends CrudRepository<CustomerEntity, String> {

}
