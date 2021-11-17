package one.microstream.demo.bookstore.rest.service;

import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.money.MonetaryAmount;
import one.microstream.demo.bookstore.jpa.dal.Repositories;
import one.microstream.demo.bookstore.jpa.domain.CountryEntity;
import one.microstream.demo.bookstore.jpa.domain.EmployeeEntity;
import one.microstream.demo.bookstore.jpa.domain.ShopEntity;
import one.microstream.demo.bookstore.rest.data.converter.AddressEntityConverter;
import one.microstream.demo.bookstore.rest.data.converter.BookEntityConverter;
import one.microstream.demo.bookstore.rest.data.converter.CustomerEntityConverter;
import one.microstream.demo.bookstore.rest.data.converter.EmployeeEntityConverter;
import one.microstream.demo.bookstore.rest.data.converter.PurchaseEntityConverter;
import one.microstream.demo.bookstore.rest.data.transfer.BookRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.EmployeeRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.PurchaseRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class JpaQueryService extends BaseQueryService {

  private final Repositories repositories;

  @Autowired
  public JpaQueryService(Repositories repositories) {
    super();
    this.repositories = repositories;
  }

  @Override
  ModelMapper configureModelMapper(ModelMapper mapper) {
    mapper.addConverter(new AddressEntityConverter());
    mapper.addConverter(new BookEntityConverter());
    mapper.addConverter(new CustomerEntityConverter());
    mapper.addConverter(new EmployeeEntityConverter());
    mapper.addConverter(new PurchaseEntityConverter());
    return mapper;
  }

  @Transactional(readOnly = true)
  @Override
  public Collection<CustomerRepresentation> customersPaged(int page) {
    return repositories
        .customerRepository()
        .findAll(PageRequest.of(page, defaultPageSize()))
        .stream()
        .map(c -> map(c, CustomerRepresentation.class))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public Collection<BookRepresentation> booksByTitleAndCountry(String title, String country) {
    CountryEntity countryEntity =
        repositories
            .countryRepository()
            .findByCode(country)
            .orElseThrow(() -> new IllegalArgumentException("No country for code: " + country));
    return repositories
        .bookRepository()
        .findByTitleLikeAndAuthorAddressCityStateCountry(title, countryEntity)
        .stream()
        .map(b -> map(b, BookRepresentation.class))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public Collection<BookRepresentation> booksInPriceRange(double min, double max) {
    final MonetaryAmount minPrice = monetaryAmount(min);
    final MonetaryAmount maxPrice = monetaryAmount(max);
    return repositories
        .bookRepository()
        .findByRetailPriceGreaterThanEqualAndRetailPriceLessThan(minPrice, maxPrice)
        .stream()
        .map(b -> map(b, BookRepresentation.class))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public double revenueOfShop(String shopName, int year) {
    ShopEntity shop =
        repositories
            .shopRepository()
            .findByName(shopName)
            .orElseThrow(
                () -> new IllegalArgumentException("No shop with name: \"" + shopName + "\""));
    return repositories.purchaseItemRepository().revenueOfShopInYear(shop, year);
  }

  @Transactional(readOnly = true)
  @Override
  public Collection<BookRepresentation> bestsellers(String country, int year) {
    return repositories.purchaseItemRepository().bestSellerList(year, getCountry(country)).stream()
        .map(b -> map(b.book(), BookRepresentation.class))
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public EmployeeRepresentation employeeOfTheYear(int year, String country) {
    EmployeeEntity employee =
        repositories.employeeRepository().employeeOfTheYear(year, getCountry(country).getId());
    return employee == null ? null : map(employee, EmployeeRepresentation.class);
  }

  @Transactional(readOnly = true)
  @Override
  public Collection<PurchaseRepresentation> purchasesOfForeigners(String country, int year) {
    return repositories
        .purchaseRepository()
        .findPurchasesOfForeigners(year, getCountry(country))
        .stream()
        .map(p -> map(p, PurchaseRepresentation.class))
        .collect(Collectors.toList());
  }

  private CountryEntity getCountry(String country) {
    return repositories
        .countryRepository()
        .findByCode(country.toUpperCase(Locale.ROOT))
        .orElseThrow(() -> new IllegalArgumentException("No country for code: " + country));
  }
}
