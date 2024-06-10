package org.example.algo.sort;

public final class InsertionSort {

    public InsertionSort() {
    }

    public int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int sorted = i -1;
            while (sorted > -1 && array[sorted] > array[sorted + 1]) {
                int temp = array[sorted];
                array[sorted] = array[sorted + 1];
                array[sorted + 1] = temp;
                sorted--;
            }
        }
        return array;
    }
}
