package com.sambhavi.cache;

import com.sambhavi.cache.lru.LRUCache;

public class Main {

    public static void main(String[] args) {
        LRUCache<Integer, String> cache = new LRUCache<>(2);
        cache.put(1, "One");
        cache.put(2, "Two");
        System.out.println(cache.get(1));
        cache.put(3, "Three");
        System.out.println(cache.get(2));
    }
}
