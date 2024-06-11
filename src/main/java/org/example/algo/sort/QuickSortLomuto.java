package org.example.algo.sort;

import java.util.Arrays;

public final class QuickSortLomuto {

    public QuickSortLomuto() {
    }

    private int partOfSortLomuto(int[] array, int start, int end){
        int left = start;
        for (int current = start; current < end; current++) {
            if (array[current] <= array[end]) {
                int temp = array[left];
                array[left] = array[current];
                array[current] = temp;
                left++;
            }
        }
        int temp = array[left];
        array[left] = array[end];
        array[end] = temp;
        return left;
    }


    private void quickSortLomuto(int[] array, int start, int end) {
        if (start >= end) return;
        int rightStart = partOfSortLomuto(array, start, end);
        quickSortLomuto(array, start, rightStart - 1);
        quickSortLomuto(array, rightStart + 1, end);
    }

    public void quickSort(int[] array) {
        quickSortLomuto(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }


}
