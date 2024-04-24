package com.fiido.reservations.infrastructure.interfaces;

import com.fiido.reservations.api.models.requests.ReservationRequest;
import com.fiido.reservations.api.models.responses.ReservationResponse;

public interface ReservationService extends CrudService<ReservationRequest, ReservationResponse, Long> {

}
