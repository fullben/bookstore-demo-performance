package one.microstream.demo.bookstore.jpa.dal;

import java.util.List;
import one.microstream.demo.bookstore.jpa.domain.CountryEntity;
import one.microstream.demo.bookstore.jpa.domain.CustomerEntity;
import one.microstream.demo.bookstore.jpa.domain.PurchaseEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PurchaseRepository extends BaseRepository<PurchaseEntity> {

  List<PurchaseEntity> findByCustomer(CustomerEntity customer);

  @Query(
      "SELECT COUNT(*) "
          + "FROM PurchaseEntity p "
          + "WHERE EXTRACT(YEAR FROM p.timestamp) = :year"
          + "  AND p.customer.address.city <> p.shop.address.city")
  long countPurchasesOfForeigners(@Param("year") int year);

  @Query(
      "SELECT COUNT(*) "
          + "FROM PurchaseEntity p "
          + "WHERE EXTRACT(YEAR FROM p.timestamp) = :year"
          + "  AND p.shop.address.city.state.country = :country "
          + "  AND p.customer.address.city <> p.shop.address.city")
  long countPurchasesOfForeigners(@Param("year") int year, @Param("country") CountryEntity country);

  @Query(
      "FROM PurchaseEntity p "
          + "WHERE EXTRACT(YEAR FROM p.timestamp) = :year"
          + "  AND p.customer.address.city <> p.shop.address.city")
  List<PurchaseEntity> findPurchasesOfForeigners(@Param("year") int year);

  @Query(
      "FROM PurchaseEntity p "
          + "WHERE EXTRACT(YEAR FROM p.timestamp) = :year"
          + "  AND p.shop.address.city.state.country = :country "
          + "  AND p.customer.address.city <> p.shop.address.city")
  List<PurchaseEntity> findPurchasesOfForeigners(
      @Param("year") int year, @Param("country") CountryEntity country);
}
