package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.jpa.domain.AddressEntity;
import one.microstream.demo.bookstore.rest.data.transfer.AddressRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class AddressEntityConverter implements Converter<AddressEntity, AddressRepresentation> {

  @Override
  public AddressRepresentation convert(
      MappingContext<AddressEntity, AddressRepresentation> mappingContext) {
    AddressEntity e = mappingContext.getSource();
    AddressRepresentation address = new AddressRepresentation();
    address.setAddress(e.getAddress());
    address.setAddress2(e.getAddress2());
    address.setCity(e.getCity().getName());
    address.setZipCode(e.getZipCode());
    address.setState(e.getCity().getState().getName());
    return address;
  }
}
