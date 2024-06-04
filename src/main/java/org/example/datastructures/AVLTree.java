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
