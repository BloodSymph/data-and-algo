package org.example.algo.search;

public final class LinearSearch {

    private int[] array;

    private int value;

    public LinearSearch() {
        this.array = null;
        this.value = -1;
    }

    public int linearSearch(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (value == array[i]) {
                return i;
            }
        }
        return -1;
    }
}
