package org.example;

import java.util.LinkedList;

class HashTable<Key, Value> {
    private static class Node<Key, Value> {
        Key key;
        Value value;
        Node<Key, Value> next;

        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int size;
    private final LinkedList<Node<Key, Value>>[] table;

    public HashTable(int size) {
        this.size = size;
        this.table = new LinkedList[size];
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }

    private int hash(Key key) {
        return Math.abs(key.hashCode() % size);
    }

    public void put(Key key, Value value) {
        int index = hash(key);
        LinkedList<Node<Key, Value>> bucket = table[index];
        for (Node<Key, Value> node : bucket) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        bucket.add(new Node<>(key, value));
    }

    public Value get(Key key) {
        int index = hash(key);
        LinkedList<Node<Key, Value>> bucket = table[index];
        for (Node<Key, Value> node : bucket) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }
        return null;
    }

    public void remove(Key key) {
        int index = hash(key);
        LinkedList<Node<Key, Value>> bucket = table[index];
        for (Node<Key, Value> node : bucket) {
            if (key.equals(node.key)) {
                bucket.remove(node);
                return;
            }
        }
    }

    public static void main(String[] args) {
        HashTable<String, Integer> ht = new HashTable<>(10);
        ht.put("one", 1);
        ht.put("two", 2);
        ht.put("three", 3);

        System.out.println(ht.get("one"));
        System.out.println(ht.get("two"));
        System.out.println(ht.get("three"));

        ht.remove("two");
        System.out.println(ht.get("two"));
    }
}

