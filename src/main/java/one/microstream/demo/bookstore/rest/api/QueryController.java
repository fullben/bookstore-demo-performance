package one.microstream.demo.bookstore.rest.api;

import java.util.Collection;
import one.microstream.demo.bookstore.rest.data.transfer.BookRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.BookSalesRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.EmployeeRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.PurchaseRepresentation;
import one.microstream.demo.bookstore.rest.service.JpaQueryService;
import one.microstream.demo.bookstore.rest.service.MsQueryService;
import one.microstream.demo.bookstore.rest.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller managing the API endpoints providing access to the queries offered by the application.
 *
 * @author Benedikt Full
 */
@RequestMapping("api")
@RestController
public class QueryController {

  private static final String MODE_MS = "ms";
  private static final String MODE_JPA = "jpa";
  private final MsQueryService msQueryService;
  private final JpaQueryService jpaQueryService;

  @Autowired
  public QueryController(MsQueryService msQueryService, JpaQueryService jpaQueryService) {
    this.msQueryService = msQueryService;
    this.jpaQueryService = jpaQueryService;
  }

  @GetMapping(value = "queries/customers-page", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<CustomerRepresentation> customersPaged(
      @RequestParam(name = "mode", required = false, defaultValue = "ms") String mode,
      @RequestParam(name = "page") int page) {
    return queryService(mode).customersPaged(page);
  }

  @GetMapping(value = "queries/books-by-title", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<BookRepresentation> booksByTitleAndCountry(
      @RequestParam(name = "mode", required = false, defaultValue = "ms") String mode,
      @RequestParam(name = "title") String title,
      @RequestParam(name = "country") String country) {
    return queryService(mode).booksByTitleAndCountry(title, country);
  }

  @GetMapping(
      value = "queries/books-in-price-range",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<BookRepresentation> booksInPriceRange(
      @RequestParam(name = "mode", required = false, defaultValue = "ms") String mode,
      @RequestParam(name = "min") int min,
      @RequestParam(name = "max") int max) {
    return queryService(mode).booksInPriceRange(min, max);
  }

  @GetMapping(value = "queries/shop-revenue", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public double revenueOfShopInYear(
      @RequestParam(name = "mode", required = false, defaultValue = "ms") String mode,
      @RequestParam(name = "shop") String shopName,
      @RequestParam(name = "year") int year) {
    return queryService(mode).revenueOfShop(shopName, year);
  }

  @GetMapping(value = "queries/book-sales", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<BookSalesRepresentation> bookSalesForYearAndCountry(
      @RequestParam(name = "mode", required = false, defaultValue = "ms") String mode,
      @RequestParam(name = "country") String country,
      @RequestParam(name = "year") int year) {
    return queryService(mode).bookSales(country, year);
  }

  @GetMapping(
      value = "queries/employee-of-the-year",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public EmployeeRepresentation employeeOfTheYearInCountry(
      @RequestParam(name = "mode", required = false, defaultValue = "ms") String mode,
      @RequestParam(name = "year") int year,
      @RequestParam(name = "country") String country) {
    return queryService(mode).employeeOfTheYear(year, country);
  }

  @GetMapping(
      value = "queries/foreigner-purchases",
      produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<PurchaseRepresentation> purchasesOfForeignersInCountryAndYear(
      @RequestParam(name = "mode", required = false, defaultValue = "ms") String mode,
      @RequestParam(name = "country") String country,
      @RequestParam(name = "year") int year) {
    return queryService(mode).purchasesOfForeigners(country, year);
  }

  private QueryService queryService(String mode) {
    requireValidMode(mode);
    if (isMsMode(mode)) {
      return msQueryService;
    } else if (isJpaMode(mode)) {
      return jpaQueryService;
    } else {
      throw new IllegalStateException();
    }
  }

  private String requireValidMode(String mode) {
    if (!isValidMode(mode)) {
      throw new InvalidRequestParamException("mode");
    }
    return mode;
  }

  private boolean isValidMode(String mode) {
    return MODE_MS.equals(mode) || MODE_JPA.equals(mode);
  }

  private boolean isMsMode(String mode) {
    return MODE_MS.equals(mode);
  }

  private boolean isJpaMode(String mode) {
    return MODE_JPA.equals(mode);
  }
}
