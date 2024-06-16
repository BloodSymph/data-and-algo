package org.example.datastructures;

import java.util.ArrayList;
import java.util.Objects;

//Хеш таблица хранит пару ключ-значение
public final class MyHashTable<K, V> {
    //Бакет или же корзина которая хранит клас HashNode
    private ArrayList<HashNode<K, V>> bucket;
    //Изначальный размер бакета
    private int numberBuckets;
    //Размер хеш таблицы
    private int size;
    //Инициализация хещ таблицы
    public MyHashTable() {
        //Инициализируется пустой массив бакета
        this.bucket = new ArrayList<>();
        //Изначальный размер таблицы 0
        this.size = 0;
        //Изначальная размерность бакета 10
        this.numberBuckets = 10;

        //Перебором в цикле размера бакета добавляем в него значения типа null
        for (int i = 0; i < numberBuckets; i++) {
            bucket.add(null);
        }
    }
    //Получаем размер хеш таблицы
    public int size() {
        return size;
    }

    //Таблица пуста если размер равен 0
    public boolean isEmpty() {
        return size() == 0;
    }

    //Получаем хеш код с обьекта путём вызова функции хеш код на вход которой передаём ключ
    private final int hashCode(K key) {
        return Objects.hashCode(key);
    }

    //Получаем индекс элемента бакета
    private int getIndex(K key) {
        //ПЗаписываем хеш код в локальную переменную
        int hashCode = hashCode(key);
        //Вычисляем индекс баккета путём получения остатка от деления хеш кода на размер бакета
        int index = hashCode % numberBuckets;

        //Проверяем индекс
        index = index < 0 ? index * -1: index;
        //Возвращаем индекс баккета
        return index;
    }

    //Запись єлемента в хеш таблицу
    public void add(K key, V value) {
        //Получаем индекс баккета путём вызова придыдущей функции передавая туда ключ
        int bucketIndex = getIndex(key);
        //Получаем хеш код вызовом придыдущей функции передавая туда ключ
        int hashCode = hashCode(key);
        //Создаем первый узел получая при этом содержимое бакета по индексу
        HashNode<K, V> head = bucket.get(bucketIndex);

        //В цикле если головной узел не равен null
        while (head != null) {
            //Проверяем ключи узла с переданным ключем + хеш код головного узла
            if (head.key.equals(key) && head.hashCode == hashCode) {
                //Записываем значение в начальный узел
                head.value = value;
                //Глухой ретерн
                return;
            }
            //Создаём ссылку на следующий узел
            head = head.next;
        }
        //Инкрементируем постфиксно размер хеш таблици
        size++;
        //Получаем индекс бакета следующего узла
        head = bucket.get(bucketIndex);
        //Создаём новый узел передавая туда ключ, значение и хеш код
        HashNode<K, V> newNode = new HashNode<K, V>(key, value, hashCode);
        //Создаем ссылку на головной узел
        newNode.next = head;
        //Помещаем в бакет индекс бакета и ноду
        bucket.set(bucketIndex, newNode);

        //Проверка во избежания коллизий
        //Если размер делёный на размер бакетов равен или больше 0.7
        if ((1.0 * size) / numberBuckets >= 0.7) {
            //Создаём мнимую ноду в которуб заносим бакет
            ArrayList<HashNode<K, V> > temp = bucket;
            //Создвем новый бакет
            bucket = new ArrayList<>();
            //Увеличиваем размер исходного бакета в 2 раза
            numberBuckets = 2 * numberBuckets;
            //Размер устанавливаем на 0
            size = 0;
            //В цикле перебераем по размеру бакета и заносим в него значения null
            for (int i = 0; i < numberBuckets; i++)
                bucket.add(null);
            //Обходим циклом мнимую ноду
            for (HashNode<K, V> headNode : temp) {
                //В цикле если она не null
                while (headNode != null) {
                    //Заносим значения ключа и value
                    add(headNode.key, headNode.value);
                    //Создаем ссылку на следующую ноду
                    headNode = headNode.next;
                }
            }
        }
    }

    //Получение значения по ключу
    public V get(K key)
    {
        //Получаем индекс баккета путём вызова придыдущей функции передавая туда ключ
        int bucketIndex = getIndex(key);
        //Получаем хеш код вызовом придыдущей функции передавая туда ключ
        int hashCode = hashCode(key);
        //Создаем первый узел получая при этом содержимое бакета по индексу
        HashNode<K, V> head = bucket.get(bucketIndex);

        //В цикле если головной узел не равен null
        while (head != null) {
            //Проверяем ключи узла с переданным ключем + хеш код головного узла
            if (head.key.equals(key) && head.hashCode == hashCode)
                //Возвращаем значение узла
                return head.value;
            //Создаём ссылку на следующий узел
            head = head.next;
        }
        //Если ничего не нашли возвращаем null
        return null;
    }
    //Удаление значения по ключу
    public V remove(K key) {
        //Получаем индекс баккета путём вызова придыдущей функции передавая туда ключ
        int bucketIndex = getIndex(key);
        //Получаем хеш код вызовом придыдущей функции передавая туда ключ
        int hashCode = hashCode(key);
        //Создаем первый узел получая при этом содержимое бакета по индексу
        HashNode<K, V> head = bucket.get(bucketIndex);

        //Создаём пустую придыдущую ноду
        HashNode<K, V> prev = null;
        //В цикле если головной узел не равен null
        while (head != null) {
            //Проверяем ключи узла с переданным ключем + хеш код головного узла
            if (head.key.equals(key) && hashCode == head.hashCode)
                //Прерываем
                break;
            //Придыдущему узлу присваиваем значение головного
            prev = head;
            //Создаём ссылку на следующий узел
            head = head.next;
        }
        //Если головной узел null
        if (head == null)
            //Возвращаем null
            return null;
        //Декрементируем в постпрефексной форме размер хеш таблици
        size--;
        //Если придыдущая нода не равна null
        if (prev != null)
            //Указатель предідущей ноді переписываем на указатель головы
            prev.next = head.next;
        else
            //Помещаем в бакет индекс и ссылку на последующие ноды с головы
            bucket.set(bucketIndex, head.next);
        //Возвращаем значение головной ноды
        return head.value;
    }

    //Создаём статический класс хеш узла
    private static class HashNode<K, V> {
        //Ключ
        private K key;
        //Значение
        private V value;
        //Хеш код
        private final int hashCode;
        //Ссылка на последующие узлы
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

        //Инициализация значений
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
