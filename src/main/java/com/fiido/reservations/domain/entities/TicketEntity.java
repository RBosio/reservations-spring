package com.fiido.reservations.domain.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "ticket")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TicketEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private LocalDate departureDate;
  private LocalDate arrivalDate;
  private LocalDate purchaseDate;
  private BigDecimal price;
  
  @ManyToOne()
  @JoinColumn(name = "fly_id")
  private FlyEntity fly;
  
  @ManyToOne()
  @JoinColumn(name = "tour_id", nullable = true)
  private TourEntity tour;

  @ManyToOne()
  @JoinColumn(name = "customer_dni")
  private CustomerEntity customer;
}
