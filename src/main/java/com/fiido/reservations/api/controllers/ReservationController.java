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

import com.fiido.reservations.api.models.requests.ReservationRequest;
import com.fiido.reservations.api.models.responses.ReservationResponse;
import com.fiido.reservations.infrastructure.interfaces.ReservationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController {
  private final ReservationService reservationService;

  @PostMapping
  public ResponseEntity<ReservationResponse> create(@RequestBody ReservationRequest request) {
    return ResponseEntity.ok(this.reservationService.create(request));
  }

  @GetMapping("{id}")
  public ResponseEntity<ReservationResponse> read(@PathVariable Long id) {
    return ResponseEntity.ok(this.reservationService.read(id));
  }

  @PutMapping("{id}")
  public ResponseEntity<ReservationResponse> update(@PathVariable Long id, @RequestBody ReservationRequest request) {
    return ResponseEntity.ok(this.reservationService.update(id, request));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.reservationService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
