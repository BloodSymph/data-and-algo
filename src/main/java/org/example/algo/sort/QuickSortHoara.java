package org.example.algo.sort;

import java.util.Arrays;

public final class QuickSortHoara {

    public QuickSortHoara() {
    }

    private int partOfQuickSort(int[] array, int left, int right) {
        int pivot = array[Math.round((left + right) / 2)];
        while (left <= right) {
            while (array[left] < pivot) left++;
            while (array[right] > pivot) right--;
            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    private void quickSortHoara(int[] array, int start, int end) {
        if (start >= end) return;
        int rightStart = partOfQuickSort(array, start, end);
        quickSortHoara(array, start, rightStart + 1);
        quickSortHoara(array, rightStart, end);
    }

    public void quickSort(int[] array) {
        quickSortHoara(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }


}
