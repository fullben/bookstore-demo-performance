package one.microstream.demo.bookstore.rest.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import one.microstream.demo.bookstore.jpa.dal.CountryRepository;
import one.microstream.demo.bookstore.jpa.dal.CustomerRepository;
import one.microstream.demo.bookstore.jpa.dal.PurchaseRepository;
import one.microstream.demo.bookstore.jpa.dal.ShopRepository;
import one.microstream.demo.bookstore.jpa.domain.CountryEntity;
import one.microstream.demo.bookstore.jpa.domain.NamedEntity;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerMetadataRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link ModelService} implementation that uses JPA-based persistence as backing persistence
 * approach.
 *
 * @see MsModelService
 * @author Benedikt Full
 */
@Service
public class JpaModelService implements ModelService {

  private final CountryRepository countryRepository;
  private final PurchaseRepository purchaseRepository;
  private final ShopRepository shopRepository;
  private final CustomerRepository customerRepository;

  @Autowired
  public JpaModelService(
      CountryRepository countryRepository,
      PurchaseRepository purchaseRepository,
      ShopRepository shopRepository,
      CustomerRepository customerRepository) {
    this.countryRepository = countryRepository;
    this.purchaseRepository = purchaseRepository;
    this.shopRepository = shopRepository;
    this.customerRepository = customerRepository;
  }

  @Transactional(readOnly = true)
  @Override
  public List<String> getAllCountryCodes() {
    return StreamSupport.stream(countryRepository.findAll().spliterator(), false)
        .map(CountryEntity::getCode)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public List<Integer> getAllPurchaseYears() {
    return StreamSupport.stream(purchaseRepository.findAll().spliterator(), false)
        .map(p -> p.getTimestamp().getYear())
        .distinct()
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  @Override
  public CustomerMetadataRepresentation getCustomerMetadata(int pageSize) {
    if (pageSize < 1) {
      throw new ResourceNotFoundException("Page size must be greater than zero");
    }
    CustomerMetadataRepresentation metaData = new CustomerMetadataRepresentation();
    metaData.setTotal((int) customerRepository.count());
    metaData.setPageSize(pageSize);
    metaData.setPageCount((int) Math.ceil((double) metaData.getTotal() / pageSize));
    return metaData;
  }

  @Transactional(readOnly = true)
  @Override
  public List<String> getAllShopNames() {
    return StreamSupport.stream(shopRepository.findAll().spliterator(), false)
        .map(NamedEntity::getName)
        .collect(Collectors.toList());
  }
}
