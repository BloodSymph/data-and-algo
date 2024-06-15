package org.example.datastructures;

import java.net.StandardSocketOptions;
import java.util.Objects;
//Реализация очереди FIFO
public final class MyQueue<T> {
    //Елементы
    private T[] elements;
    //Начало очереди
    private int front;

    //Колличевство елементов очереди
    private int capacity;
    //Конец очереди
    private int back;

    //Инициализация очереди
    public MyQueue(int capacity) {
        this.capacity = capacity;
        this.front = -1;
        this.back = -1;
        this.elements = null;
    }

    // Очередь пусата
    //True если начало и конец равны -1
    public boolean isEmpty() {
        return front == -1 && back == -1;
    }

    // Очередь переполнена
    //True если конец очереди равен колл єлементов - 1;
    public boolean isFull() {
        return back == capacity - 1;
    }

    //Добавление єлемента в очередь
    public void enqueue(T element) {
        //Если очередь полна выбрасываем исключение
        if (isFull()) {
            throw new  RuntimeException("Queue is full!");
            //Если начало и конец равны -1
        } else if (front == -1 && back == -1) {
            //Перезаписываем начало и конец к значению 0
            front = back = 0;
            //Добавляем в конец очереди первый элемент
            elements[back] = element;
        } else {
            //Добавляем элемент инкрементируя в постпрефиксной форме конец очереди
            elements[back++] = element;
        }
    }

    //Извлечение єлементов из очереди
    public T dequeue() {
        //Если очередь пуста выбрасываем исключение
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
            //Если конец и начало очереди равны
        } else if (front == back) {
            //Присваиваем им -1
            front = back = -1;
        }
        //Возвращаем элемент инкрементируя начало в постфиксной форме
        return elements[front++];
    }

    //Отображение очереди
    public void display() {
        //Создаёмм числовую переменную из начением 0
        int i = 0;
        //Если очередь пуста выбрасываем исключение
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty!");
        } else {
            //Перебераем в цикле где i = front, back
            for (i = front; i < back; i++) {
                //Выводим по индексу все элементы очереди
                System.out.println(elements[i]);
            }
        }
    }

    // Взять один єлемент из очереди
    public void peek() {
        //Берём один элемент из начала очереди
        System.out.println("Front value: " + elements[front]);
    }

}
