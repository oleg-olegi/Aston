package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {

    @Test
    void testPutAndGet() {
        HashTable<String, Integer> ht = new HashTable<>(10);
        ht.put("one", 1);
        ht.put("two", 2);
        ht.put("three", 3);

        assertEquals(1, ht.get("one"));
        assertEquals(2, ht.get("two"));
        assertEquals(3, ht.get("three"));
    }

    @Test
    void testUpdateValue() {
        HashTable<String, Integer> ht = new HashTable<>(10);
        ht.put("one", 1);
        ht.put("one", 10);

        assertEquals(10, ht.get("one"));
    }

    @Test
    void testRemove() {
        HashTable<String, Integer> ht = new HashTable<>(10);
        ht.put("one", 1);
        ht.put("two", 2);

        assertTrue(ht.remove("one"));
        assertNull(ht.get("one"));
        assertEquals(2, ht.get("two"));
    }

    @Test
    void testRemoveNonExistentKey() {
        HashTable<String, Integer> ht = new HashTable<>(10);
        ht.put("one", 1);

        assertFalse(ht.remove("two"));
        assertEquals(1, ht.get("one"));
    }

    @Test
    void testGetNonExistentKey() {
        HashTable<String, Integer> ht = new HashTable<>(10);

        assertNull(ht.get("nonexistent"));
    }

    @Test
    void testCollisionHandling() {
        HashTable<String, Integer> ht = new HashTable<>(1);
        ht.put("one", 1);
        ht.put("two", 2);

        assertEquals(1, ht.get("one"));
        assertEquals(2, ht.get("two"));
    }
}
