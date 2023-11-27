import java.util.List;

public class LinkedListDeque<T> implements Deque<T> {
    // Instance variables
    private intNode sentinel;
    private int size;
    // Node class
    private class intNode {
        public T item;
        public intNode prev;
        public intNode next;

        // Constructor for new Node
        public intNode (T i, intNode p, intNode n){
            item = i;
            prev = p;
            next = n;
        }
    }


    // Constructor to initialize empty deque with circular sentinel
    public LinkedListDeque() {
        sentinel = new intNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    @Override
    public void addFirst(T x) {

    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
