package com.fiido.reservations.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest {
  private String clientId;
  private Long hotelId;
  private Integer totalDays;
}
