package org.example.datastructures;

import java.util.*;

public final class MyStack<T> {

    private int stackTop;

    private T[] elements;



    public MyStack(int stackSize) {
        this.stackTop = -1;
        this.elements = (T[]) new Object[stackSize];
    }

    public synchronized int size() {
        return this.elements.length;
    }

    public synchronized boolean isEmpty() {
        return this.stackTop == -1;
    }

    public synchronized void push(T elemant) {
        this.elements[++stackTop] = elemant;
    }

    public synchronized T pop() {
        if (stackTop == -1) {
            throw new RuntimeException();
        }
        return elements[stackTop--];
    }

}
