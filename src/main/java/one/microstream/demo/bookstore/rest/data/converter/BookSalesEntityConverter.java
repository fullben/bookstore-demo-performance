package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.jpa.dal.BookSales;
import one.microstream.demo.bookstore.rest.data.transfer.BookSalesRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class BookSalesEntityConverter implements Converter<BookSales, BookSalesRepresentation> {

  @Override
  public BookSalesRepresentation convert(
      MappingContext<BookSales, BookSalesRepresentation> mappingContext) {
    BookSales sales = mappingContext.getSource();
    BookSalesRepresentation r = new BookSalesRepresentation();
    r.setBook(BookEntityConverter.toRepresentation(sales.book()));
    r.setAmount(sales.amount());
    return r;
  }
}
