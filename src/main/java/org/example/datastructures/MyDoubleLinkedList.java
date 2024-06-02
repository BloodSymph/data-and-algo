package org.example.datastructures;

public final class MyDoubleLinkedList<T> {

    private Node<T> head;

    private Node<T> tail;

    public MyDoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    private static class Node<T> {
        private T value;

        private Node<T> next;

        private Node<T> prev;

        public Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
        }
        tail = newNode;
    }


    public void reverse() {
        Node<T> currentNode = head;
        Node<T> temp;

        while (currentNode != null) {
            temp = currentNode.next;
            currentNode.next = currentNode.prev;
            if (temp != null) {
                currentNode.prev = temp;
                currentNode = temp;
            } else {
                currentNode.prev = null;
                currentNode = null;
            }
        }
        temp = head;
        head = tail;
        tail = temp;
    }

    public void remove(T value) {
        Node<T> currentNode = head;
        Node<T> prevNode = null;

        while (currentNode.next != null) {
            if (currentNode.value == value) {
                if (currentNode == head){
                    head = currentNode.next;
                } else {
                    prevNode.next = currentNode.next;
                }
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
    }

    public void printList() {
        Node<T> currentNode = head;

        while (currentNode != null) {
            System.out.println(currentNode.value);
            currentNode = currentNode.next;
        }
        System.out.println();
    }

}
