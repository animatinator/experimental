package com.animatinator.practice.rotatedsearch;

class FindInRotatedArray {
    static int findInArray(int[] array, int element) {
        int rotationPoint = findRotationPoint(array);
        if (element >= array[0] && rotationPoint != 0) {
            return findInPartialArray(array, element, 0, rotationPoint);
        } else {
            return findInPartialArray(array, element, rotationPoint, array.length);
        }
    }

    private static int findRotationPoint(int[] array) {
        int left = 0;
        int right = array.length - 1;

        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] > array[right]) {
                left = mid + 1;
            } else if (array[mid] < array[right]) {
                right = mid;
            } else {
                left++;
            }
        }

        return left;
    }

    private static int findInPartialArray(int[] array, int element, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;
            if (array[mid] == element) {
                return mid;
            } else if (element < array[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        if (array[left] == element) {
            return left;
        } else {
            return -1;
        }
    }
}
