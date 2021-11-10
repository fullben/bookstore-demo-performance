package one.microstream.demo.bookstore.jpa.dal;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public interface BaseRepositoryCustom<T> {

  void batchInsert(Collection<T> entities);

  void batchInsert(BatchPreparedStatementSetter statementSetter);

  interface Dumper<E> {

    int batchSize();

    Object[] values(int index);
  }

  <E> void dump(Dumper<E> dumper, Writer writer) throws IOException;
}
