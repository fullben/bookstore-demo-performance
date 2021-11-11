package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.data.Address;
import one.microstream.demo.bookstore.data.Customer;
import one.microstream.demo.bookstore.rest.data.transfer.AddressRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class CustomerConverter implements Converter<Customer, CustomerRepresentation> {

  @Override
  public CustomerRepresentation convert(
      MappingContext<Customer, CustomerRepresentation> mappingContext) {
    Customer c = mappingContext.getSource();
    CustomerRepresentation r = new CustomerRepresentation();
    r.setId(c.customerId());
    r.setName(c.name());
    Address a = c.address();
    r.setAddress(
        new AddressRepresentation(
            a.address(), a.address2(), a.zipCode(), a.city().name(), a.city().state().name()));
    return r;
  }
}
