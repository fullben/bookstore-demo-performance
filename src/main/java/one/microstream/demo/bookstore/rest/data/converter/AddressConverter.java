package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.data.Address;
import one.microstream.demo.bookstore.rest.data.transfer.AddressRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class AddressConverter implements Converter<Address, AddressRepresentation> {

  @Override
  public AddressRepresentation convert(
      MappingContext<Address, AddressRepresentation> mappingContext) {
    Address a = mappingContext.getSource();
    AddressRepresentation address = new AddressRepresentation();
    address.setAddress(a.address());
    address.setAddress2(a.address2());
    address.setCity(a.city().name());
    address.setZipCode(a.zipCode());
    address.setState(a.city().state().name());
    return address;
  }
}
