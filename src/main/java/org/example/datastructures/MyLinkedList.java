package org.example.datastructures;
//Реализация односвязного списка
public final class MyLinkedList<T> {
    //Головной конец списка
    private Node<T> head;

    //Конструктор инициализации
    public MyLinkedList() {
        this.head = null;
    }

    //Статіческий клас которий представляет узел списка
    private static class Node<T> {
        //Ссылка на последующий узел
        private Node<T> next;
        //Значение передаваемое в узел
        private T value;

        //Конструктор инициализации
        public Node(T value) {
            this.next = null;
            this.value = value;
        }
    }

    //Вставка значения
    public void add(T value) {
        //Создается новый узел и помещаеться передаваемое значение
        Node<T> newNode = new Node<>(value);
        //Текущий узел является головой списка
        Node<T> currentNode = head;

        //Если значение головного узла null
        if (head == null) {
            //Присваиваем ему значение нового узла
            head = newNode;
        } else {
            //Запускаем цикл: если текущий узел с сылкой на последующие не содержит null
            while (currentNode.next != null) {
                //Присваиваем текущему узлу ссылку на последующую ноду
                currentNode = currentNode.next;
            }
            //Ссылаемся на новый узел
            currentNode.next = newNode;
        }
    }

    //Удаление значения из списка
    public void remove(T value) {
        //Присваиваем текущей ноде голову
        Node<T> currentNode = head;
        //Придыдущей ноде значение null
        Node<T> prevNode = null;

        //В цикле: если ссылка на последующие ноды не null
        while (currentNode.next != null) {
            //Проверяем текущое значение с передаваемым
            if (currentNode.value == value) {
                //если текущая нода является головой
                if (currentNode == head){
                    //Присваиваем голове ссылку на последующую ноду
                    head = currentNode.next;
                } else {
                    // Или присваиваем ссылке на придыдущее ноды на ссылку последующих
                    prevNode.next = currentNode.next;
                }
            }
            //присваиваем придыдущей ноде текущую ноду
            prevNode = currentNode;
            //Присваиваем текущей ноде ссылку на следующие ноды
            currentNode = currentNode.next;
        }
    }

    //Распечатать лист
    public void print() {
        //Текущая нода равна голове
        Node<T> currentNode = head;
        //Если текущая нода не null
        if (head != null) {
            //Выводим значение головы
            System.out.println(head.value);
        }
        //Запускаем цикл: пока ссылка на другую ноду не null
        while (currentNode.next != null) {
            //Текущей ноде присваиваем значение последующей
            currentNode = currentNode.next;
            //Выводим на экран значение текущей ноды
            System.out.println(currentNode.value);
        }
    }
}