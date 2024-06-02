package org.example.datastructures;

public final class MyLinkedList<T> {

    private Node<T> head;

    public MyLinkedList() {
        this.head = null;
    }

    private static class Node<T> {
        private Node<T> next;
        private T value;

        public Node(T value) {
            this.next = null;
            this.value = value;
        }
    }

    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        Node<T> currentNode = head;

        if (head == null) {
            head = newNode;
        } else {
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode;
        }
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

    public void print() {
        Node<T> currentNode = head;

        if (head != null) {
            System.out.println(head.value);
        }

        while (currentNode.next != null) {
            currentNode = currentNode.next;
            System.out.println(currentNode.value);
        }
    }
}