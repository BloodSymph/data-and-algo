package org.example.algo.cache;

public final class Fibonachi {

    public Fibonachi() {}

    private int fibonachi(int value, int cache[]) {
        if (value == 1 || value == 2) {
            return 1;
        }
        if (cache[value] == 0) {
            int a = fibonachi(value - 2, cache);
            int b = fibonachi(value - 1, cache);
            cache[value] = a + b;
        }
        return cache[value];
    }

    public int fibonachiWrap(int value) {
        int[] cache = new int[value + 1];
        return fibonachi(value, cache);
    }
}
