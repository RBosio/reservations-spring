package com.fiido.reservations.infrastructure.helpers;

import org.springframework.stereotype.Component;

import com.fiido.reservations.domain.repositories.CustomerRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Component
@AllArgsConstructor
public class CustomerHelper {

  private final CustomerRepository customerRepository;

  public void increase(String customerId, Class<?> type) {
    var customer = this.customerRepository.findById(customerId).orElseThrow();
    switch (type.getSimpleName()) {
      case "TourService" -> customer.setTotalTours(customer.getTotalTours() + 1);
      case "TicketService" -> customer.setTotalFlights(customer.getTotalFlights() + 1);
      case "ReservationService" -> customer.setTotalLodgings(customer.getTotalLodgings() + 1);
    }
    this.customerRepository.save(customer);
  }
}
