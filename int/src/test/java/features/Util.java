package features;

import com.google.inject.Singleton;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

/**
 * Utility class containing reusable logic in tests
 * Primarily:
 *  1) a value cache to assist in persisting test state across steps
 *  2) a reference to the database driver to manage test data and state
 *
 *  TODO: Add any required utility methods in this class
 */
@Singleton
public class Util {

    @Inject
    private DatabaseDriver db;

    private Map<String, Object> cache = new HashMap<>();

    /**
     * Puts a value in the cache
     * @param key the unique key identifying the value in the cache
     * @param value the cached value
     */
    public void put(String key, Object value) {
        cache.put(key, value);
    }

    /**
     * Gets a value from the cache
     * @param key the key used to retrieve the value
     * @param <T> the Type of value stored in the cache
     * @return a value of the inferred type T
     */
    public <T> T get(String key) {
        T value = (T) cache.get(key);
        if (value == null) {
            throw new RuntimeException(
                    String.format("Value with key %s not found in cache.", key));
        }
        return value;
    }

    /**
     * Removes a value from the cache
     * @param key the key of the value to be removed
     */
    public void remove(String key) {
        cache.remove(key);
    }

    /**
     * Checks if a value exist in the cache
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return cache.containsKey(key);
    }
}
