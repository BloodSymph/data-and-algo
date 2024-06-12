package org.example.algo.tree;

public final class Tree {

    private Node root;

    public Tree() {
        this.root = null;
    }

    private Node addNode(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addNode(current.left, value);
        } else if (value > current.value) {
            current.right = addNode(current.right, value);
        } else {
            return current;
        }

        return current;
    }


    public void add(int value) {
        root = addNode(root, value);
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }




    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
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

    private Node recursiveDelete(Node node, int value) {
        if (node == null) return null;
        if (value < node.value) {
            node.left = recursiveDelete(node.left, value);
        } else if (value > node.value) {
            node.right = recursiveDelete(node.right, value);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node maxInLeft = getMax(node.left);
                node.value = maxInLeft.value;
                node.right = recursiveDelete(node.right, maxInLeft.value);
            }
        }
        return node;
    }

    //Обходы деревьев->

    //Симетрический
    private void sym(Node node) {
        if (node == null) return;
        sym(node.left);
        System.out.println(node.value);
        sym(node.right);
    }

    //Обратный
    private void reverse(Node node) {
        if (node == null) return;
        reverse(node.left);
        reverse(node.right);
        System.out.println(node.value);
    }

    //Прямой
    private void copy(Node node) {
        if (node == null) return;
        copy(node.left);
        copy(node.right);
        System.out.println(node.value);
    }




    public void delete(int value) {
        recursiveDelete(root, value);
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

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
