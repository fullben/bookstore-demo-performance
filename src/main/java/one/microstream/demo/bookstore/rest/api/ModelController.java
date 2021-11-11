package one.microstream.demo.bookstore.rest.api;

import java.util.Collection;
import one.microstream.demo.bookstore.rest.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api")
@RestController
public class ModelController {

  private final ModelService modelService;

  @Autowired
  public ModelController(ModelService modelService) {
    this.modelService = modelService;
  }

  @GetMapping(value = "countries/names", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<String> allCountryNames() {
    return modelService.getAllCountryNames();
  }

  @GetMapping(value = "countries/codes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<String> allCountryCodes() {
    return modelService.getAllCountryCodes();
  }

  @GetMapping(value = "purchases/years", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
  public Collection<Integer> allPurchaseYears() {
    return modelService.getAllPurchaseYears();
  }
}
