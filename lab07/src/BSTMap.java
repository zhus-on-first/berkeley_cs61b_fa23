import java.util.Iterator;
import java.util.Set;

public class BSTMap< K extends Comparable<K>, V > implements Map61B<K, V> {

    /** Keys and values are stored in a Map of Entry objects.
     *  This variable stores the first pair in this Map. */
    private Entry root;

    /** Represents one node in the Map that stores the key-value pairs
     *  in the dictionary. */
    private class Entry {
        K key;
        V value;
        Entry left;
        Entry right;

        // Constructor
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if (root == null) {
            root = new Entry(key, value);
        } else {
            root = putHelper(root, key, value); // Outsource to a helper
        }
    }

    /**
    * Helper function to traverse tree
    */
    private Entry putHelper(Entry node, K key, V value) {
        // If current node is null, return new Entry object with key and value?
        if (node == null) {
            return new Entry (key, value);
        }

        // Initialize a compare of the new key with the current node's key
        int compare = key.compareTo(node.key);

        // If key is less, go left
        if (compare < 0) {
            node.left = putHelper(node.left, key, value);
        }

        // If key is greater, go right
        else if (compare > 0) {
            node.right = putHelper(node.right, key, value);
        }

        // If key is equal (found key you want), update value of current node
        else {
            node.value = value;
        }
        return node;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        // Start from the root
        Entry node = root;

        // Loop through the tree
        while (node != null) {
            // Compare the search key with the current node's key
            int compare = key.compareTo(node.key);

            if (compare < 0) { // If the search key is less than the current node's key
                node = node.left;
            } else if (compare > 0) { // If the search key is greater than the current node's key
                node = node.right;
            } else { // If you find the node with the key
                return node.value;
            }
        }
        // Return null if the key is not found
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {

    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
//        return null;
        throw new UnsupportedOperationException("Not implementing");
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
//        return null;
        throw new UnsupportedOperationException("Not implementing");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
