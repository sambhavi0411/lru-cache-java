package com.sambhavi.cache.lru;

import com.sambhavi.cache.cache.Cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity;
    private final Map<K, V> entries;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        this.capacity = capacity;
        // Use LinkedHashMap in access-order mode.
        // This means the map keeps entries ordered by most recent access,
        // not by insertion order.
        this.entries = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                // LinkedHashMap calls this method automatically after a put().
                // If it returns true, LinkedHashMap removes the eldest entry.
                // Here, eldest means the least recently used entry.
                return size() > capacity;
            }
        };
    }

    @Override
    public V get(K key) {
        return entries.get(key);
    }

    @Override
    public void put(K key, V value) {
        entries.put(key, value);
    }

    @Override
    public void remove(K key) {
        entries.remove(key);
    }

    @Override
    public boolean containsKey(K key) {
        return entries.containsKey(key);
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public void clear() {
        entries.clear();
    }
}
