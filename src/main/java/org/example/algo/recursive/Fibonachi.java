package org.example.algo.recursive;

public final class Fibonachi {

    public Fibonachi() {}

    public int fibonachi(int value) {
        if (value == 1 || value == 2) {
            return 1;
        }
        return fibonachi(value - 1) + fibonachi(value - 2);
    }
}
