package org.example.datastructures;

public final class RedBlackTree {

    private static final boolean RED = false;
    private static final boolean BLACK = true;

    private Node root;


    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.left == oldChild) {
            parent.left = newChild;
        } else if (parent.right == oldChild) {
            parent.right = newChild;
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.parent = parent;
        }
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftChild = node.left;

        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }

        leftChild.right = node;
        node.parent = leftChild;

        replaceParentsChild(parent, node, leftChild);
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightChild = node.right;

        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }

        rightChild.left = node;
        node.parent = rightChild;

        replaceParentsChild(parent, node, rightChild);
    }

    public Node searchNode(int data) {
        Node node = root;
        while (node != null) {
            if (data == node.data) {
                return node;
            } else if (data < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        return null;
    }

    public void insertNode(int data) {
        Node node = root;
        Node parent = null;

        while (node != null) {
            parent = node;
            if (data < node.data) {
                node = node.left;
            } else if (data > node.data) {
                node = node.right;
            } else {
                throw new IllegalArgumentException("BST already contains a node with key " + data);
            }
        }
        Node newNode = new Node(data);
        newNode.color = RED;
        if (parent == null) {
            root = newNode;
        } else if (data < parent.data) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        newNode.parent = parent;

        balanceInsertion(newNode);
    }


    private Node getUncle(Node parent) {
        Node grandparent = parent.parent;
        if (grandparent.left == parent) {
            return grandparent.right;
        } else if (grandparent.right == parent) {
            return grandparent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }

    private Node findMinimum(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void deleteNode(int data) {
        Node node = root;
        while (node != null && node.data != data) {
            if (data < node.data) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (node == null) {
            return;
        }

        Node movedUpNode;
        boolean deletedNodeColor;
        if (node.left == null || node.right == null) {
            movedUpNode = deleteNodeWithZeroOrOneChild(node);
            deletedNodeColor = node.color;
        }

        else {
            Node inOrderSuccessor = findMinimum(node.right);
            node.data = inOrderSuccessor.data;
            movedUpNode = deleteNodeWithZeroOrOneChild(inOrderSuccessor);
            deletedNodeColor = inOrderSuccessor.color;
        }
        if (deletedNodeColor == BLACK) {
            fixRedBlackPropertiesAfterDelete(movedUpNode);
            if (movedUpNode.getClass() == NilNode.class) {
                replaceParentsChild(movedUpNode.parent, movedUpNode, null);
            }
        }
    }

    private void fixRedBlackPropertiesAfterDelete(Node node) {
        if (node == root) {
            return;
        }
        Node sibling = getSibling(node);
        if (sibling.color == RED) {
            handleRedSibling(node, sibling);
            sibling = getSibling(node);
        }

        if (isBlack(sibling.left) && isBlack(sibling.right)) {
            sibling.color = RED;
            if (node.parent.color == RED) {
                node.parent.color = BLACK;
            }
            else {
                fixRedBlackPropertiesAfterDelete(node.parent);
            }
        }
        else {
            handleBlackSiblingWithAtLeastOneRedChild(node, sibling);
        }
    }

    private Node getSibling(Node node) {
        Node parent = node.parent;
        if (node == parent.left) {
            return parent.right;
        } else if (node == parent.right) {
            return parent.left;
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }
    private boolean isBlack(Node node) {
        return node == null || node.color == BLACK;
    }

    private void handleRedSibling(Node node, Node sibling) {
        sibling.color = BLACK;
        node.parent.color = RED;

        if (node == node.parent.left) {
            rotateLeft(node.parent);
        } else {
            rotateRight(node.parent);
        }
    }

    private void handleBlackSiblingWithAtLeastOneRedChild(Node node, Node sibling) {
        boolean nodeIsLeftChild = node == node.parent.left;
        if (nodeIsLeftChild && isBlack(sibling.right)) {
            sibling.left.color = BLACK;
            sibling.color = RED;
            rotateRight(sibling);
            sibling = node.parent.right;
        } else if (!nodeIsLeftChild && isBlack(sibling.left)) {
            sibling.right.color = BLACK;
            sibling.color = RED;
            rotateLeft(sibling);
            sibling = node.parent.left;
        }
        sibling.color = node.parent.color;
        node.parent.color = BLACK;
        if (nodeIsLeftChild) {
            sibling.right.color = BLACK;
            rotateLeft(node.parent);
        } else {
            sibling.left.color = BLACK;
            rotateRight(node.parent);
        }
    }

    private Node deleteNodeWithZeroOrOneChild(Node node) {
        if (node.left != null) {
            replaceParentsChild(node.parent, node, node.left);
            return node.left;
        }
        else if (node.right != null) {
            replaceParentsChild(node.parent, node, node.right);
            return node.right; // moved-up node
        }
        else {
            Node newChild = node.color == BLACK ? new NilNode() : null;
            replaceParentsChild(node.parent, node, newChild);
            return newChild;
        }
    }


    private void balanceInsertion(Node node) {
        Node parent = node.parent;
        if (parent == null) {
            return;
        }
        if (parent.color == BLACK) {
            return;
        }
        Node grandparent = parent.parent;
        if (grandparent == null) {
            parent.color = BLACK;
            return;
        }
        Node uncle = getUncle(parent);

        if (uncle != null && uncle.color == RED) {
            parent.color = BLACK;
            grandparent.color = RED;
            uncle.color = BLACK;
            balanceInsertion(grandparent);
        }

        else if (parent == grandparent.left) {
            if (node == parent.right) {
                rotateLeft(parent);
                parent = node;
            }

            rotateRight(grandparent);

            parent.color = BLACK;
            grandparent.color = RED;
        }

        else {
            if (node == parent.left) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);
            parent.color = BLACK;
            grandparent.color = RED;
        }
    }


    private static class Node {
        private int data;
        private Node left;
        private Node right;
        private Node parent;

        private boolean color;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    ", left=" + left +
                    ", right=" + right +
                    ", color=" + color +
                    '}';
        }
    }

    private static class NilNode extends Node {
        public NilNode() {
            super(0);
        }
    }

    @Override
    public String toString() {
        return "RedBlackTree{" +
                "root=" + root +
                '}';
    }
}