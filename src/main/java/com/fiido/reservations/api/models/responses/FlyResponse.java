package com.fiido.reservations.api.models.responses;

import java.math.BigDecimal;

import com.fiido.reservations.infrastructure.AeroLine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyResponse {
  private Long id;
  private Double originLat;
  private Double originLng;
  private Double destinyLat;
  private Double destinyLng;
  private String originName;
  private String destinyName;
  private BigDecimal price;
  private AeroLine aeroLine;
}
