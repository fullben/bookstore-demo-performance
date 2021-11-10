package one.microstream.demo.bookstore.jpa.dal;

import java.util.Optional;
import one.microstream.demo.bookstore.jpa.domain.NamedWithCodeEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedWithCodeRepository<T extends NamedWithCodeEntity> extends NamedRepository<T> {

  Optional<T> findByCode(String code);
}
