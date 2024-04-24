package com.fiido.reservations.infrastructure.interfaces;

import com.fiido.reservations.api.models.requests.TicketRequest;
import com.fiido.reservations.api.models.responses.TicketResponse;

public interface TicketService extends CrudService<TicketRequest, TicketResponse, Long> {

}
