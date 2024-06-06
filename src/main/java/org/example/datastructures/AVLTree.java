package org.example.datastructures;

public final class AVLTree {

    private Node root;

    public AVLTree() {
        this.root = null;
    }

    private int getHeight(Node node) {
        return node == null ? -1: node.height;
    }

    private void updateHeight(Node node) {
        node.height = Math.max(getHeight(node.left), getHeight(node.right));
    }

    private Node getMax(Node node) {
        if (node == null) return null;
        if (node.right == null) return node;
        return getMax(node.right);
    }

    private Node getMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return null;
        return getMin(node.left);
    }

    private int getBalance(Node node) {
        return (node == null) ? 0: getHeight(node.right) - getHeight(node.left);
    }

    private void swap(Node a, Node b) {
        int a_value = a.value;
        a.value = b.value;
        b.value = a_value;
    }

    private void rightRotate(Node node) {
        swap(node, node.left);
        Node buffer = node.right;
        node.right = node.left;
        node.left = node.right.left;
        node.right.left = node.right.right;
        node.right.right = buffer;
        updateHeight(node.right);
        updateHeight(node);
    }

    private void leftRotate(Node node) {
        swap(node, node.right);
        Node buffer = node.left;
        node.left = node.right;
        node.right = node.left.right;
        node.right.left = node.right.right;
        node.left.right = node.left.left;
        node.left.left = buffer;
        updateHeight(node.left);
        updateHeight(node);
    }

    private void balance(Node node) {
        int balance = getBalance(node);
        if (balance == -2) {
            if (getBalance(node.left) == 1) leftRotate(node.left);
            rightRotate(node);
        } else if (balance == 2) {
            if (getBalance(node.right) == -1) rightRotate(node.right);
            leftRotate(node);
        }
    }

    private Node insert(Node node, int value) {
        if (node == null ){
            node = new Node(value);
        }
        if (value < node.value) {
            if (node.left == null) node.left = new Node(value);
            else insert(node.left, value);
        }
        else if (value > node.value) {
            if (node.right == null) node.right = new Node(value);
            else insert(node.right, value);
        }
        updateHeight(node);
        balance(node);
        return node;
    }

    private Node delete(Node node, int value) {
        if (node == null) return null;
        else if (value < node.value) {
            node.left = delete(node.left, value);
        } else if (value > node.value) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right: node.left;
            } else {
                Node maxLnLeft = getMax(node.right);
                node.value = maxLnLeft.value;
                node.right = delete(node.right, maxLnLeft.value);
            }
        }
        if (node != null) {
            updateHeight(node);
            balance(node);
        }
        return node;
    }

    public void insert(int value) {
        root = insert(root, value);
    }

    public void delete(int value) {
        insert(root, value);
    }


    private static class Node {
        private int value;
        private int height;
        private Node left;
        private Node right;

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
