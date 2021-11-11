package one.microstream.demo.bookstore.rest.service;

import java.util.Collection;
import one.microstream.demo.bookstore.rest.data.transfer.BookRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.EmployeeRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.PurchaseRepresentation;

public interface QueryService {

  Collection<CustomerRepresentation> customersPaged(int page);

  Collection<BookRepresentation> booksByTitleAndCountry(String title, String country);

  Collection<BookRepresentation> booksInPriceRange(double min, double max);

  double revenueOfShop(long shopId, int year);

  Collection<BookRepresentation> bestsellers(String country, int year);

  EmployeeRepresentation employeeOfTheYear(int year, String country);

  Collection<PurchaseRepresentation> purchasesOfForeigners(String country, int year);
}
