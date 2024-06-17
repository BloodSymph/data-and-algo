package org.example.datastructures;
//Реализация АВЛ дерева
public final class AVLTree {
    //Корневая нода
    private Node root;
    //Инициализация корневой ноды
    public AVLTree() {
        this.root = null;
    }

    //Получение высоты текущей ноды
    private int getHeight(Node node) {
        //Если нода null присваиваем высоте -1 или же возвращаем текущую высоту
        return node == null ? -1: node.height;
    }

    //Обновление высоты ноды
    private void updateHeight(Node node) {
        //Обновляем высоту путём получения максимального значения из высот левой и правой ветки
        node.height = Math.max(getHeight(node.left), getHeight(node.right));
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
        //Если левая ветвь равна null возвращаем null
        if (node.left == null) return null;
        //Рекурсивно возвращаем эту функцию передавая левую ветвь
        return getMin(node.left);
    }

    //Получаем баланс высот
    private int getBalance(Node node) {
        //Если нода нал возвращаем 0 высоту или разницу высот между правой и левой ветвями
        return (node == null) ? 0: getHeight(node.right) - getHeight(node.left);
    }

    //Свап значений нод
    private void swap(Node a, Node b) {
        //Мнимой ноде присваиваем значение ноды а
        int a_value = a.value;
        //Значение ноды а присваиваем значение б
        a.value = b.value;
        //Значению ноды б присваиваем значение мнимой ноды
        b.value = a_value;
    }

    //Правый поворот узла
    private void rightRotate(Node node) {
        //Вызывам свап и передаём туда текущей узел и левую ветвь
        swap(node, node.left);
        //Присваиваем буфферной ноде правую ветвь
        Node buffer = node.right;
        //Правой ветви присваиваем левую
        node.right = node.left;
        //Левой ветви присваиваем правую-левую ветвь
        node.left = node.right.left;
        //Парво-левой ветви присваиваем правые ветви
        node.right.left = node.right.right;
        //Правым ветвям присваиваем буферную ноду
        node.right.right = buffer;
        //Обновляем высоту для правой ветви
        updateHeight(node.right);
        //Обновляем высоту для текущей ноды
        updateHeight(node);
    }

    //Левый поворот узла
    private void leftRotate(Node node) {
        //Вызывам свап и передаём туда текущей узел и правую ветвь
        swap(node, node.right);
        //Присваиваем буфферной ноде левую ветвь
        Node buffer = node.left;
        //Левой ветви присваиваем правую
        node.left = node.right;
        //Правой ветви присваиваем ветвь с лева на право
        node.right = node.left.right;
        //Парво-левой ветви присваиваем правые ветви
        node.right.left = node.right.right;
        //Ветви с лево на право присваиваем ветви с лева на лево
        node.left.right = node.left.left;
        //Левым ветвям присваиваем буферную ноду
        node.left.left = buffer;
        //Обновляем высоту для левой ветви
        updateHeight(node.left);
        //Обновляем высоту для текущей ноды
        updateHeight(node);
    }

    //Основная функция балансировки
    private void balance(Node node) {
        //Получаем баланс высоты узла
        int balance = getBalance(node);
        //Если высота -2
        if (balance == -2) {
            //Если высота левой ветви 1 делаем левый поворот
            if (getBalance(node.left) == 1) leftRotate(node.left);
            //Иначе правый поворот
            rightRotate(node);
            //Если высота 2
        } else if (balance == 2) {
            //Если высота правой ветви -1 делаем правый поворот
            if (getBalance(node.right) == -1) rightRotate(node.right);
            //Иначе левый поворот
            leftRotate(node);
        }
    }

    //Рекурсивное создание узла на вход текущий узел и значение
    private Node insert(Node node, int value) {
        //Базовый случай если текущий узел null
        if (node == null ){
            //Возвращаем новый узел и заносим туда значение
            node = new Node(value);
        }
        //Если передаваемое значение меньше текущего
        if (value < node.value) {
            //Если левая ветка пуста заносим туда значение
            if (node.left == null) node.left = new Node(value);
            //Иначе рекурсивно добавляем левую ветвь и значение
            else insert(node.left, value);
        }
        //Если передаваемое значение больше текущего
        else if (value > node.value) {
            //Если правая ветка пуста заносим туда значение
            if (node.right == null) node.right = new Node(value);
                //Иначе рекурсивно добавляем правую ветвь и значение
            else insert(node.right, value);
        }
        //Обновляем высоту
        updateHeight(node);
        //Балансируем узел
        balance(node);
        //Возвращаем узел
        return node;
    }

    //Рекурсивная функция удаления узла передаем ноду и значение
    private Node delete(Node node, int value) {
        //Если нода равна null возвращаем null
        if (node == null) return null;
            //Если передаваемое значение меньше текущего
        else if (value < node.value) {
            //Рекурсивно удаляем левую ветвь и значение
            node.left = delete(node.left, value);
            //Если передаваемое значение выше текущего
        } else if (value > node.value) {
            //Рекурсивно удаляем правую ветвь и значение
            node.right = delete(node.right, value);
        } else {
            //Если левая ветвь равнва null или правая ветвь равна null
            if (node.left == null || node.right == null) {
                //То текущему узлу присваиваем либо ту либо ту ветку
                node = (node.left == null) ? node.right: node.left;
            } else {
                //Находим максимальное значение нод в правой ветви
                Node maxLnLeft = getMax(node.right);
                //Присваиваем текущей ноде максимальное значение ноды правой ветви
                node.value = maxLnLeft.value;
                //Для правой ветви рекурсивно производим удаление передавая
                // правую ветвь и максимальное значение ноды
                node.right = delete(node.right, maxLnLeft.value);
            }
        }
        //Если нода не пуста
        if (node != null) {
            //Обновляем высоту узла
            updateHeight(node);
            //Балансируем узел
            balance(node);
        }
        //Возвращаем узел
        return node;
    }

    //Публичный метод добавления нового узла
    public void insert(int value) {
        //Как новый узел добавляем корневой и передаём значение
        root = insert(root, value);
    }

    //Публичный метод удаления
    public void delete(int value) {
        //В рекурсивную функцию удаления передаем корневой узел и значение
        delete(root, value);
    }

    //Статический клас ноды
    private static class Node {
        //Значение
        private int value;
        //Высота
        private int height;
        //Левая нода
        private Node left;
        //Правая нода
        private Node right;

        //Инициализация значений
        public Node(int value) {
            this.value = value;
            this.height = 0;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", height=" + height +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "AVLTree{" +
                "root=" + root +
                '}';
    }
}
