package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.HashMap;

class HashTableTest {
    private HashMap<String, Integer> map;

    @BeforeEach
    public void setUp() {
        map = new HashMap<>();
    }

    @Test
    public void testPutAndGet() {
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);

        assertThat(map.get("one")).isEqualTo(1);
        assertThat(map.get("two")).isEqualTo(2);
        assertThat(map.get("three")).isEqualTo(3);
        assertThat(map.get("four")).isNull();
    }

    @Test
    public void testUpdateValue() {
        map.put("one", 1);
        map.put("one", 10);

        assertThat(map.get("one")).isEqualTo(10);
    }

    @Test
    public void testRemove() {
        map.put("one", 1);
        map.put("two", 2);

        map.remove("one");

        assertThat(map.get("one")).isNull();
        assertThat(map.get("two")).isEqualTo(2);

        map.remove("two");

        assertThat(map.get("two")).isNull();
    }

    @Test
    public void testCollision() {
        map.put("Aa", 1);
        map.put("BB", 2); // "Aa" и "BB" имеют одинаковый хэш-код в некоторых системах

        assertThat(map.get("Aa")).isEqualTo(1);
        assertThat(map.get("BB")).isEqualTo(2);

        map.remove("Aa");

        assertThat(map.get("Aa")).isNull();
        assertThat(map.get("BB")).isEqualTo(2);
    }

    @Test
    public void testRemoveNonExistingKey() {
        map.put("one", 1);
        map.put("two", 2);

        map.remove("two"); // "two" не существует

        assertThat(map.get("one")).isEqualTo(1);
        assertThat(map.get("two")).isNull();
    }
}
