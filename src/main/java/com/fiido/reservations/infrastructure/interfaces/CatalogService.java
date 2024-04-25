package com.fiido.reservations.infrastructure.interfaces;

import java.math.BigDecimal;
import java.util.Set;

import org.springframework.data.domain.Page;

import com.fiido.reservations.util.SortType;

public interface CatalogService<R> {

  Page<R> list(Integer page, Integer size, SortType sortType);

  Set<R> readLessPrice(BigDecimal price);

  Set<R> readBetweenPrice(BigDecimal min, BigDecimal max);

  String FIELD_BY_SORT = "price";
}
