package ru.geekbrains.algoritms.homework_6;

import java.util.NoSuchElementException;

public class MyTreeMap<K extends Comparable<K>, V> {
    private Node root;

    private class Node {
        K key;
        V value;
        Node left;
        Node right;
        int size;
        int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.size = 1;
            this.height = 0;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) { // Метод, позволяющий определить высоту узла
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    public boolean isEmpty() {
        return root == null;
    }

    private void checkKeyNotNull(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key == null");
        }
    }

    public boolean contains(K key) {
        return get(key) != null;
    }

    public V get(K key) {
        checkKeyNotNull(key);
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            return node.value;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    public void put(K key, V value) {
        checkKeyNotNull(key);
        if (value == null) {
            remove(key);
            return;
        }
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.value = value;
        } else if (cmp < 0) {
            node.left = put(node.left, key, value);
        } else {
            node.right = put(node.right, key, value);
        }
        node.size = size(node.left) + size(node.right) + 1;
        setHeight(node); // Вызов метода задания высоты узла
        return node;
    }

    public K minKey() {
        return min(root).key;
    }

    private Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    public void removeMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        root = removeMin(root);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        setHeight(node);
        return node;
    }

    public void remove(K key) {
        checkKeyNotNull(key);
        root = remove(root, key);
    }

    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node temp = node;
            node = min(node.right);
            node.right = removeMin(temp.right);
            node.left = temp.left;
        }
        node.size = size(node.left) + size(node.right) + 1;
        setHeight(node);
        return node;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node node) {
        if (node == null) {
            return "";
        }
        return toString(node.left) + " " +
                node.key + "=" + node.value + " " +
                toString(node.right);
    }

    private void setHeight(Node node) { // Метод задания высоты для узла
        if (node.left == null && node.right == null) {
            node.height = 0;
        } else {
            if (node.right == null || (height(node.left) > height(node.right))) {
                node.height = height(node.left) + 1;
            } else if (node.left == null || (height(node.left) < height(node.right))){
                node.height = height(node.right) + 1;
            } else { // Если высоты дочерних узлов равны
                node.height = height(node.left) + 1;
            }
        }
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) { // Метод проверки на сбалансированность узлов дерева
        return Math.abs(height(node.right) - height(node.left)) <= 1;
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) { // Метод обхода дерева
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.key + " " + height(node));
            inOrder(node.right);
        }
    }
}