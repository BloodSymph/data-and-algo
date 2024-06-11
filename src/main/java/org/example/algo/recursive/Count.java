package org.example.algo.recursive;

public final class Count {

    public Count() {
    }

    public int count(int[] array) {
        if (array == null) {
            return -1;
        }
        return 1 + count(array);
    }
}
