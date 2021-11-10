package one.microstream.demo.bookstore.jpa.dal;

import java.util.Optional;
import one.microstream.demo.bookstore.jpa.domain.AddressEntity;
import one.microstream.demo.bookstore.jpa.domain.NamedWithAddressEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedWithAddressRepository<T extends NamedWithAddressEntity>
    extends NamedRepository<T> {

  Optional<T> findByAddress(AddressEntity address);
}
