package one.microstream.demo.bookstore.jpa.dal;

import java.util.Collection;
import one.microstream.demo.bookstore.jpa.domain.GenreEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenreRepositoryImpl extends BaseRepositoryImpl<GenreEntity> {

  public GenreRepositoryImpl() {
    super();
  }

  @Transactional
  @Override
  public void batchInsert(final Collection<GenreEntity> entities) {
    this.batchInsert(
        entities,
        (ps, entity) -> {
          ps.setLong(1, entity.getId());
          ps.setString(2, entity.getName());
        });
  }

  @Override
  protected String insertSql() {
    return "INSERT INTO GENRE " + "(ID,NAME) " + "VALUES(?,?)";
  }

  @Override
  protected String copySql() {
    return "COPY GENRE " + "(ID,NAME)";
  }
}
