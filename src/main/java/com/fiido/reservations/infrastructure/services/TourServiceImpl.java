package com.fiido.reservations.infrastructure.services;

import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiido.reservations.api.models.requests.TourRequest;
import com.fiido.reservations.api.models.responses.TourResponse;
import com.fiido.reservations.domain.entities.FlyEntity;
import com.fiido.reservations.domain.entities.HotelEntity;
import com.fiido.reservations.domain.entities.ReservationEntity;
import com.fiido.reservations.domain.entities.TicketEntity;
import com.fiido.reservations.domain.entities.TourEntity;
import com.fiido.reservations.domain.repositories.CustomerRepository;
import com.fiido.reservations.domain.repositories.FlyRepository;
import com.fiido.reservations.domain.repositories.HotelRepository;
import com.fiido.reservations.domain.repositories.TourRepository;
import com.fiido.reservations.infrastructure.helpers.CustomerHelper;
import com.fiido.reservations.infrastructure.helpers.TourHelper;
import com.fiido.reservations.infrastructure.interfaces.TourService;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class TourServiceImpl implements TourService {
  private final TourRepository tourRepository;
  private final FlyRepository flyRepository;
  private final HotelRepository hotelRepository;
  private final CustomerRepository customerRepository;
  private final TourHelper tourHelper;
  private final CustomerHelper customerHelper;

  @Override
  public TourResponse create(TourRequest request) {
    var customer = this.customerRepository.findById(request.getCustomerId()).orElseThrow();
    var flights = new HashSet<FlyEntity>();
    request.getFlights().forEach(fly -> flights.add(this.flyRepository.findById(fly.getId()).orElseThrow()));

    var hotels = new HashMap<HotelEntity, Integer>();
    request.getHotels()
        .forEach(hotel -> hotels.put(this.hotelRepository.findById(hotel.getId()).orElseThrow(), hotel.getTotalDays()));

    var tour = TourEntity.builder()
        .tickets(this.tourHelper.createTickets(flights, customer))
        .reservations(this.tourHelper.createReservations(hotels, customer))
        .customer(customer)
        .build();
    var tourSaved = this.tourRepository.save(tour);

    this.customerHelper.increase(customer.getDni(), TourServiceImpl.class);
    return TourResponse.builder()
        .reservationIds(
            tourSaved.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
        .ticketIds(
            tourSaved.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
        .id(tourSaved.getId())
        .build();
  }

  @Override
  public TourResponse read(Long id) {
    var tour = this.tourRepository.findById(id).orElseThrow();
    return TourResponse.builder()
        .reservationIds(tour.getReservations().stream().map(ReservationEntity::getId).collect(Collectors.toSet()))
        .ticketIds(tour.getTickets().stream().map(TicketEntity::getId).collect(Collectors.toSet()))
        .id(tour.getId())
        .build();
  }

  @Override
  public void delete(Long id) {
    var tour = this.tourRepository.findById(id).orElseThrow();
    this.tourRepository.delete(tour);
  }

  @Override
  public void removeTicket(Long tourId, Long ticketId) {
    var tour = this.tourRepository.findById(tourId).orElseThrow();
    tour.removeTicket(ticketId);
    this.tourRepository.save(tour);
  }

  @Override
  public void addTicket(Long tourId, Long flyId) {
    var tour = this.tourRepository.findById(tourId).orElseThrow();
    var fly = this.flyRepository.findById(flyId).orElseThrow();

    var ticket = this.tourHelper.createTicket(fly, tour.getCustomer());
    tour.addTicket(ticket);
    this.tourRepository.save(tour);
  }

  @Override
  public void removeReservation(Long tourId, Long reservationId) {
    var tour = this.tourRepository.findById(tourId).orElseThrow();
    tour.removeTicket(reservationId);
    this.tourRepository.save(tour);
  }

  @Override
  public void addReservation(Long tourId, Long hotelId, Integer totalDays) {
    var tour = this.tourRepository.findById(tourId).orElseThrow();
    var hotel = this.hotelRepository.findById(hotelId).orElseThrow();
    this.tourHelper.createReservation(hotel, tour.getCustomer(), totalDays);
  }
}
