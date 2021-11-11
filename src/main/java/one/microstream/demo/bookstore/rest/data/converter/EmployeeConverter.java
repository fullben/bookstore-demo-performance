package one.microstream.demo.bookstore.rest.data.converter;

import one.microstream.demo.bookstore.data.Address;
import one.microstream.demo.bookstore.data.Employee;
import one.microstream.demo.bookstore.rest.data.transfer.AddressRepresentation;
import one.microstream.demo.bookstore.rest.data.transfer.EmployeeRepresentation;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class EmployeeConverter implements Converter<Employee, EmployeeRepresentation> {

  @Override
  public EmployeeRepresentation convert(
      MappingContext<Employee, EmployeeRepresentation> mappingContext) {
    Employee e = mappingContext.getSource();
    EmployeeRepresentation r = new EmployeeRepresentation();
    r.setId(-1);
    r.setName(e.name());
    Address a = e.address();
    r.setAddress(
        new AddressRepresentation(
            a.address(), a.address2(), a.zipCode(), a.city().name(), a.city().state().name()));
    return r;
  }
}
