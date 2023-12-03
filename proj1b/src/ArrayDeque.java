import java.util.ArrayList;
import java.util.List;

// index:   0 1 2 3 4 5 6 7
// items: [-9 1 3 4 0 0 0 0]
// size: 8

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
    private int size;

    /**
     * Index of the first array element.
     */
    private int nextFirst;

    /**
     * Index of the last array element.
     */
    private int nextLast;

    private static final int INITIAL_CAPACITY = 8;


    /** Constructor */
    public ArrayDeque() {
        // Initialize array with initial size of 8
        items = (T[]) new Object[INITIAL_CAPACITY];

        // Initialize nextFirst and nextLast circular references.
        nextFirst = 0; // First element goes to index 0
        nextLast = 0; // Next "last" element added to index 1

        // Initialize size to 0 as array deque has no elements.
        size = 0;

    }

    /**
     * Resizes the backing array to the target capacity.
     * TODO: WORK ON RESIZING AFTER ALL METHODS WORK WITH FIXED-SIZE ARRAY FIRST.
     */
//    private void resize(int capacity) {
//        int[] a = (T[]) new Object(capacity);
//    }

    /**
     * Add an item to the front of the deque. Assumes the item is never null.
     *
     * @param x item to add
     */
    @Override
    public void addFirst(T x) {
        // Calculate the index to add the new item
        int newIndex = (nextFirst - 1 + items.length) % items.length;

        // Add the new item to the calculated index
        items[newIndex] = x;

        // Adjust circular references
        nextFirst = newIndex;

        // Increment size, the number of elements in the deque (NOT the capacity or index)
        size += 1;
    }

    /**
     * Add an item to the back of the deque. Assumes item is never null.
     *
     * @param x item to add
     */
    @Override
    public void addLast(T x) {
        // Calculate index to add the new item
        int newIndex = nextLast;

        // Add the new item to the calculated index
        items[newIndex] = x;

        // Adjust the references
        nextLast = (newIndex + 1) % items.length;

        // Increment size, the number of elements in the deque (NOT the capacity or index)
        size += 1;
    }

    /**
     * Returns a List copy of the deque, so we can verify its values. Does not alter the deque.
     *
     * @return a new list copy of the deque.
     */

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();  // TODO: pass in size, the number of actual elements? or capacity if capacity == items.length?

        // Start where?? at nextFirst, the index of first element as defined
        int startIndex = nextFirst;

        // Iterate through all elements after the nextFirst
        while (size != 0) {
            returnList.add(items[startIndex]);
            // Move to next index...in a loop...
            startIndex = (startIndex + 1) % items.length;
            size -= 1;
        }
        return returnList;
    }

//    @Override
//    public List<T> toListWithNulls() {
//        List<T> returnList = new ArrayList<>();
//
//        // Start where?? at nextFirst, the index of first element as defined
//        int current = nextFirst;
//
//        // Iterate through all index up to capacity
//        while (size != items.length) {
//            if (items[current] == null) {
//                returnList.add(null);
//            } else {
//                returnList.add(items[current]);
//            }
//            // Move to next index...in a loop...
//            current = (current + 1) % items.length;
//            size -= 1;
//        }
//        return returnList;
//    }
//
//    public List<T> toListUsingForLoopWithNulls() {
//        List<T> returnList = new ArrayList<>();
//
//        for (int i = 0; i < items.length; i += 1) {
    /**
     * Start at nextFirst and traverse the length of the index (each index--not size, which is about elements) and go back to nextFirst
     */
//            int index = (nextFirst + i) % items.length;
//            if (items[index] == null) {
//                returnList.add(null);
//            } else {
//                returnList.add(items[index]);
//            }
//        }
//
//        return returnList;
//    }


    /**
     * Returns if the deque is empty. Does not alter the deque.
     *
     * @return {@code true} if the deque has no elements, {@code false} otherwise.
     */
    @Override
    public boolean isEmpty() {
        return false;
    }

    /**
     * Returns the size of the deque. Does not alter the deque.
     *
     * @return the number of items in the deque.
     */
    @Override
    public int size() {
        return 0;
    }

    /**
     * Remove and return the element at the front of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeFirst() {
        return null;
    }

    /**
     * Remove and return the element at the back of the deque, if it exists.
     *
     * @return removed element, otherwise {@code null}.
     */
    @Override
    public T removeLast() {
        return null;
    }

    /**
     * The Deque abstract data type does not typically have a get method,
     * but we've included this extra operation to provide you with some
     * extra programming practice. Gets the element, iteratively. Returns
     * null if index is out of bounds. Does not alter the deque.
     *
     * @param index index to get
     * @return element at {@code index} in the deque
     */
    @Override
    public T get(int index) {
        return null;
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
        return null;
    }
}
