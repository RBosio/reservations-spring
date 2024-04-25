package com.fiido.reservations.api.controllers;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiido.reservations.api.models.responses.HotelResponse;
import com.fiido.reservations.infrastructure.interfaces.HotelService;
import com.fiido.reservations.util.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/hotel")
@AllArgsConstructor
public class HotelController {
  private final HotelService hotelService;

  @GetMapping
  public ResponseEntity<Page<HotelResponse>> findAll(@RequestParam Integer page, @RequestParam Integer size,
      @RequestHeader(required = false) SortType sort) {
    if (Objects.isNull(sort))
      sort = SortType.NONE;
    var response = this.hotelService.list(page, size, sort);
    return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
  }

  @GetMapping("/less-price")
  public ResponseEntity<Set<HotelResponse>> findByPriceLessThan(@RequestParam BigDecimal price) {
    return ResponseEntity.ok(this.hotelService.readLessPrice(price));
  }

  @GetMapping("/between-price")
  public ResponseEntity<Set<HotelResponse>> findByPriceBetween(@RequestParam BigDecimal min,
      @RequestParam BigDecimal max) {
    return ResponseEntity.ok(this.hotelService.readBetweenPrice(min, max));
  }

  @GetMapping("/greater-rating")
  public ResponseEntity<Set<HotelResponse>> findByRatingGreaterThan(@RequestParam Integer rating) {
    return ResponseEntity.ok(this.hotelService.findRatingGreaterThan(rating));
  }

}
