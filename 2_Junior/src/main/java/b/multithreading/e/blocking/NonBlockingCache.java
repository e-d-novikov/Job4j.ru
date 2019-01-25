package b.multithreading.e.blocking;

import java.util.concurrent.ConcurrentHashMap;
/**
 * Class NonBlockingCache.
 * @author Egor Novikov
 * E-mail: e.novikov@yahoo.com
 * @version 1$
 * @since 0.1
 */
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
            if (y.getVersion() != y.getVersion()) {
                if (model.getVersion() < y.getVersion()) {
                    throw new OptimisticException();
                }
            }
            y.setVersion(y.getVersion() + 1);
            return y;
        });
    }

    public void delete(Base model) {
        cache.remove(model.getId());
    }
}


