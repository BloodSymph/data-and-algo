package org.example.datastructures;

import java.net.StandardSocketOptions;
import java.util.Objects;

public final class MyQueue<T> {

    private T[] elements;

    private int front;

    private int capacity;

    private int back;

    public MyQueue(int capacity) {
        this.capacity = capacity;
        this.front = -1;
        this.back = -1;
        this.elements = null;
    }

    public boolean isEmpty() {
        return front == -1 && back == -1;
    }

    public boolean isFull() {
        return back == capacity -1;
    }

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

    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (front == back) {
            front = back = -1;
        }
        return elements[front++];
    }

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

    public void peek() {
        System.out.println("Front value: " + elements[front]);
    }

}
