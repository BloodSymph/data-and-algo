package org.example.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Objects;

public final class MyHashTable<K, V> {

    private ArrayList<HashNode<K, V>> bucket;

    private int numberBuckets;

    private int size;

    public MyHashTable() {
        this.bucket = new ArrayList<>();
        this.size = 0;
        this.numberBuckets = 10;

        for (int i = 0; i < numberBuckets; i++) {
            bucket.add(null);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    private final int hashCode(K key) {
        return Objects.hashCode(key);
    }

    private int getIndex(K key) {
        int hashCode = hashCode(key);
        int index = hashCode % numberBuckets;

        index = index < 0 ? index * -1: index;
        return index;
    }

    public void add(K key, V value) {
        int bucketIndex = getIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = bucket.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = bucket.get(bucketIndex);
        HashNode<K, V> newNode
                = new HashNode<K, V>(key, value, hashCode);
        newNode.next = head;
        bucket.set(bucketIndex, newNode);

        if ((1.0 * size) / numberBuckets >= 0.7) {
            ArrayList<HashNode<K, V> > temp = bucket;
            bucket = new ArrayList<>();
            numberBuckets = 2 * numberBuckets;
            size = 0;
            for (int i = 0; i < numberBuckets; i++)
                bucket.add(null);

            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }

    public V get(K key)
    {
        int bucketIndex = getIndex(key);
        int hashCode = hashCode(key);

        HashNode<K, V> head = bucket.get(bucketIndex);

        while (head != null) {
            if (head.key.equals(key) && head.hashCode == hashCode)
                return head.value;
            head = head.next;
        }
        return null;
    }

    public V remove(K key) {

        int bucketIndex = getIndex(key);
        int hashCode = hashCode(key);
        HashNode<K, V> head = bucket.get(bucketIndex);

        HashNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key) && hashCode == head.hashCode)
                break;
            prev = head;
            head = head.next;
        }

        if (head == null)
            return null;
        size--;

        if (prev != null)
            prev.next = head.next;
        else
            bucket.set(bucketIndex, head.next);

        return head.value;
    }

    private static class HashNode<K, V> {
        private K key;
        private V value;
        private final int hashCode;
        private HashNode<K, V> next;

        @Override
        public String toString() {
            return "HashNode{" +
                    "key=" + key +
                    ", value=" + value +
                    ", hashCode=" + hashCode +
                    ", next=" + next +
                    '}';
        }

        public HashNode(K key, V value, int hashCode) {
            this.key = key;
            this.value = value;
            this.hashCode = hashCode;
            this.next = null;


        }
    }

    @Override
    public String toString() {
        return "MyHashTable{" +
                "bucket=" + bucket +
                ", numberBuckets=" + numberBuckets +
                ", size=" + size +
                '}';
    }
}
