package one.microstream.demo.bookstore.data;

/**
 * View of a shop's inventory item slot.
 *
 * <p>This type is immutable and therefore inherently thread safe.
 */
public class InventoryItem {

  private final Book book;
  private final int amount;
  private final Shop shop;

  /**
   * Constructor to create a new {@link InventoryItem} instance.
   *
   * @param shop not <code>null</code>
   * @param book not <code>null</code>
   * @param amount positive amount
   */
  public InventoryItem(final Shop shop, final Book book, final int amount) {
    super();
    this.shop = shop;
    this.book = book;
    this.amount = amount;
  }

  /**
   * Get the shop which this inventory item belongs to
   *
   * @return the shop
   */
  public Shop shop() {
    return this.shop;
  }

  /**
   * Get the book of this inventory item
   *
   * @return the book
   */
  public Book book() {
    return this.book;
  }

  /**
   * Get the amount of this inventory item
   *
   * @return the amount
   */
  public int amount() {
    return this.amount;
  }
}
