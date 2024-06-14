package org.example.datastructures;

import java.net.StandardSocketOptions;
import java.util.Objects;
//Реализация очереди FIFO
public final class MyQueue<T> {

    private T[] elements;

    private int front;

    //Колличевство елементов очереди
    private int capacity;

    private int back;

    //Инициализация очереди
    public MyQueue(int capacity) {
        this.capacity = capacity;
        this.front = -1;
        this.back = -1;
        this.elements = null;
    }

    // Очередь пуста
    public boolean isEmpty() {
        return front == -1 && back == -1;
    }

    // Очередь переполнена
    public boolean isFull() {
        return back == capacity -1;
    }

    //Добавление єлемента в очередь
    public void enqueue(T element) {
        if (isFull()) {
            throw new  RuntimeException("Queue is full!");
        } else if (front == -1 && back == -1) {
            front = back = 0;
            elements[back] = element;
        } else {
            elements[back++] = element;
        }
    }

    //Извлечение єлементов из очереди
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (front == back) {
            front = back = -1;
        }
        return elements[front++];
    }

    //Отображение очереди
    public void display() {
        int i = 0;
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        } else {
            for (i = front; i < back; i++) {
                System.out.println(elements[i]);
            }
        }
    }

    // Взять один єлемент из очереди
    public void peek() {
        System.out.println("Front value: " + elements[front]);
    }

}
