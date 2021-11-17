package one.microstream.demo.bookstore.rest.service;

import static java.util.Objects.requireNonNull;

import javax.money.MonetaryAmount;
import one.microstream.demo.bookstore.BookStoreDemo;
import org.modelmapper.ModelMapper;

/**
 * Base implementation for {@link QueryService}s which takes care of certain common aspects and
 * provides some required defaults.
 *
 * @author Benedikt Full
 */
public abstract class BaseQueryService implements QueryService {

  private final ModelMapper mapper;

  public BaseQueryService() {
    mapper = initMapper();
  }

  private ModelMapper initMapper() {
    ModelMapper mapper = new ModelMapper();
    ModelMapper returnedMapper = configureModelMapper(mapper);
    if (!mapper.equals(returnedMapper)) {
      throw new IllegalStateException("Configured mapper is not provided mapper");
    }
    return returnedMapper;
  }

  /**
   * Returns the default model mapper instance utilized by this class whenever {@link #map(Object,
   * Class)} is called.
   *
   * <p>This method can be overridden. The primary purpose is to allow for the customization of the
   * mapper, e.g. by adding converters.
   *
   * @param mapper the mapper instance used by this service
   * @return the provided instance, as otherwise the instantiation of this service will fail with an
   *     {@link IllegalStateException}
   */
  ModelMapper configureModelMapper(ModelMapper mapper) {
    return mapper;
  }

  /**
   * Maps the given value to the provided class using the {@link ModelMapper} instance maintained by
   * this service and configured using {@link #configureModelMapper(ModelMapper)}.
   *
   * @param value the value to be mapped, must not be {@code null}
   * @param clazz the type to which the provided value will be mapped, must not be {@code null}
   * @param <T> the value type
   * @param <S> the return type
   * @return an instance of type {@code S} based on the given instance {@code T}
   */
  final <T, S> S map(T value, Class<S> clazz) {
    return mapper.map(value, clazz);
  }

  /** @return the default for customer pagination page size */
  final int defaultPageSize() {
    return 100;
  }

  /**
   * Converts the given double value to a {@link MonetaryAmount}.
   *
   * @param value the value to be converted
   * @return an instance of {@code MonetaryAmount} representing the given value
   */
  final MonetaryAmount monetaryAmount(double value) {
    return BookStoreDemo.money(value);
  }

  /**
   * Derives the value of the given {@link MonetaryAmount}.
   *
   * @param money a monetary amount, must not be {@code null}
   * @return the value represented by the given object
   */
  final double deriveDouble(MonetaryAmount money) {
    return requireNonNull(money).getNumber().doubleValueExact();
  }
}
