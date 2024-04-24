package com.fiido.reservations.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerResponse {
  private String dni;
  private String fullName;
  private String creditCard;
  private String phoneNumber;
  private Integer totalFlights;
  private Integer totalLodgings;
  private Integer totalTours;
}
