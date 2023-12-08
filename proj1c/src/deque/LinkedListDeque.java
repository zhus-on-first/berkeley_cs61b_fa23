package deque;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a deque using a doubly-linked list with a sentinel node.
 *
 * @param <T> The type of elements stored in the deque.
 */
public class LinkedListDeque<T> implements Deque<T> {
    // Instance variables
    /**
     * The sentinel node serves as a dummy node and helps simplify edge cases.
     */
    private Node sentinel;

    /**
     * The number of elements in the deque.
     */
    private int size;

    // Node class
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        /**
         * Constructor for a new node.
         *
         * @param i The element to be stored in the node.
         * @param p The previous node in the list.
         * @param n The next node in the list.
         */
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    /**
     * Constructs an empty deque with a circular sentinel node.
     * The sentinel node is initially set to point to itself in both directions.
     */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    /**
     * Create new node with provided item x, pointing to the current first node.
     * Sentinel is first node if size is 0.
     *
     * @param x The element to be added to the front of the deque.
     */
    @Override
    public void addFirst(T x) {
        // Add item to front/beginning/left of deque
        Node newNode = new Node(x, sentinel, sentinel.next);

        // Adjust references to include new node
        sentinel.next.prev = newNode;
        sentinel.next = newNode;

        // Increment deque size
        size++;

    }

    /**
     * Create new node with provided item x, pointing to the current first node.
     * Sentinel is first node if size is 0.
     *
     * @param x The element to be added to the front of the deque.
     */
    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, sentinel.prev, sentinel);
//        sentinel.next.prev = newNode;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    /**
     * Take all elements of the deque after the sentinel and adds them to a list.
     * This is so we can verify our deque's values.
     *
     */
    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();

        // Start at node after sentinel
        Node current = sentinel.next;

        // Iterate through all nodes
        while (current != sentinel) {
            returnList.add(current.item);
            current = current.next;
        }

        return returnList;
    }

    @Override
    public boolean isEmpty() {
//        return size == 0;
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        // Remove the item at front/head of deque and return the resulting deque
        if (isEmpty()) {
            return null;
//            throw new NoSuchElementException("Deque is empty");
        }
        // Store removed item
        T removedItem = sentinel.next.item;

        // Update sentinel next link to skip first item
        sentinel.next = sentinel.next.next;

        // update prev link of new first item (which we set to sentinel.next in line above) to sentinel
        sentinel.next.prev = sentinel;
        size--;
        return removedItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
//            throw new NoSuchElementException("Deque is empty");
        }
        // Store removed item
        T removedItem = sentinel.prev.item;

        // Update sentinel prev link to skip last item
        sentinel.prev = sentinel.prev.prev;

        // Update next link of new last item (which we set to sentinel.prev in live above) to sentinel
        sentinel.prev.next = sentinel;
        size--;
        return removedItem;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

//    @Override
//    public T getRecursive(int index) {
//        return null;
//    }

    @Override
    public T getRecursive(int index) {
        // Get the item at the given index recursively

        return getRecursiveHelper(index, sentinel.next);

    }
    private T getRecursiveHelper(int index, Node current) {
        if (index == 0) {
            return current.item;
        }
        if (current == sentinel) {
            return null; // or throw an exception
        }
        return getRecursiveHelper(index - 1, current.next);
    }

}
