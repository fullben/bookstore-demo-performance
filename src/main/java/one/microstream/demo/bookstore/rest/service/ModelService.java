package one.microstream.demo.bookstore.rest.service;

import java.util.List;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerMetadataRepresentation;

/**
 * Should be implemented for each persistence approach that is part of this performance demo.
 *
 * <p>Service that uses MicroStream-based persistence to provide certain specific collections of
 * values and values that are necessary for determining the valid value ranges for the parameters of
 * the methods offered by {@link QueryService} implementations maintaining the same data in their
 * backing persistence solutions.
 *
 * @author Benedikt Full
 */
public interface ModelService {

  List<String> getAllCountryCodes();

  List<Integer> getAllPurchaseYears();

  CustomerMetadataRepresentation getCustomerMetadata(int pageSize);

  List<String> getAllShopNames();
}
