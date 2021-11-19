package one.microstream.demo.bookstore.rest.api;

import java.util.Collection;
import java.util.List;
import one.microstream.demo.bookstore.rest.data.transfer.CustomerMetadataRepresentation;
import one.microstream.demo.bookstore.rest.service.JpaModelService;
import one.microstream.demo.bookstore.rest.service.ModelService;
import one.microstream.demo.bookstore.rest.service.MsModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller managing the API endpoints providing access to data (primarily metadata) necessary for
 * using safe values for the parameters of the endpoints provided by the {@link QueryController}.
 *
 * @author Benedikt Full
 */
@RequestMapping("api")
@RestController
public class ModelController extends ModeBasedController {

  private final MsModelService msModelService;
  private final JpaModelService jpaModelService;

  @Autowired
  public ModelController(MsModelService msModelService, JpaModelService jpaModelService) {
    this.msModelService = msModelService;
    this.jpaModelService = jpaModelService;
  }

  @GetMapping(value = "countries/codes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<String> allCountryCodes(
      @RequestParam(name = "mode", required = false, defaultValue = MODE_MS) String mode) {
    return selectModelService(mode).getAllCountryCodes();
  }

  @GetMapping(value = "purchases/years", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<Integer> allPurchaseYears(
      @RequestParam(name = "mode", required = false, defaultValue = MODE_MS) String mode) {
    return selectModelService(mode).getAllPurchaseYears();
  }

  @GetMapping(value = "customers/metadata", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public CustomerMetadataRepresentation customersPageCount(
      @RequestParam(name = "mode", required = false, defaultValue = MODE_MS) String mode,
      @RequestParam(name = "size", required = false, defaultValue = "100") int pageSize) {
    return selectModelService(mode).getCustomerMetadata(pageSize);
  }

  @GetMapping(value = "shops/names", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public List<String> shopNames(
      @RequestParam(name = "mode", required = false, defaultValue = MODE_MS) String mode) {
    return selectModelService(mode).getAllShopNames();
  }

  private ModelService selectModelService(String mode) {
    requireValidMode(mode);
    if (isMsMode(mode)) {
      return msModelService;
    } else if (isJpaMode(mode)) {
      return jpaModelService;
    } else {
      throw new IllegalStateException();
    }
  }
}
