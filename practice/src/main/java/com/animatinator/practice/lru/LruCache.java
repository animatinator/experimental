package com.animatinator.practice.lru;

import java.util.Optional;

public interface LruCache<T> {
    void addToCache(long id, T entry);
    Optional<T> getFromCache(long id);
}
