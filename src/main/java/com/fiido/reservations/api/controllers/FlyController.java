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
import org.springframework.web.servlet.function.EntityResponse;

import com.fiido.reservations.api.models.responses.FlyResponse;
import com.fiido.reservations.infrastructure.interfaces.FlyService;
import com.fiido.reservations.util.SortType;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/fly")
@AllArgsConstructor
public class FlyController {
  private final FlyService flyService;

  @GetMapping
  public ResponseEntity<Page<FlyResponse>> findAll(@RequestParam Integer page, @RequestParam Integer size,
      @RequestHeader(required = false) SortType sort) {
    if (Objects.isNull(sort))
      sort = SortType.NONE;
    var response = this.flyService.list(page, size, sort);
    return response.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
  }

  @GetMapping("/less-price")
  public ResponseEntity<Set<FlyResponse>> findByPriceLessThan(@RequestParam BigDecimal price) {
    return ResponseEntity.ok(this.flyService.readLessPrice(price));
  }

  @GetMapping("/between-price")
  public ResponseEntity<Set<FlyResponse>> findByPriceBetween(@RequestParam BigDecimal min,
      @RequestParam BigDecimal max) {
    return ResponseEntity.ok(this.flyService.readBetweenPrice(min, max));
  }

  @GetMapping("/origin-destiny")
  public ResponseEntity<Set<FlyResponse>> findByOriginNameAndDestinyName(@RequestParam String origin,
      @RequestParam String destiny) {
    return ResponseEntity.ok(this.flyService.readByOriginDestiny(origin, destiny));
  }
}
