package org.example;


/**
 * Кастомная хеш-таблица с использованием цепочек для разрешения коллизий.
 *
 * @param <K> тип ключей, поддерживаемых хеш-таблицей
 * @param <V> тип значений, сопоставленных с ключами
 * @author oleg shinkevich
 */
public class HashTable<K, V> {
    private final static int DEFAULT_CAPACITY = 16;
    private final Node<K, V>[] table;

    /**
     * Конструктор объекта таблицы
     * Создает новую, пустую хеш-таблицу с начальной емкостью по умолчанию (16).
     */
    @SuppressWarnings("unchecked")
    public HashTable() {
        table = new Node[DEFAULT_CAPACITY];
    }

    /**
     * Класс узла, представляющий каждую запись в хеш-таблице.
     *
     * @param <K> тип ключей
     * @param <V> тип значений
     */
    private static class Node<K, V> {
        final K key;
        V value;
        Node<K, V> next;

        /**
         * Конструктор объекта узла
         * Создает новый узел с указанным ключом и значением.
         *
         * @param key   ключ
         * @param value значение
         */
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Вычисляет хеш-значение для данного ключа.
     *
     * @param key ключ
     * @return хеш-значение
     */
    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % DEFAULT_CAPACITY);
    }

    /**
     * Ассоциирует указанное значение с указанным ключом в этой хеш-таблице.
     * Если хеш-таблица уже содержит отображение для ключа, старое значение заменяется.
     *
     * @param key   ключ, с которым должно быть связано указанное значение
     * @param value значение, которое должно быть связано с указанным ключом
     */
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

    /**
     * Возвращает значение, с которым связан указанный ключ, или {@code null}, если эта
     * хеш-таблица не содержит отображения для ключа.
     *
     * @param key ключ, значение которого должно быть возвращено
     * @return значение, с которым связан указанный ключ, или {@code null}, если эта
     * хеш-таблица не содержит отображения для ключа
     */
    public V get(K key) {
        int index = hash(key);
        Node<K, V> current = table[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        return null;
    }

    /**
     * Удаляет отображение для указанного ключа из этой хеш-таблицы, если оно присутствует.
     *
     * @param key ключ, отображение которого должно быть удалено из хеш-таблицы
     */
    public void remove(K key) {
        int index = hash(key);
        Node<K, V> current = table[index];
        Node<K, V> prev = null;
        while (current != null) {
            if (current.key.equals(key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    /**
     * Возвращает строковое представление этой хеш-таблицы. Строковое представление
     * состоит из списка отображений хеш-таблицы в порядке, в котором они возвращаются
     * итератором представления набора ее записей.
     *
     * @return строковое представление этой хеш-таблицы
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");

        for (Node<K, V> node : table) {
            while (node != null) {
                sb.append(node.key).append("=").append(node.value).append(", ");
                node = node.next;
            }
        }

        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);  // Удаляем последнюю запятую и пробел
        }

        sb.append("}");
        return sb.toString();
    }
}
