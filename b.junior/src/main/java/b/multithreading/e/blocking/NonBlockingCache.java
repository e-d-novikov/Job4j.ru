package b.multithreading.e.blocking;

import java.util.concurrent.ConcurrentHashMap;

public class NonBlockingCache {

    private ConcurrentHashMap<Integer, Base> cache;

    public NonBlockingCache() {
        this.cache = new ConcurrentHashMap<Integer, Base>(16, 0.75f, 1);
    }

    public void add(Base model) {
        cache.put(model.getId(), model);
    }

    public void update(Base model) {
        cache.computeIfPresent(model.getId(), (x, y) -> {
            if (y.getVersion() == y.getVersion()) {
                model.setVersion(model.getVersion() + 1);
                return model;
            } else {
                throw new OptimisticException();
            }
        });
    }

    public void delete(Base model) {
        cache.remove(model.getId());
    }
}


