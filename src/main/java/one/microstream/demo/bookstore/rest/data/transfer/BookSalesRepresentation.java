package one.microstream.demo.bookstore.rest.data.transfer;

public class BookSalesRepresentation {

  private BookRepresentation book;
  private long amount;

  public BookSalesRepresentation() {
    book = null;
    amount = 0;
  }

  public BookRepresentation getBook() {
    return book;
  }

  public void setBook(BookRepresentation book) {
    this.book = book;
  }

  public long getAmount() {
    return amount;
  }

  public void setAmount(long amount) {
    this.amount = amount;
  }
}
