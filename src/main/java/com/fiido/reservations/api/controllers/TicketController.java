package com.fiido.reservations.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fiido.reservations.api.models.requests.TicketRequest;
import com.fiido.reservations.api.models.responses.TicketResponse;
import com.fiido.reservations.infrastructure.interfaces.TicketService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
  private final TicketService ticketService;

  @PostMapping
  public ResponseEntity<TicketResponse> create(@RequestBody TicketRequest request) {
    return ResponseEntity.ok(this.ticketService.create(request));
  }

  @GetMapping("{id}")
  public ResponseEntity<TicketResponse> get(@PathVariable Long id) {
    return ResponseEntity.ok(this.ticketService.read(id));
  }

  @PutMapping("{id}")
  public ResponseEntity<TicketResponse> update(@PathVariable Long id, @RequestBody TicketRequest request) {
    return ResponseEntity.ok(this.ticketService.update(id, request));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.ticketService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
