package com.fiido.reservations.infrastructure.services;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fiido.reservations.api.models.responses.HotelResponse;
import com.fiido.reservations.domain.entities.HotelEntity;
import com.fiido.reservations.domain.repositories.HotelRepository;
import com.fiido.reservations.infrastructure.interfaces.HotelService;
import com.fiido.reservations.util.SortType;

import lombok.AllArgsConstructor;

@Transactional(readOnly = true)
@Service
@AllArgsConstructor
public class HotelServiceImpl implements HotelService {
  private final HotelRepository hotelRepository;

  @Override
  public Page<HotelResponse> list(Integer page, Integer size, SortType sortType) {
    PageRequest pageRequest = null;
    switch (sortType) {
      case NONE -> pageRequest = PageRequest.of(page, size);
      case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
      case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
    }

    return this.hotelRepository.findAll(pageRequest).map(this::entityToResponse);
  }

  @Override
  @Cacheable(value = "hotels")
  public Set<HotelResponse> readLessPrice(BigDecimal price) {
    return this.hotelRepository.findByPriceLessThan(price).stream().map(this::entityToResponse)
        .collect(Collectors.toSet());
  }

  @Override
  @Cacheable(value = "hotels")
  public Set<HotelResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
    return this.hotelRepository.findByPriceIsBetween(min, max).stream().map(this::entityToResponse)
        .collect(Collectors.toSet());
  }

  @Override
  @Cacheable(value = "hotels")
  public Set<HotelResponse> findRatingGreaterThan(Integer rating) {
    return this.hotelRepository.findByRatingGreaterThan(rating).stream().map(this::entityToResponse)
        .collect(Collectors.toSet());
  }

  private HotelResponse entityToResponse(HotelEntity entity) {
    var response = new HotelResponse();
    BeanUtils.copyProperties(entity, response);
    return response;
  }
}
