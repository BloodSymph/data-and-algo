package org.example.algo.search;

public class BinarySearch {
    private int[] array;
    private int value;

    public BinarySearch() {
        this.value = -1;
        this.array = null;
    }

    public int binarySearch(int[] array, int value) {
        int middle;
        int guess;
        int height = array.length - 1;
        int low = 0;
        while (low <= height) {
            middle = Math.round((low + height) / 2);
            guess = array[middle];
            if (guess == value) return middle;
            if (guess > value) height = middle - 1;
            else low = middle + 1;
        }
        return -1;
    }
}
