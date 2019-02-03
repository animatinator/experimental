package com.animatinator.practice.basic;

class BinarySearch {
    static int findElement(int[] array, int query) {
        if (array.length == 0) {
            return -1;
        }

        int l = 0;
        int r = array.length;

        while (l < r) {
            int mid = (l + r) / 2;
            if (array[mid] == query) {
                return mid;
            } else if (array[mid] < query) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }

        return -1;
    }
}
