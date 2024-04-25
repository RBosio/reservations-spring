package com.fiido.reservations.infrastructure.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiido.reservations.api.models.requests.TicketRequest;
import com.fiido.reservations.api.models.responses.FlyResponse;
import com.fiido.reservations.api.models.responses.TicketResponse;
import com.fiido.reservations.domain.entities.TicketEntity;
import com.fiido.reservations.domain.repositories.CustomerRepository;
import com.fiido.reservations.domain.repositories.FlyRepository;
import com.fiido.reservations.domain.repositories.TicketRepository;
import com.fiido.reservations.infrastructure.helpers.CustomerHelper;
import com.fiido.reservations.infrastructure.interfaces.TicketService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
  private final FlyRepository flyRepository;
  private final CustomerRepository customerRepository;
  private final TicketRepository ticketRepository;
  private final CustomerHelper customerHelper;

  @Override
  public TicketResponse create(TicketRequest request) {
    var fly = this.flyRepository.findById(request.getFlyId()).orElseThrow();
    var customer = this.customerRepository.findById(request.getClientId()).orElseThrow();

    var ticket = TicketEntity.builder()
        .fly(fly)
        .customer(customer)
        .price(fly.getPrice().multiply(BigDecimal.valueOf(1.2)))
        .purchaseDate(LocalDate.now())
        .arrivalDate(LocalDateTime.now())
        .departureDate(LocalDateTime.now())
        .build();

    this.customerHelper.increase(customer.getDni(), TicketServiceImpl.class);
    log.info("Ticket created: {}", ticket);

    return this.entityToResponse(this.ticketRepository.save(ticket));
  }

  @Override
  public TicketResponse read(Long id) {
    return this.entityToResponse(this.ticketRepository.findById(id).orElseThrow());
  }

  @Override
  public TicketResponse update(Long id, TicketRequest request) {
    var ticket = this.ticketRepository.findById(id).orElseThrow();
    var fly = this.flyRepository.findById(request.getFlyId()).orElseThrow();
    ticket.setFly(fly);
    ticket.setPrice(ticket.getPrice().multiply(BigDecimal.valueOf(1.20)));

    return this.entityToResponse(this.ticketRepository.save(ticket));
  }

  @Override
  public void delete(Long id) {
    var ticket = this.ticketRepository.findById(id).orElseThrow();
    this.ticketRepository.delete(ticket);
  }

  private TicketResponse entityToResponse(TicketEntity entity) {
    var ticket = new TicketResponse();
    BeanUtils.copyProperties(entity, ticket);
    var fly = new FlyResponse();
    BeanUtils.copyProperties(entity.getFly(), fly);
    ticket.setFly(fly);

    return ticket;
  }

}
