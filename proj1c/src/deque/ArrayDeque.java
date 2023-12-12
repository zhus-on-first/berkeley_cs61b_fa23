package deque;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

// index:   0 1 2 3 4 5 6 7
// items: [-9 1 3 4 0 0 0 0]
// numberOfElements: 8

/**
 * Implementation of a deque using an array with a circular structure.
 *
 * @param <T> The type of elements stored in the deque.
 */
public class ArrayDeque<T> implements Deque<T> {
    // Instance variables

    /**
     * The actual elements of type T.
     */
    private T[] items;

    /**
     * The number of elements in the array deque.
     */
    private int numberOfElements;

    /**
     * Index of the first array element.
     */
    private int headIndex;

    /**
     * Index of the last array element.
     */
    private int tailIndex;

    private static final int INITIAL_CAPACITY = 8;


    /** Constructor */
    public ArrayDeque() {
        // Initialize array with initial size of 8
        items = (T[]) new Object[INITIAL_CAPACITY];

        // Initialize headIndex and tailIndex circular references.
        headIndex = 0;
        tailIndex = 0;

        // Initialize to 0 as array deque has no elements.
        numberOfElements = 0;

    }

    /** Returns an iterator */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;
        public ArrayDequeIterator() {
           index = 0;
        }

        @Override
        public boolean hasNext(){
            return index < numberOfElements;
        }
        @Override
        public T next() {

            // Calculate actual index
            int actualIndex = (headIndex + index) % items.length;

            // Get item at actual index
            T returnItem = items[actualIndex];

            // Move to next index
            index += 1;

            return returnItem;
        }
    }
    /**
     * Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(Object x) {
        for (int i = 0; i < numberOfElements; i++) {
            if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Equals method to check items of two array deques. Overrides the default boolean equal method of the class.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other instanceof ArrayDeque<?> otherDeque) {
            // Check deque are of same size
            if (this.numberOfElements != otherDeque.numberOfElements) {
                return false;
            }
            // Check that all of MY items are in the other array deque
            for (T x : this) {
                if (!otherDeque.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        // Other deque is not an array deque, so return false
        return false;
    }

    /**
     * toString method that returns string itself instead of memory location
     *
     */
    @Override
    public String toString() {
        return toList().toString();
    }

    /**
     * Add an item to the front of the deque. Assumes the item is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        if (numberOfElements == items.length) {
            // Create new array with double capacity
            resize(numberOfElements * 2);
        }
        // Update headIndex
        headIndex = (headIndex - 1 + items.length) % items.length;

        // Add the new item to the updated head index
        items[headIndex] = x;

        // Increment the number of elements in the deque (NOT the capacity or index)
        numberOfElements += 1;
    }

    /**
     * Add an item to the back of the deque. Assumes item is never null.
     *
     * @param x item to add
     */

    @Override
    public void addLast(T x) {
        if (numberOfElements == items.length) {
            // Create new array with double capacity
            resize(numberOfElements * 2);
        }

        // Update tailIndex
        int newIndex = tailIndex;

        // Add the new item to the calculated index
        items[newIndex] = x;

        // Adjust the references
        tailIndex = (newIndex + 1) % items.length;

        // Increment the number of elements in the deque (NOT the capacity or index)
        numberOfElements += 1;
    }

    /**
     * Resizes the backing array to the target capacity.
     */
    private void resize(int capacity) {
        // Create new array with calculated target capacity
        T[] newItems = (T[]) new Object[capacity];

        // Copy elements starting from headIndex
        for (int i = 0; i < numberOfElements; i++) {
            newItems[i] = items[(headIndex + i) % items.length];
        }

        // Update instance variables
        items = newItems; // Reassign items reference to new array
        headIndex = 0;
        tailIndex = numberOfElements;

    }

    /**
     * Returns a List copy of the deque, so we can verify its values. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        // Start where?? at headIndex, the index of first element
        int startIndex = (headIndex) % items.length;

        // Iterate through all elements starting at headIndex
        for (int i = 0; i < numberOfElements; i++) {
            int currentIndex = (startIndex + i) % items.length;
            returnList.add(items[currentIndex]);
        }
//            returnList.add(items[startIndex]);
//            // Move to next index...in a loop...
//            startIndex = (startIndex + 1) % items.length;
//        }
        return returnList;
    }

    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return numberOfElements;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
//            throw new NoSuchElementException("Deque is empty");
        }
        // Store removed item
        T removedItem = items[headIndex];

        // Optional: set headIndex to null for garbage
        items[headIndex] = null;

        // Update headIndex to next element
        headIndex = (headIndex + 1) % items.length;

        // Update number of elements
        numberOfElements--;

        return removedItem;

    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
//            throw new NoSuchElementException("Deque is empty");
        }
        // Store removed item
        T removedItem = items[(tailIndex - 1 + items.length) % items.length];

        // Optional: set tailIndex to null for garbage
        items[(tailIndex - 1 + items.length) % items.length] = null;

        // Update tailIndex to next element
        tailIndex = (tailIndex - 1 + items.length) % items.length;

        // Update number of elements
        numberOfElements--;

        return removedItem;
    }

    /**
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= numberOfElements) {
            return null;
        }
        return items[index];
    }

    /**
     * This method technically shouldn't be in the interface, but it's here
     * to make testing nice. Gets an element, recursively. Returns null if
     * index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}