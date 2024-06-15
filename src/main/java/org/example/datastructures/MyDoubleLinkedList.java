package org.example.datastructures;
//Реализация двосвязного списка
public final class MyDoubleLinkedList<T> {
    //Голова списка
    private Node<T> head;
    //Хвост списка
    private Node<T> tail;
    //Конструктор
    public MyDoubleLinkedList() {
        this.head = null;
        this.tail = null;
    }

    //Статический клас узла
    private static class Node<T> {
        //Значение
        private T value;
        //Ссылка на следующую ноду
        private Node<T> next;
        //Ссылка на придыдущую ноду
        private Node<T> prev;

        //Конструктор
        public Node(T value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    //Добавление нового значения
    public void add(T value) {
        //Создается новый узел и помещаеться передаваемое значение
        Node<T> newNode = new Node<>(value);

        //Если значение головного узла null
        if (head == null) {
            //Присваиваем ему значение нового узла
            head = newNode;
        } else {
            //Последующая нода будет являться ссылкой на хвост
            tail.next = newNode;
            //Придыдущая нода будет являться ссылкой на хвост
            newNode.prev = tail;
        }
        //Хвост - новая нода
        tail = newNode;
    }

    //Реверсия списка
    public void reverse() {
        //Присваиваем текущей ноде голову
        Node<T> currentNode = head;
        //Создаём мнимый узел
        Node<T> temp;

        //В цикле: если ссылка на текущую ноду не null
        while (currentNode != null) {
            //Мнимому узлу присваиваем ссылку на следующий узел
            temp = currentNode.next;
            //Присваиваем ссылке на последующий узел ссылку на предыдущий
            currentNode.next = currentNode.prev;
            //Если мнимый узел не null
            if (temp != null) {
                //Ссылке на предыдущий узел присваиваем временный мнимый узел
                currentNode.prev = temp;
                //Текущему узлу присваиваем мнимый временный узел
                currentNode = temp;
            } else {
                //Если ссылки на предыдущий узел нету присваиваем null
                currentNode.prev = null;
                //Если текущего узла не существует присваиваем null
                currentNode = null;
            }
        }
        //В нимый временный узел записываем голову
        temp = head;
        //В голову записываем хвост
        head = tail;
        //В хвост значение мнимого узла
        tail = temp;
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
    public void printList() {
        //Текущая нода равна голове
        Node<T> currentNode = head;

        //Запускаем цикл: пока ссылка на другую ноду не null
        while (currentNode != null) {
            //Выводим на экран значение текущей ноды
            System.out.println(currentNode.value);
            //Текущей ноде присваиваем значение последующей
            currentNode = currentNode.next;
        }
        System.out.println();
    }

}
