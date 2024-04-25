package com.fiido.reservations.infrastructure.interfaces;

import com.fiido.reservations.api.models.requests.TourRequest;
import com.fiido.reservations.api.models.responses.TourResponse;

public interface TourService extends SimpleCrudService<TourRequest, TourResponse, Long> {
  void removeTicket(Long tourId, Long ticketId);

  void addTicket(Long tourId, Long flyId);

  void removeReservation(Long tourId, Long reservationId);

  void addReservation(Long tourId, Long reservationId, Integer totalDays);
}
