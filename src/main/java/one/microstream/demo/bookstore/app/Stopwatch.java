package one.microstream.demo.bookstore.app;

import java.util.function.LongSupplier;

public interface Stopwatch {

  long stop();

  long restart();

  static Stopwatch Start() {
    return new Default(System::currentTimeMillis);
  }

  static Stopwatch StartNanotime() {
    return new Default(System::nanoTime);
  }

  class Default implements Stopwatch {
    private final LongSupplier timeSupplier;
    private long start;

    public Default(final LongSupplier timeSupplier) {
      super();
      this.start = (this.timeSupplier = timeSupplier).getAsLong();
    }

    @Override
    public long stop() {
      return this.timeSupplier.getAsLong() - this.start;
    }

    @Override
    public long restart() {
      final long now = this.timeSupplier.getAsLong();
      final long duration = now - this.start;
      this.start = now;
      return duration;
    }
  }
}
