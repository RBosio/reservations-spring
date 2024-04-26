package com.fiido.reservations.api.models.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketRequest {

  @NotNull(message = "clientId is required")
  private String clientId;
  @NotNull(message = "flyId is required")
  @Positive(message = "flyId should be a positive number")
  private Long flyId;
}
