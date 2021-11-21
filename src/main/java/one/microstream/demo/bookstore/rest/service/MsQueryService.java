package one.microstream.demo.bookstore.rest.service;

import java.util.Collection;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.money.MonetaryAmount;
import one.microstream.demo.bookstore.BookStoreDemo;
import one.microstream.demo.bookstore.data.Country;
import one.microstream.demo.bookstore.data.Data;
import one.microstream.demo.bookstore.data.Employee;
import one.microstream.demo.bookstore.data.Shop;
import one.microstream.demo.bookstore.rest.data.converter.AddressConverter;
import one.microstream.demo.bookstore.rest.data.converter.BookConverter;
import one.microstream.demo.bookstore.rest.data.converter.BookSalesConverter;
import one.microstream.demo.bookstore.rest.data.converter.CustomerConverter;
import one.microstream.demo.bookstore.rest.data.converter.EmployeeConverter;
import one.microstream.demo.bookstore.rest.data.converter.PurchaseConverter;
import one.microstream.demo.bookstore.rest.data.transfer.BookRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.BookSalesRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.EmployeeRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.PurchaseRepresentation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * {@link QueryService} implementation that uses MicroStream-based persistence as backing
 * persistence approach.
 *
 * @see JpaQueryService
 * @author Benedikt Full
 */
@Service
public class MsQueryService extends BaseQueryService {

  private final Data data;

  @Autowired
  public MsQueryService(BookStoreDemo bookStoreDemo) {
    super();
    this.data = bookStoreDemo.data();
  }

  @Override
  ModelMapper configureModelMapper(ModelMapper mapper) {
    mapper.addConverter(new AddressConverter());
    mapper.addConverter(new BookConverter());
    mapper.addConverter(new BookSalesConverter());
    mapper.addConverter(new CustomerConverter());
    mapper.addConverter(new EmployeeConverter());
    mapper.addConverter(new PurchaseConverter());
    return mapper;
  }

  @Override
  public Collection<CustomerRepresentation> customersPaged(int page) {
    int pageSize = defaultPageSize();
    return data.customers()
        .compute(
            customers ->
                customers
                    .skip((long) page * pageSize)
                    .limit(pageSize)
                    .map(c -> map(c, CustomerRepresentation.class))
                    .collect(Collectors.toList()));
  }

  @Override
  public Collection<BookRepresentation> booksByTitleAndCountry(String title, String countryCode) {
    return data.books().searchByTitle(title).stream()
        .filter(book -> book.author().address().city().state().country().code().equals(countryCode))
        .map(b -> map(b, BookRepresentation.class))
        .collect(Collectors.toList());
  }

  @Override
  public Collection<BookRepresentation> booksInPriceRange(double min, double max) {
    final MonetaryAmount minPrice = monetaryAmount(min);
    final MonetaryAmount maxPrice = monetaryAmount(max);
    return data.books()
        .compute(
            books ->
                books
                    .filter(
                        b ->
                            b.retailPrice().isGreaterThanOrEqualTo(minPrice)
                                && b.retailPrice().isLessThan(maxPrice))
                    .map(b -> map(b, BookRepresentation.class))
                    .collect(Collectors.toList()));
  }

  @Override
  public double revenueOfShop(String shopName, int year) {
    final Shop shop = data.shops().ofName(shopName);
    if (shop == null) {
      throw new ResourceNotFoundException("No shop with name: \"" + shopName + "\"");
    }
    MonetaryAmount revenue = data.purchases().revenueOfShopInYear(shop, year);
    return deriveDouble(revenue);
  }

  @Override
  public Collection<BookSalesRepresentation> bookSales(String countryCode, int year) {
    Country c = getCountry(countryCode);
    return data.purchases().bestSellerList(year, c).stream()
        .map(b -> map(b, BookSalesRepresentation.class))
        .collect(Collectors.toList());
  }

  @Override
  public EmployeeRepresentation employeeOfTheYear(int year, String countryCode) {
    Employee employeeOfTheYear = data.purchases().employeeOfTheYear(year, getCountry(countryCode));
    return map(employeeOfTheYear, EmployeeRepresentation.class);
  }

  @Override
  public Collection<PurchaseRepresentation> purchasesOfForeigners(String countryCode, int year) {
    return data.purchases().purchasesOfForeigners(year, getCountry(countryCode)).stream()
        .map(p -> map(p, PurchaseRepresentation.class))
        .collect(Collectors.toList());
  }

  private Country getCountry(String countryCode) {
    String code = countryCode.toUpperCase(Locale.ROOT);
    return data.shops()
        .compute(
            shops ->
                shops
                    .map(s -> s.address().city().state().country())
                    .filter(c -> c.code().equals(code))
                    .findAny()
                    .orElseThrow(
                        () ->
                            new ResourceNotFoundException("No country for code: " + countryCode)));
  }
}
