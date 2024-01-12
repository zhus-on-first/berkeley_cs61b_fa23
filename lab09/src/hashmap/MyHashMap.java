package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import static java.util.Objects.hash;

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

    /* Class-level Instance Variables */
    private Collection<Node>[] buckets; // Don't change. Array of Collection<Node> objects
    private int numberOfItems; // Track number of items(elements) in the map
    private int numberOfBuckets; // Track number of buckets
    private double loadFactor;


    /** Constructors */
    public MyHashMap() {
        numberOfItems = 0;
        numberOfBuckets = 16;
        loadFactor = 0.75;

        // Create buckets now instead of having put() create it later
        buckets = (Collection<Node>[]) new Collection[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = createBucket(); // Access and modify each item of array by index
        }
    }

    public MyHashMap(int initialCapacity) {
        numberOfItems = 0;
        numberOfBuckets = initialCapacity;
        loadFactor = 0.75;

        // Create buckets now instead of having put() create it later
        buckets = (Collection<Node>[]) new Collection[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = createBucket(); // Access and modify each item of array by index
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        numberOfItems = 0;
        numberOfBuckets = initialCapacity;
        this.loadFactor = loadFactor;

        // Create buckets now instead of having put() create it later
        buckets = (Collection<Node>[]) new Collection[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = createBucket(); // Access and modify each item of array by index
        }

    }

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
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    /**
     * Helper function for these repeated steps in implementation:
     *         // Calculating the hash for the key: hash(key) -> index corresponding to a bucket to place it in
     *         // Finding the appropriate bucket based on this hash.
     *         // Iterating over the buckets to find a node with the given key.
     */
    private Collection<Node> getBucket(K key) {
        // Calculate hash for the given key
        int hash = key.hashCode();

        // Find bucket based on calculated hash
        int index = Math.floorMod(hash, numberOfBuckets);

        // Return bucket at calculated index
        return buckets[index];
    }

    /**
     * Helper function: calculates loadFactor to determine resizing
     * Returns ??
     */
    private void resize() {
        double currentLoadFactor = (double) numberOfItems / numberOfBuckets;
        if (currentLoadFactor >= loadFactor) {
            // 1. Double bucket array size
            int newNumberOfBuckets = numberOfBuckets * 2;

            // 2. Create and initialize new, larger bucket array
            Collection<Node>[] newBuckets = (Collection<Node>[]) new Collection[newNumberOfBuckets];
            for (int i = 0; i < newNumberOfBuckets; i++) {
                newBuckets[i] = createBucket(); // Access and modify each item of array by index
            }

            // 3. Rehash items and add to new bucket array
            for (Collection<Node> bucket: buckets) { // Iterate over each bucket in old bucket array
                for (Node node: bucket) { // for each item(node) in each old bucket
                    int hash = node.key.hashCode(); // Calculate hash for the given key (in the node)
                    int newIndex = Math.floorMod(hash, newNumberOfBuckets); // Find new bucket based on calculated hash
                    newBuckets[newIndex].add(node); // Add node to correct bucket in newBuckets
                }
            }

            // 4. Replace old buckets array with new one
            buckets = newBuckets;
            numberOfBuckets = newNumberOfBuckets;
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
        // hash(key) -> index corresponding to a bucket to place it in
        // get bucket by indexing into the buckets array
        // if bucket contains given key, update key's value
        // else, add a new Node<K, V> to bucket's collection

        Collection<Node> bucket = getBucket(key);
        for (Node node: bucket) {
            if (node.key.equals(key)) { // if node with given key exists. use equals() for value equality
                node.value = value; // update node's value
                return; // Exit loop
            }
        }
        // If no node with the key is found, create new node
        Node newNode = new Node(key, value);

        // Add new node to correct bucket
        bucket.add(newNode);

        // track size and when to resize
        numberOfItems++;
        resize();
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
        return numberOfItems;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        // Reset items
        numberOfItems = 0;

        // Reinitialize buckets
//        numberOfBuckets = 16; // Need this? Without will array size reset to what's set in constructors?
        buckets = (Collection<Node>[]) new Collection[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            buckets[i] = createBucket(); // Access and modify each item of array by index
        }
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
