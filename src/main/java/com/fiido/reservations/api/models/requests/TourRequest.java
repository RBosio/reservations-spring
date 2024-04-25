package com.fiido.reservations.api.models.requests;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TourRequest {
  private String customerId;
  private Set<TourFlyRequest> flights;
  private Set<TourHotelRequest> hotels;
}
