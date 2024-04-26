package com.fiido.reservations.infrastructure.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiido.reservations.api.models.requests.ReservationRequest;
import com.fiido.reservations.api.models.responses.CustomerResponse;
import com.fiido.reservations.api.models.responses.HotelResponse;
import com.fiido.reservations.api.models.responses.ReservationResponse;
import com.fiido.reservations.domain.entities.ReservationEntity;
import com.fiido.reservations.domain.repositories.CustomerRepository;
import com.fiido.reservations.domain.repositories.HotelRepository;
import com.fiido.reservations.domain.repositories.ReservationRepository;
import com.fiido.reservations.infrastructure.helpers.CustomerHelper;
import com.fiido.reservations.infrastructure.interfaces.ReservationService;
import com.fiido.reservations.util.Tables;
import com.fiido.reservations.util.exceptions.NotFoundException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {
  private final HotelRepository hotelRepository;
  private final CustomerRepository customerRepository;
  private final ReservationRepository reservationRepository;
  private final CustomerHelper customerHelper;

  @Override
  public ReservationResponse create(ReservationRequest request) {
    var hotel = this.hotelRepository.findById(request.getHotelId())
        .orElseThrow(() -> new NotFoundException(Tables.hotel.name()));
    var customer = this.customerRepository.findById(request.getClientId()).orElseThrow(() -> new NotFoundException(Tables.customer.name()));
    var reservation = ReservationEntity.builder()
        .hotel(hotel)
        .customer(customer)
        .totalDays(request.getTotalDays())
        .dateTimeReservation(LocalDateTime.now())
        .dateStart(LocalDate.now())
        .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
        .price(hotel.getPrice().multiply(BigDecimal.valueOf(1.20)))
        .build();

    this.customerHelper.increase(customer.getDni(), ReservationServiceImpl.class);

    return this.entityToResponse(this.reservationRepository.save(reservation));
  }

  @Override
  public ReservationResponse read(Long id) {
    return this.entityToResponse(this.reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(Tables.reservation.name())));
  }

  @Override
  public ReservationResponse update(Long id, ReservationRequest request) {
    var reservation = this.reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(Tables.reservation.name()));
    var hotel = this.hotelRepository.findById(request.getHotelId()).orElseThrow(() -> new NotFoundException(Tables.hotel.name()));

    reservation.setHotel(hotel);
    reservation.setTotalDays(request.getTotalDays());

    log.info("Updating reservation with id: {}", id);
    return this.entityToResponse(this.reservationRepository.save(reservation));
  }

  @Override
  public void delete(Long id) {
    var reservation = this.reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(Tables.reservation.name()));
    this.reservationRepository.delete(reservation);
  }

  private ReservationResponse entityToResponse(ReservationEntity entity) {
    var reservation = new ReservationResponse();
    BeanUtils.copyProperties(entity, reservation);

    var hotel = new HotelResponse();
    BeanUtils.copyProperties(entity.getHotel(), hotel);
    reservation.setHotel(hotel);

    var customer = new CustomerResponse();
    BeanUtils.copyProperties(entity.getCustomer(), customer);
    reservation.setCustomer(customer);

    return reservation;
  }

}
