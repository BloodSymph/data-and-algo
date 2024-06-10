package org.example.algo.recursive;

public final class Factorial {


    public Factorial() {
    }

    public int factorial(int value) {
        if (value == 1) {
            return 1;
        }
        return value * factorial(value - 1);
    }

}
