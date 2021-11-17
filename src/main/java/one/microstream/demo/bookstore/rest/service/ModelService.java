package one.microstream.demo.bookstore.rest.service;

import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import one.microstream.demo.bookstore.BookStoreDemo;
import one.microstream.demo.bookstore.data.Data;
import one.microstream.demo.bookstore.data.Shop;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerMetadataRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service that uses MicroStream-based persistence to provide certain specific collections of values
 * and values that are necessary for determining the valid value ranges for the parameters of the
 * methods offered by {@link QueryService} implementations maintaining the same data in their
 * backing persistence solutions.
 */
@Service
public class ModelService {

  private final Data data;

  @Autowired
  public ModelService(BookStoreDemo bookStoreDemo) {
    this.data = bookStoreDemo.data();
  }

  public List<String> getAllCountryNames() {
    return data.shops()
        .compute(
            shops ->
                shops
                    .map(s -> s.address().city().state().country().name())
                    .distinct()
                    .collect(Collectors.toList()));
  }

  public List<String> getAllCountryCodes() {
    return data.shops()
        .compute(
            shops ->
                shops
                    .map(s -> s.address().city().state().country().code())
                    .distinct()
                    .collect(Collectors.toList()));
  }

  public List<Integer> getAllPurchaseYears() {
    Range<Integer> range = data.purchases().years();
    List<Integer> years = new ArrayList<>(range.upperEndpoint() - range.lowerEndpoint());
    for (int i = range.lowerEndpoint(); i <= range.upperEndpoint(); i++) {
      years.add(i);
    }
    return years;
  }

  public CustomerMetadataRepresentation getCustomerMetadata(int pageSize) {
    if (pageSize < 1) {
      throw new IllegalArgumentException();
    }
    CustomerMetadataRepresentation metaData = new CustomerMetadataRepresentation();
    metaData.setTotal(data.customers().customerCount());
    metaData.setPageSize(pageSize);
    metaData.setPageCount((int) Math.ceil((double) data.customers().customerCount() / pageSize));
    return metaData;
  }

  public List<String> getAllShopNames() {
    return data.shops().all().stream().map(Shop::name).collect(Collectors.toList());
  }
}
