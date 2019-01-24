package com.animatinator.practice.lru;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class LinkedListLruCacheTest {

    private static final String HELLO_WORLD = "Hello world";
    private static final long FIRST_ID = 1L;

    private LinkedListLruCache<String> cache;

    @Before
    public void setUp() {
        cache = new LinkedListLruCache<>(3);
    }

    @Test
    public void cacheWithNoCapacity() {
        cache = new LinkedListLruCache<>(0);
        cache.addToCache(FIRST_ID, HELLO_WORLD);

        assertFalse(cache.getFromCache(FIRST_ID).isPresent());
    }

    @Test
    public void addThenRetrieve() {
        cache.addToCache(FIRST_ID, HELLO_WORLD);

        Optional<String> retrievedValue = cache.getFromCache(1L);

        assertTrue(retrievedValue.isPresent());
        assertEquals("Hello world", retrievedValue.get());
    }

    @Test
    public void addThenRetrieve_farTooMany_shouldBeCleared() {
        cache.addToCache(FIRST_ID, HELLO_WORLD);
        addToCache(20, 2L);

        Optional<String> retrievedValue = cache.getFromCache(1L);

        assertFalse(retrievedValue.isPresent());
    }

    @Test
    public void recentlyUsed_doesntGetCleared() {
        cache.addToCache(FIRST_ID, HELLO_WORLD);
        addToCache(2, 2L);

        // Should be the most recently used now!
        cache.getFromCache(FIRST_ID);

        addToCache(2, 4L);

        assertTrue(cache.getFromCache(FIRST_ID).isPresent());
    }

    private void addToCache(int numItems, long firstId) {
        for (long i = firstId; i < firstId + numItems; i++) {
            cache.addToCache(i, ""+i);
        }
    }
}