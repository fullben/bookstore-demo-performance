package one.microstream.demo.bookstore.jpa.dal;

import java.util.Collection;
import one.microstream.demo.bookstore.jpa.domain.PurchaseItemEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PurchaseItemRepositoryImpl extends BaseRepositoryImpl<PurchaseItemEntity> {

  public PurchaseItemRepositoryImpl() {
    super();
  }

  @Transactional
  @Override
  public void batchInsert(final Collection<PurchaseItemEntity> entities) {
    this.batchInsert(
        entities,
        (ps, entity) -> {
          ps.setLong(1, entity.getId());
          ps.setLong(2, entity.getPurchase().getId());
          ps.setLong(3, entity.getBook().getId());
          ps.setInt(4, entity.getAmount());
          ps.setDouble(5, entity.getPrice().getNumber().doubleValue());
        });
  }

  @Override
  protected String insertSql() {
    return "INSERT INTO PURCHASEITEM "
        + "(ID,PURCHASE_ID,BOOK_ID,AMOUNT,PRICE) "
        + "VALUES(?,?,?,?,?)";
  }

  @Override
  protected String copySql() {
    return "COPY PURCHASEITEM " + "(ID,PURCHASE_ID,BOOK_ID,AMOUNT,PRICE)";
  }
}
