package one.microstream.demo.bookstore.app;

import one.microstream.reference.LazyReferenceManager;
import one.microstream.storage.embedded.types.EmbeddedStorageManager;
import org.hibernate.Cache;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public interface ClearAction extends Action {

  String title();

  String verb();

  static ClearAction GarbageCollector() {
    return new Default("Garbage Collector", "Execute", System::gc);
  }

  static ClearAction LazyRefsAndObjectCache(final EmbeddedStorageManager storageManager) {
    return new Default(
        "Lazy References",
        "Clear",
        () -> {
          LazyReferenceManager.get().clear();

          //			storageManager
          //				.persistenceManager()
          //				.objectRegistry()
          //				.clear();
        });
  }

  static ClearAction StorageCache(final EmbeddedStorageManager storageManager) {
    return new Default(
        "Storage Cache",
        "Clear",
        () -> {
          storageManager.issueCacheCheck(Long.MAX_VALUE, (s, t, e) -> true);
        });
  }

  static ClearAction SessionCache(final SessionFactory sessionFactory) {
    return new Default(
        "Session Cache",
        "Clear",
        () -> {
          final Session session = sessionFactory.getCurrentSession();
          if (session != null) {
            session.clear();
          }
        });
  }

  static ClearAction SecondLevelCache(final SessionFactory sessionFactory) {
    return new Default(
        "Second-Level Cache",
        "Clear",
        () -> {
          final Cache cache = sessionFactory.getCache();
          if (cache != null) {
            cache.evictAllRegions();
          }
        });
  }

  static ClearAction New(final String title, final String verb, final Runnable logic) {
    return new Default(title, verb, logic);
  }

  class Default implements ClearAction {
    private final String title;
    private final String verb;
    private final Runnable logic;

    Default(final String title, final String verb, final Runnable logic) {
      super();
      this.title = title;
      this.verb = verb;
      this.logic = logic;
    }

    @Override
    public String title() {
      return this.title;
    }

    @Override
    public String verb() {
      return this.verb;
    }

    @Override
    public String description() {
      return this.verb + " " + this.title;
    }

    @Override
    public Runnable logic() {
      return this.logic;
    }
  }
}
