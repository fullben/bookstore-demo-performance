package one.microstream.demo.bookstore.jpa.dal;

import java.util.Optional;
import one.microstream.demo.bookstore.jpa.domain.NamedEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NamedRepository<T extends NamedEntity> extends BaseRepository<T> {

  Optional<T> findByName(String name);
}
