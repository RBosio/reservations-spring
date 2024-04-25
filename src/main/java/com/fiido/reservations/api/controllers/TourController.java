package com.fiido.reservations.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiido.reservations.api.models.requests.TourRequest;
import com.fiido.reservations.api.models.responses.TourResponse;
import com.fiido.reservations.infrastructure.interfaces.TourService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/tour")
@AllArgsConstructor
public class TourController {

  private final TourService tourService;

  @PostMapping
  public ResponseEntity<TourResponse> post(@RequestBody TourRequest request) {
    return ResponseEntity.ok(this.tourService.create(request));
  }

  @GetMapping("{id}")
  public ResponseEntity<TourResponse> post(@PathVariable Long id) {
    return ResponseEntity.ok(this.tourService.read(id));
  }

  @PatchMapping("{tourId}/remove-ticket/{ticketId}")
  public ResponseEntity<TourResponse> removeTicket(@PathVariable Long tourId, @PathVariable Long ticketId) {
    this.tourService.removeTicket(tourId, ticketId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("{tourId}/add-ticket/{flyId}")
  public ResponseEntity<TourResponse> addTicket(@PathVariable Long tourId, @PathVariable Long flyId) {
    this.tourService.addTicket(tourId, flyId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("{tourId}/remove-reservation/{hotelId}")
  public ResponseEntity<TourResponse> removeReservation(@PathVariable Long tourId, @PathVariable Long hotelId) {
    this.tourService.removeReservation(tourId, hotelId);
    return ResponseEntity.noContent().build();
  }

  @PatchMapping("{tourId}/add-reservation/{hotelId}")
  public ResponseEntity<TourResponse> addReservation(@PathVariable Long tourId, @PathVariable Long hotelId,
      @RequestParam Integer totalDays) {
    this.tourService.addReservation(tourId, hotelId, totalDays);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    this.tourService.delete(id);
    return ResponseEntity.noContent().build();
  }
}
