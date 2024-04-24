package com.fiido.reservations.api.models.responses;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class HotelResponse {
  private Long id;
  private String name;
  private String address;
  private Integer rating;
  private BigDecimal price;
}
