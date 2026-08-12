package com.sambhavi.cache.lru;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LRUCacheTest {

    private LRUCache<Integer, String> cache;

    @BeforeEach
    void setUp() {
        cache = new LRUCache<>(2);
    }

    @Test
    void shouldInsertAndRetrieveValue() {

        cache.put(1, "One");

        assertEquals("One", cache.get(1));
    }

    @Test
    void shouldReturnNullForMissingKey() {

        assertNull(cache.get(100));
    }

    @Test
    void shouldUpdateExistingValue() {

        cache.put(1, "One");

        cache.put(1, "Updated");

        assertEquals("Updated", cache.get(1));
        assertEquals(1, cache.size());
    }

    @Test
    void shouldEvictLeastRecentlyUsedItem() {

        cache.put(1, "One");
        cache.put(2, "Two");

        cache.get(1);

        cache.put(3, "Three");

        assertNull(cache.get(2));
        assertEquals("One", cache.get(1));
        assertEquals("Three", cache.get(3));
    }

    @Test
    void shouldEvictCorrectItemWithoutAccess() {

        cache.put(1, "One");
        cache.put(2, "Two");

        cache.put(3, "Three");

        assertNull(cache.get(1));
        assertEquals("Two", cache.get(2));
        assertEquals("Three", cache.get(3));
    }

    @Test
    void shouldRemoveKey() {

        cache.put(1, "One");

        cache.remove(1);

        assertNull(cache.get(1));
        assertFalse(cache.containsKey(1));
    }

    @Test
    void shouldClearCache() {

        cache.put(1, "One");
        cache.put(2, "Two");

        cache.clear();

        assertEquals(0, cache.size());

        assertNull(cache.get(1));
        assertNull(cache.get(2));
    }

    @Test
    void shouldContainExistingKey() {

        cache.put(1, "One");

        assertTrue(cache.containsKey(1));
        assertFalse(cache.containsKey(2));
    }

    @Test
    void shouldHandleCapacityOne() {

        LRUCache<Integer, String> singleCache = new LRUCache<>(1);

        singleCache.put(1, "One");

        singleCache.put(2, "Two");

        assertNull(singleCache.get(1));
        assertEquals("Two", singleCache.get(2));
    }

    @Test
    void shouldThrowExceptionForInvalidCapacity() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new LRUCache<>(0)
        );
    }

    @Test
    void sizeShouldReflectCurrentNumberOfEntries() {

        cache.put(1, "One");
        cache.put(2, "Two");

        assertEquals(2, cache.size());

        cache.remove(1);

        assertEquals(1, cache.size());
    }

    @Test
    void getShouldMakeItemMostRecentlyUsed() {

        cache.put(1, "One");
        cache.put(2, "Two");

        cache.get(1);

        cache.put(3, "Three");

        assertNull(cache.get(2));
        assertEquals("One", cache.get(1));
        assertEquals("Three", cache.get(3));
    }

}