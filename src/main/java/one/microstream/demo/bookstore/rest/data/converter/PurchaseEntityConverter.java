package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.jpa.domain.PurchaseEntity;
import one.microstream.demo.bookstore.rest.data.transfer.PurchaseRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class PurchaseEntityConverter implements Converter<PurchaseEntity, PurchaseRepresentation> {

  @Override
  public PurchaseRepresentation convert(
      MappingContext<PurchaseEntity, PurchaseRepresentation> mappingContext) {
    PurchaseEntity p = mappingContext.getSource();
    PurchaseRepresentation r = new PurchaseRepresentation();
    r.setAmount(-1);
    r.setCustomer(p.getCustomer().getName());
    r.setCustomerCountryCode(
        p.getCustomer().getAddress().getCity().getState().getCountry().getCode());
    r.setEmployee(p.getEmployee().getName());
    r.setTimestamp(p.getTimestamp());
    r.setItemCount(p.getItems().size());
    return r;
  }
}
