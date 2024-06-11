package org.example.algo.recursive;

public final class Sum {

    public Sum() {
    }

    public int sum(int[] array) {
        if (array == null) {
            return -1;
        }
        return array[0] + sum(array);
    }
}
