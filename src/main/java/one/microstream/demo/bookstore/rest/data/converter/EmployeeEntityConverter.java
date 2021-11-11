package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.jpa.domain.AddressEntity;
import one.microstream.demo.bookstore.jpa.domain.EmployeeEntity;
import one.microstream.demo.bookstore.rest.data.transfer.AddressRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.EmployeeRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class EmployeeEntityConverter implements Converter<EmployeeEntity, EmployeeRepresentation> {

  @Override
  public EmployeeRepresentation convert(
      MappingContext<EmployeeEntity, EmployeeRepresentation> mappingContext) {
    EmployeeEntity e = mappingContext.getSource();
    EmployeeRepresentation r = new EmployeeRepresentation();
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
