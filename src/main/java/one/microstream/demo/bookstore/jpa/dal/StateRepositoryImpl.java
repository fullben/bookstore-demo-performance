package one.microstream.demo.bookstore.jpa.dal;

import java.util.Collection;
import one.microstream.demo.bookstore.jpa.domain.StateEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class StateRepositoryImpl extends BaseRepositoryImpl<StateEntity> {

  public StateRepositoryImpl() {
    super();
  }

  @Transactional
  @Override
  public void batchInsert(final Collection<StateEntity> entities) {
    this.batchInsert(
        entities,
        (ps, entity) -> {
          ps.setLong(1, entity.getId());
          ps.setString(2, entity.getName());
          ps.setLong(3, entity.getCountry().getId());
        });
  }

  @Override
  protected String insertSql() {
    return "INSERT INTO STATE " + "(ID,NAME,COUNTRY_ID) " + "VALUES(?,?,?)";
  }

  @Override
  protected String copySql() {
    return "COPY STATE " + "(ID,NAME,COUNTRY_ID)";
  }
}
