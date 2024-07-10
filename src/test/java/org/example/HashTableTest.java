package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashTableTest {
    private HashTable<String, Integer> hashTable;

    @BeforeEach
    public void setUp() {
        hashTable = new HashTable<>();
    }

    @Test
    public void testPutAndGet() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);

        assertEquals(1, hashTable.get("one"));
        assertEquals(2, hashTable.get("two"));
        assertEquals(3, hashTable.get("three"));
        assertNull(hashTable.get("four"));
    }

    @Test
    public void testUpdateValue() {
        hashTable.put("one", 1);
        hashTable.put("one", 11);

        assertEquals(11, hashTable.get("one"));
    }

    @Test
    public void testRemove() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);

        hashTable.remove("one");
        assertNull(hashTable.get("one"));
        assertEquals(2, hashTable.get("two"));

        hashTable.remove("two");
        assertNull(hashTable.get("two"));
    }

    @Test
    public void testResize() {
        for (int i = 1; i <= 12; i++) {
            hashTable.put("key" + i, i);
        }

        assertEquals(1, hashTable.get("key1"));
        assertEquals(12, hashTable.get("key12"));

        // Проверка, что все элементы на месте после изменения размера
        for (int i = 1; i <= 12; i++) {
            assertEquals(i, hashTable.get("key" + i));
        }
    }

    @Test
    public void testToString() {
        hashTable.put("one", 1);
        hashTable.put("two", 2);
        hashTable.put("three", 3);

        String expected = "{one=1, two=2, three=3}";
        assertEquals(expected, hashTable.toString());
    }
}
