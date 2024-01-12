package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Jia-Jia Zhu
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() { }

    public MyHashMap(int initialCapacity) { }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) { }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

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
        // hash(key) -> index corresponding to a bucket to place it in
        // get bucket by indexing into the buckets array
        // if bucket contains given key, update key's value
        // else, add a new Node<K, V> to bucket's collection

        // track size and when to resize

    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        // hash(key) -> index corresponding to a bucket to place it in
        // get bucket by indexing into the buckets array
        // if bucket contains given key, return key's value
        // else, return null
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        // hash(key) -> index corresponding to a bucket to place it in
        // get bucket by indexing into the buckets array
        // if bucket contains given key, return true
        // else, return false
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
     * Returns a Set view of the keys contained in this map. Not required for Lab 9.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not implementing");
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not implementing");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Not implementing");
    }

}
