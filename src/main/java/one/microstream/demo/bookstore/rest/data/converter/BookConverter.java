package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.data.Book;
import one.microstream.demo.bookstore.rest.data.transfer.BookRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class BookConverter implements Converter<Book, BookRepresentation> {

  @Override
  public BookRepresentation convert(MappingContext<Book, BookRepresentation> mappingContext) {
    Book b = mappingContext.getSource();
    BookRepresentation r = new BookRepresentation();
    r.setAuthor(b.author().name());
    r.setGenre(b.genre().name());
    r.setIsbn13(b.isbn13());
    r.setLanguage(b.language().name());
    r.setPublisher(b.publisher().name());
    r.setPurchasePrice(b.purchasePrice().getNumber().doubleValueExact());
    r.setRetailPrice(b.retailPrice().getNumber().doubleValueExact());
    return r;
  }
}
