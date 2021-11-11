package one.microstream.demo.bookstore.rest.service;

import com.google.common.collect.Range;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import one.microstream.demo.bookstore.BookStoreDemo;
import one.microstream.demo.bookstore.data.Data;
import org.springframework.stereotype.Service;

@Service
public class ModelService {

  private final Data data;

  public ModelService() {
    this.data = BookStoreDemo.getInstance().data();
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
}
