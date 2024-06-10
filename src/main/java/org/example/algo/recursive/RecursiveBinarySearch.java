package org.example.algo.recursive;

public final class RecursiveBinarySearch {

    public RecursiveBinarySearch() {
    }

    public int recursiveBinarySearch(int[] array, int value, int start, int end) {
        int middle = Math.round((start + end) / 2);
        if (value == array[middle]) return middle;
        if (value < array[middle]) return recursiveBinarySearch(array, value, start, middle-1);
        else return recursiveBinarySearch(array, value, middle + 1, end);
    }
}
