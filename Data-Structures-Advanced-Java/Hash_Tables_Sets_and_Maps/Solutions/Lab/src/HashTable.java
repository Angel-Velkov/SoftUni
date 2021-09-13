import java.lang.reflect.Array;
import java.util.LinkedList;

public class HashTable<K, V> {
    private static class KeyValue<K, V> {
        K key;
        V value;

        KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private static final int INITIAL_CAPACITY = 16;
    private static final double MAX_LOAD_FACTOR = 0.7;

    private final LinkedList<KeyValue<K, V>>[] table;
    private int size;

    public HashTable() {
        this.table = createTable(INITIAL_CAPACITY);
        this.size = 0;
    }

    public void put(K key, V value) {
        int index = convertToIndex(key);

        if (this.table[index] == null) {
            this.table[index] = new LinkedList<>();
        }

        if (!set(this.table[index], key, value)) {
            this.table[index].add(new KeyValue<>(key, value));
        }

        this.table[index].add(new KeyValue<>(key, value));
        this.size++;

        if (this.size >= table.length * MAX_LOAD_FACTOR) {
            this.doubleTheCapacity();
        }
    }

    private boolean set(LinkedList<KeyValue<K, V>> chain, K key, V value) {
        for (KeyValue<K, V> element : chain) {
            if (element.key.equals(key)) {
                element.value = value;
                return true;
            }
        }

        return false;
    }

    private void doubleTheCapacity() {
        //TODO
    }

    public V get(K key) {
        int index = convertToIndex(key);
        LinkedList<KeyValue<K, V>> chain = this.table[index];

        if (chain != null) {
            for (KeyValue<K, V> element : chain) {
                if (element.key.equals(key)) {
                    return element.value;
                }
            }
        }

        return null;
    }

    private int convertToIndex(K key) {
        int hashCode = key.hashCode();

        return Math.abs(hashCode) % table.length;
    }

    @SuppressWarnings("unchecked")
    private LinkedList<KeyValue<K, V>>[] createTable(int size) {
        return (LinkedList<KeyValue<K, V>>[]) Array.newInstance(KeyValue.class, size);
    }
}
