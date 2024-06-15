package org.example.datastructures;

import java.util.*;
//Работает по принципу LIFO
public final class MyStack<T> {
    //Вершина стека
    private int stackTop;
    //Елементы
    private T[] elements;


    //Инициализация стека
    public MyStack(int stackSize) {
        this.stackTop = -1;
        this.elements = (T[]) new Object[stackSize];
    }

    //Размер стека
    //Просто возвращаем длину єлемента
    public synchronized int size() {
        return this.elements.length;
    }

    //Провека пустой ли стек
    //Возвращаем true если вершина стека -1
    public synchronized boolean isEmpty() {
        return this.stackTop == -1;
    }

    //Добавление елементов в стек
    //Добавляем єлемент по префиксному инкрименту индекса
    public synchronized void push(T elemant) {
        this.elements[++stackTop] = elemant;
    }

    //Выборка элементов из вершины стека
    //Удаляем элемент из стека по постфиксному декрименту индекса
    public synchronized T pop() {
        if (stackTop == -1) {
            throw new RuntimeException();
        }
        return elements[stackTop--];
    }

}
