package com.fiido.reservations.infrastructure.services;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fiido.reservations.api.models.responses.FlyResponse;
import com.fiido.reservations.domain.entities.FlyEntity;
import com.fiido.reservations.domain.repositories.FlyRepository;
import com.fiido.reservations.infrastructure.interfaces.FlyService;
import com.fiido.reservations.util.SortType;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class FlyServiceImpl implements FlyService {
  private final FlyRepository flyRepository;

  @Override
  public Page<FlyResponse> list(Integer page, Integer size, SortType sortType) {
    PageRequest pageRequest = null;
    switch (sortType) {
      case NONE -> pageRequest = PageRequest.of(page, size);
      case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
      case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
    }

    return this.flyRepository.findAll(pageRequest).map(this::entityToResponse);
  }

  @Override
  public Set<FlyResponse> readLessPrice(BigDecimal price) {
    throw new UnsupportedOperationException("Unimplemented method 'readLessPrice'");
  }

  @Override
  public Set<FlyResponse> readBetweenPrice(BigDecimal min, BigDecimal max) {
    throw new UnsupportedOperationException("Unimplemented method 'readBetweenPrice'");
  }

  @Override
  public Set<FlyResponse> readByOriginDestiny(String origin, String destiny) {
    throw new UnsupportedOperationException("Unimplemented method 'readByOriginDestiny'");
  }

  private FlyResponse entityToResponse(FlyEntity entity) {
    var response = new FlyResponse();
    BeanUtils.copyProperties(entity, response);
    return response;
  }
}
