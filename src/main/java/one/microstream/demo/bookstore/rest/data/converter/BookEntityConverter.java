package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.jpa.domain.BookEntity;
import one.microstream.demo.bookstore.rest.data.transfer.BookRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class BookEntityConverter implements Converter<BookEntity, BookRepresentation> {

  @Override
  public BookRepresentation convert(MappingContext<BookEntity, BookRepresentation> mappingContext) {
    return toRepresentation(mappingContext.getSource());
  }

  public static BookRepresentation toRepresentation(BookEntity e) {
    BookRepresentation r = new BookRepresentation();
    r.setAuthor(e.getAuthor().getName());
    r.setGenre(e.getGenre().getName());
    r.setIsbn13(e.getIsbn13());
    r.setLanguage(e.getLanguage().getLanguageTag());
    r.setPublisher(e.getPublisher().getName());
    r.setPurchasePrice(e.getPurchasePrice().getNumber().doubleValueExact());
    r.setRetailPrice(e.getRetailPrice().getNumber().doubleValueExact());
    return r;
  }
}
