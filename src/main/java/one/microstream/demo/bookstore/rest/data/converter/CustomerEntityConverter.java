package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.jpa.domain.AddressEntity;
import one.microstream.demo.bookstore.jpa.domain.CustomerEntity;
import one.microstream.demo.bookstore.rest.data.transfer.AddressRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class CustomerEntityConverter implements Converter<CustomerEntity, CustomerRepresentation> {

  @Override
  public CustomerRepresentation convert(
      MappingContext<CustomerEntity, CustomerRepresentation> mappingContext) {
    CustomerEntity e = mappingContext.getSource();
    CustomerRepresentation r = new CustomerRepresentation();
    r.setId(e.getId());
    r.setName(e.getName());
    AddressEntity ae = e.getAddress();
    r.setAddress(
        new AddressRepresentation(
            ae.getAddress(),
            ae.getAddress2(),
            ae.getZipCode(),
            ae.getCity().getName(),
            ae.getCity().getState().getName()));
    return r;
  }
}
