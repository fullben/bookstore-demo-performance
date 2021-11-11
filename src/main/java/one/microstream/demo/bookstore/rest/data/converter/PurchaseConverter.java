package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.data.Purchase;
import one.microstream.demo.bookstore.rest.data.transfer.PurchaseRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class PurchaseConverter implements Converter<Purchase, PurchaseRepresentation> {

  @Override
  public PurchaseRepresentation convert(
      MappingContext<Purchase, PurchaseRepresentation> mappingContext) {
    Purchase p = mappingContext.getSource();
    PurchaseRepresentation r = new PurchaseRepresentation();
    r.setAmount(p.total().getNumber().doubleValueExact());
    r.setCustomer(p.customer().name());
    r.setCustomerCountryCode(p.customer().address().city().state().country().code());
    r.setEmployee(p.employee().name());
    r.setTimestamp(p.timestamp());
    r.setItemCount(p.itemCount());
    return r;
  }
}
