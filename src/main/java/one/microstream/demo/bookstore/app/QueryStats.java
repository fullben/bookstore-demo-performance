package one.microstream.demo.bookstore.app;

public interface QueryStats {

  String description();

  long msNanos();

  long jpaNanos();

  static QueryStats New(final String description, final long msNanos, final long jpaNanos) {
    return new Default(description, msNanos, jpaNanos);
  }

  class Default implements QueryStats {
    private final String description;
    private final long msNanos;
    private final long jpaNanos;

    Default(final String description, final long msNanos, final long jpaNanos) {
      super();
      this.description = description;
      this.msNanos = msNanos;
      this.jpaNanos = jpaNanos;
    }

    @Override
    public String description() {
      return this.description;
    }

    @Override
    public long msNanos() {
      return this.msNanos;
    }

    @Override
    public long jpaNanos() {
      return this.jpaNanos;
    }
  }
}
