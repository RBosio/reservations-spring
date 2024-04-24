package com.fiido.reservations.api.models.responses;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationResponse {
  private Long id;
  private LocalDateTime dateTimeReservation;
  private LocalDate dateStart;
  private LocalDate dateEnd;
  private Integer totalDays;
  private BigDecimal price;
  private HotelResponse hotel;
  private CustomerResponse customer;
}
