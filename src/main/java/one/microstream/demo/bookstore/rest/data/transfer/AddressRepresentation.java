package one.microstream.demo.bookstore.rest.data.transfer;

public class AddressRepresentation {

  private String address;
  private String address2;
  private String zipCode;
  private String city;
  private String state;

  public AddressRepresentation() {}

  public AddressRepresentation(
      String address, String address2, String zipCode, String city, String state) {
    this.address = address;
    this.address2 = address2;
    this.zipCode = zipCode;
    this.city = city;
    this.state = state;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAddress2() {
    return address2;
  }

  public void setAddress2(String address2) {
    this.address2 = address2;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
