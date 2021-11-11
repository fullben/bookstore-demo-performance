package one.microstream.demo.bookstore;

import java.nio.file.Paths;
import one.microstream.demo.bookstore.data.RandomDataAmount;
import org.springframework.beans.factory.annotation.Value;

public class DemoConfiguration {

  @Value("${bookstoredemo.dataDir}")
  private String dataDir;

  @Value("${bookstoredemo.initialDataAmount:medium}")
  private String initialDataAmountConfiguration;

  @Value("${bookstoredemo.jpaDataMigrationStrategy:batch_insert}")
  private String jpaDataMigrationStrategy;

  public DemoConfiguration() {
    super();
  }

  public String dataDir() {
    final String dir = this.dataDir;
    return dir.startsWith("~/") || dir.startsWith("~\\")
        ? Paths.get(System.getProperty("user.home"), dir.substring(2)).toString()
        : dir;
  }

  public RandomDataAmount initialDataAmount() {
    return RandomDataAmount.valueOf(this.initialDataAmountConfiguration);
  }

  public String jpaDataMigrationStrategy() {
    return this.jpaDataMigrationStrategy;
  }
}
