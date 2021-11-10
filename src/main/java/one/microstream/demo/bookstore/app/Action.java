package one.microstream.demo.bookstore.app;

public interface Action {

  String description();

  Runnable logic();
}
