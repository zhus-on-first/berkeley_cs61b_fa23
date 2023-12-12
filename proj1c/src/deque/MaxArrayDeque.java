package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    // instance variable field to hold the passed in comparator
    private final Comparator<T> comparator;

    /* Constructor */
    public MaxArrayDeque(Comparator<T> c) {
        super(); // Call ArrayDeque's constructor
        this.comparator = c;
    }

    /**
     * @return max element in the deque
     */
    public T max() {
        if (isEmpty()) {
            return null;
        }

        T maxItem = null;
        for (T item: this) {
            if (maxItem == null || comparator.compare(item, maxItem) > 0)  {
                maxItem = item;
            }
        }
        return maxItem;

    }
    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = null;
        for (T item: this) {
            if (maxItem == null || c.compare(item, maxItem) > 0)  {
                maxItem = item;
            }
        }
        return maxItem;
    }

}
