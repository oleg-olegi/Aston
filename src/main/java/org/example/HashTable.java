package org.example;


public class HashTable<K, V> {
    private final static int DEFAULT_CAPACITY = 16;
    private final Node<K, V>[] table;

    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new Node[DEFAULT_CAPACITY];
    }

    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % DEFAULT_CAPACITY);
    }

    public void put(K key, V value) {
        int index = hash(key);
        Node<K, V> newNode = new Node<>(key, value);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node<K, V> current = table[index];
            Node<K, V> prev = null;
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = value;
                    return;
                }
                prev = current;
                current = current.next;
            }
            assert prev != null;
            prev.next = newNode;
        }
    }

    public V get(K key) {
        return null;
    }

    @Override
    public String toString() {
        return "HashTable{}";
    }

    public static void main(String[] args) {
        HashTable<String, Integer> ht = new HashTable<>();
        ht.put("one", 1);
        ht.put("two", 2);
        ht.put("three", 3);
        System.out.println(ht.toString());
    }
}
