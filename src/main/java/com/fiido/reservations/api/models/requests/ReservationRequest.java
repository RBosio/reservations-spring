package com.fiido.reservations.api.models.requests;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ReservationRequest {
  @NotNull(message = "clientId is required")
  private String clientId;
  @NotNull(message = "hotelId is required")
  @Positive(message = "hotelId should be a positive number")
  private Long hotelId;
  @NotNull(message = "totalDays is required")
  @Min(value = 1, message = "min 1 day to make reservation")
  @Max(value = 30, message = "max 30 days to make reservation")
  private Integer totalDays;
  @NotNull(message = "email is required")
  @Email
  private String email;
}
