package one.microstream.demo.bookstore.app;

public interface ExecutionCallback {

  void beforeExecution(Action action);

  void afterExecution(Action action);

  void queueUpdated();
}
