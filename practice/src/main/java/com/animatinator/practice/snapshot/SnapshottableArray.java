package com.animatinator.practice.snapshot;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SnapshottableArray<T> {

    private final class ArrayEntry {
        int timestamp;
        T value;

        ArrayEntry(int timestamp, T value) {
            this.timestamp = timestamp;
            this.value = value;
        }
    }

    private List<LinkedList<ArrayEntry>> array;
    private int timestamp = 0;

    SnapshottableArray(int size, T defaultVal) {
        array = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            LinkedList<ArrayEntry> entryList = new LinkedList<>();
            entryList.add(new ArrayEntry(timestamp, defaultVal));
            array.add(entryList);
        }
    }

    public void set(int i, T value) {
        ArrayEntry entry = array.get(i).get(0);
        if (entry.timestamp == timestamp) {
            entry.value = value;
        } else {
            array.get(i).add(0, new ArrayEntry(timestamp, value));
        }
    }

    public T get(int i) {
        return array.get(i).get(0).value;
    }

    int snapshot() {
        return timestamp++;
    }

    T valueAtTime(int timestamp, int index) {
        LinkedList<ArrayEntry> entryList = array.get(index);
        return getFirstValueBeforeTime(entryList, timestamp);
    }

    /**
     * Binary search for the latest value before the timestamp.
     */
    private T getFirstValueBeforeTime(LinkedList<ArrayEntry> entryList, int timestamp) {
        int left = 0;
        int right = entryList.size();
        while (left < right) {
            int mid = (left + right) / 2;
            if (entryList.get(mid).timestamp > timestamp) {
                left = mid + 1;
            } else {
                right -= 1;
            }
        }

        return entryList.get(left).value;
    }
}
