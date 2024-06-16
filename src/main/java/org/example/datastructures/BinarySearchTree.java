package org.example.datastructures;
//Реализация бинарного дерева
public final class BinarySearchTree {
    //Корень дерева
    private Node root;
    //Инициализация корня
    public BinarySearchTree() {
        this.root = null;
    }

    //Рекурсивное создание узла на вход текущий узел и значение
    private Node addNode(Node current, int value) {
        //Базовый случай если текущий узел null
        if (current == null) {
            //Возвращаем новый узел и заносим туда значение
            return new Node(value);
        }
        //Если передаваемое значение меньше текущего
        if (value < current.value) {
            //Рекурсивно его добавляем в левую ветку
            current.left = addNode(current.left, value);
          //Если передаваемое значение выше текущего
        } else if (value > current.value) {
            //Рекурсивно добавляем его в правую ветку
            current.right = addNode(current.right, value);
        } else {
            //Иначе возвращаем текущий узел
            return current;
        }
        //Возвращаем текущий узел
        return current;
    }

    //Публичный метод добавления нового узла
    public void add(int value) {
        //Как новый узел добавляем корневой и передаём значение
        root = addNode(root, value);
    }

    //Рекурсивный метод проверки есть ли узел передаём узел и значение
    private boolean containsNodeRecursive(Node current, int value) {
        //Если текущий узел null
        if (current == null) {
            //Возвращаем false
            return false;
        }
        //Если передаваемое значение равно значению текущего узла
        if (value == current.value) {
            //Возвращаем true
            return true;
        }
        //Если переданное значение меньше значения текущего узла то рекурсивно обходим левую ветку
        //В противном случае правую
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }



    //Публичный метод проверки есть ли узел
    public boolean containsNode(int value) {
        //Вызываем рекурсивный метод передаём в параметре корневой узел и значение
        return containsNodeRecursive(root, value);
    }

    // Рекурсивная функция проверки максимального значения ноды
    private Node getMax(Node node) {
        //Если нода равна null возвращаем null;
        if (node == null) return null;
        //Если правая ветвь равна null возвращаем ноду
        if (node.right == null) return node;
        //Рекурсивно возвращаем эту функцию передавая правую ветвь
        return getMax(node.right);
    }

    // Рекурсивная функция проверки минимального значения ноды
    private Node getMin(Node node) {
        //Если нода равна null возвращаем null;
        if (node == null) return null;
        //Если левая ветвь равна null возвращаем ноду
        if (node.left == null) return node;
        //Рекурсивно возвращаем эту функцию передавая левую ветвь
        return getMin(node.left);
    }

    //Рекурсивная функция удаления узла передаем ноду и значение
    private Node recursiveDelete(Node node, int value) {
        //Если нода равна null возвращаем null
        if (node == null) return null;
        //Если передаваемое значение меньше текущего
        if (value < node.value) {
            //Рекурсивно удаляем левую ветвь и значение
            node.left = recursiveDelete(node.left, value);
            //Если передаваемое значение выше текущего
        } else if (value > node.value) {
            //Рекурсивно удаляем правую ветвь и значение
            node.right = recursiveDelete(node.right, value);
        } else {
            //Если левая ветвь равнва null или правая ветвь равна null
            if (node.left == null || node.right == null) {
                //То текущему узлу присваиваем либо ту либо ту ветку
                node = (node.left == null) ? node.right : node.left;
            } else {
                //Находим максимальное значение нод в левой ветви
                Node maxInLeft = getMax(node.left);
                //Присваиваем текущей ноде максимальное значение ноды левой ветви
                node.value = maxInLeft.value;
                //Для правой ветви рекурсивно производим удаление передавая
                // правую ветвь и максимальное значение ноды
                node.right = recursiveDelete(node.right, maxInLeft.value);
            }
        }
        //Возвращаем узел
        return node;
    }

    //Публичный метод удаления
    public void delete(int value) {
        //В рекурсивную функцию удаления передаем корневой узел и значение
        recursiveDelete(root, value);
    }

    //Статический клас представляющий узел дерева
    private static class Node {
        //Значение
        private int value;
        //Левая ветвь
        private Node left;
        //Правая ветвь
        private Node right;

        //Инициализация узла
        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "BinarySearchTree{" +
                "root=" + root +
                '}';
    }
}
