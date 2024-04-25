package com.fiido.reservations.api.models.responses;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourResponse {
  private Long id;
  private Set<Long> ticketIds;
  private Set<Long> reservationIds;
}
