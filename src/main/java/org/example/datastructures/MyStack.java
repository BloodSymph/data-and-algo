package org.example.datastructures;

import java.util.*;
//Работает по принципу LIFO
public final class MyStack<T> {

    private int stackTop;

    private T[] elements;


    //Инициализация стека
    public MyStack(int stackSize) {
        this.stackTop = -1;
        this.elements = (T[]) new Object[stackSize];
    }

    //Размер стека
    public synchronized int size() {
        return this.elements.length;
    }

    //Провека пустой ли стек
    public synchronized boolean isEmpty() {
        return this.stackTop == -1;
    }

    //Добавление елементов в конец стека
    public synchronized void push(T elemant) {
        this.elements[++stackTop] = elemant;
    }

    //Выборка элементов из вершины стека
    public synchronized T pop() {
        if (stackTop == -1) {
            throw new RuntimeException();
        }
        return elements[stackTop--];
    }

}
