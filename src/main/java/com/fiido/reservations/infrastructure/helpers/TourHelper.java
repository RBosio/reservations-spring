package com.fiido.reservations.infrastructure.helpers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.fiido.reservations.domain.entities.CustomerEntity;
import com.fiido.reservations.domain.entities.FlyEntity;
import com.fiido.reservations.domain.entities.HotelEntity;
import com.fiido.reservations.domain.entities.ReservationEntity;
import com.fiido.reservations.domain.entities.TicketEntity;
import com.fiido.reservations.domain.repositories.ReservationRepository;
import com.fiido.reservations.domain.repositories.TicketRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Component
@AllArgsConstructor
public class TourHelper {
  private final TicketRepository ticketRepository;
  private final ReservationRepository reservationRepository;

  public Set<TicketEntity> createTickets(Set<FlyEntity> flights, CustomerEntity customer) {
    var response = new HashSet<TicketEntity>(flights.size());
    flights.forEach(fly -> {
      var ticket = TicketEntity.builder()
          .fly(fly)
          .customer(customer)
          .price(fly.getPrice().multiply(BigDecimal.valueOf(1.2)))
          .purchaseDate(LocalDate.now())
          .arrivalDate(LocalDateTime.now())
          .departureDate(LocalDateTime.now())
          .build();
      response.add(this.ticketRepository.save(ticket));
    });
    return response;
  }

  public Set<ReservationEntity> createReservations(HashMap<HotelEntity, Integer> hotels, CustomerEntity customer) {
    var response = new HashSet<ReservationEntity>(hotels.size());
    hotels.forEach((hotel, totalDays) -> {
      var reservation = ReservationEntity.builder()
          .hotel(hotel)
          .customer(customer)
          .totalDays(totalDays)
          .dateTimeReservation(LocalDateTime.now())
          .dateStart(LocalDate.now())
          .dateEnd(LocalDate.now().plusDays(totalDays))
          .price(hotel.getPrice().multiply(BigDecimal.valueOf(1.20)))
          .build();
      response.add(this.reservationRepository.save(reservation));
    });
    return response;
  }

  public TicketEntity createTicket(FlyEntity fly, CustomerEntity customer) {
    var ticket = TicketEntity.builder()
        .fly(fly)
        .customer(customer)
        .price(fly.getPrice().multiply(BigDecimal.valueOf(1.2)))
        .purchaseDate(LocalDate.now())
        .arrivalDate(LocalDateTime.now())
        .departureDate(LocalDateTime.now())
        .build();
    return this.ticketRepository.save(ticket);
  }

  public ReservationEntity createReservation(HotelEntity hotel, CustomerEntity customer, Integer totalDays) {
    var reservation = ReservationEntity.builder()
        .hotel(hotel)
        .customer(customer)
        .totalDays(totalDays)
        .dateTimeReservation(LocalDateTime.now())
        .dateStart(LocalDate.now())
        .dateEnd(LocalDate.now().plusDays(totalDays))
        .price(hotel.getPrice().multiply(BigDecimal.valueOf(1.20)))
        .build();
    return this.reservationRepository.save(reservation);
  }
}
