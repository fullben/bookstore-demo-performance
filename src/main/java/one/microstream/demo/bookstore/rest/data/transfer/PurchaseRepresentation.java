package one.microstream.demo.bookstore.rest.data.transfer;

import java.time.LocalDateTime;

public class PurchaseRepresentation {

  private String employee;
  private String customer;
  private String customerCountryCode;
  private LocalDateTime timestamp;
  private int itemCount;
  private double amount;

  public String getEmployee() {
    return employee;
  }

  public void setEmployee(String employee) {
    this.employee = employee;
  }

  public String getCustomer() {
    return customer;
  }

  public void setCustomer(String customer) {
    this.customer = customer;
  }

  public String getCustomerCountryCode() {
    return customerCountryCode;
  }

  public void setCustomerCountryCode(String customerCountryCode) {
    this.customerCountryCode = customerCountryCode;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(LocalDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public int getItemCount() {
    return itemCount;
  }

  public void setItemCount(int itemCount) {
    this.itemCount = itemCount;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }
}
