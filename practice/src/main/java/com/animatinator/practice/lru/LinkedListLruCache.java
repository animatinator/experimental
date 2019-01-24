package com.animatinator.practice.lru;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LinkedListLruCache<T> implements LruCache<T> {

    private final int maxSize;
    private final Map<Long, CacheEntry> cacheMap = new HashMap<>();

    private int currentSize = 0;
    // Least recently used, on the chopping block.
    private CacheEntry firstEntry = null;
    // Most recently used, safe.
    private CacheEntry lastEntry = null;

    LinkedListLruCache(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void addToCache(long id, T valueToAdd) {
        CacheEntry newEntry = new CacheEntry(id, valueToAdd);

        cacheMap.put(id, newEntry);
        addNewEntryToLinkedList(newEntry);

        currentSize++;
        if (currentSize > maxSize) {
            trimToMaxSize();
        }
    }

    @Override
    public Optional<T> getFromCache(long id) {
        if (cacheMap.containsKey(id)) {
            CacheEntry retrievedEntry = cacheMap.get(id);
            promoteToMostRecentlyUsed(retrievedEntry);

            return Optional.of(retrievedEntry.value);
        }
        return Optional.empty();
    }

    private void addNewEntryToLinkedList(CacheEntry newEntry) {
        if (firstEntry == null || lastEntry == null) {
            // firstEntry and lastEntry should always be present or null at the same time.
            assert(firstEntry == null && lastEntry == null);

            firstEntry = newEntry;
            lastEntry = newEntry;
        } else {
            putAtEndOfList(newEntry);
        }
    }

    private void trimToMaxSize() {
        while (currentSize > maxSize) {
            dropLeastRecentlyUsed();
        }
    }

    private void dropLeastRecentlyUsed() {
        // Remove it from the map
        CacheEntry entryToRemove = firstEntry;
        cacheMap.remove(entryToRemove.id);

        // Promote the next entry in the list to firstEntry
        if (firstEntry.next != null) {
            firstEntry.next.previous = null;
        }
        firstEntry = firstEntry.next;

        currentSize--;
    }

    private void promoteToMostRecentlyUsed(CacheEntry entry) {
        removeFromCurrentLocation(entry);
        putAtEndOfList(entry);
    }

    private void removeFromCurrentLocation(CacheEntry entry) {
        // Update the first and last references if necessary.
        if (firstEntry == entry) {
            firstEntry = entry.next;
        }
        if (lastEntry == entry) {
            lastEntry = entry.previous;
        }

        // Join its left neighbour to its right
        if (entry.previous != null) {
            entry.previous.next = entry.next;
        }
        // Join its right neighbour to its left
        if (entry.next != null) {
            entry.next.previous = entry.previous;
        }
    }

    private void putAtEndOfList(CacheEntry entry) {
        if (lastEntry != null) {
            lastEntry.next = entry;
        }
        entry.previous = lastEntry;
        entry.next = null;
        lastEntry = entry;
    }

    private final class CacheEntry {
        CacheEntry previous;
        CacheEntry next;

        long id;
        T value;

        CacheEntry(long id, T value) {
            this.id = id;
            this.value = value;
        }
    }
}
