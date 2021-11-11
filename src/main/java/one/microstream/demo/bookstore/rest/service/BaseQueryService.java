package one.microstream.demo.bookstore.rest.service;

import javax.money.MonetaryAmount;
import one.microstream.demo.bookstore.BookStoreDemo;
import org.modelmapper.ModelMapper;

public abstract class BaseQueryService implements QueryService {

  private final ModelMapper mapper;

  public BaseQueryService() {
    mapper = configureModelMapper(new ModelMapper());
  }

  ModelMapper configureModelMapper(ModelMapper mapper) {
    return mapper;
  }

  final <T, S> S map(T value, Class<S> clazz) {
    return mapper.map(value, clazz);
  }

  final int defaultPageSize() {
    return 100;
  }

  final MonetaryAmount monetaryAmount(double value) {
    return BookStoreDemo.money(value);
  }

  final double deriveDouble(MonetaryAmount money) {
    return money.getNumber().doubleValueExact();
  }
}
