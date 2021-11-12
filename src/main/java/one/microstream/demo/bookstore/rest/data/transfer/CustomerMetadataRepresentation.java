package one.microstream.demo.bookstore.rest.data.transfer;

public class CustomerMetadataRepresentation {

  private int total;
  private int pageSize;
  private int pageCount;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPageCount() {
    return pageCount;
  }

  public void setPageCount(int pageCount) {
    this.pageCount = pageCount;
  }
}
