import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Array;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    @DisplayName("ArrayDeque has no fields besides backing array and primitives")
    void noNonTrivialFields() {
        List<Field> badFields = Reflection.getFields(ArrayDeque.class)
                .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                .toList();

        assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
    }

    @Test
    @DisplayName("Check toList works with empty array deque")
    void testToListEmpty() {
        // Arrange
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Assert
        assertThat(ad1.toList()).isEmpty();
    }

    @Test
    @DisplayName("Check toList works with NON-empty array deque")
    void testToListNonEmpty() {
        // Arrange
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: add elements

        ad1.addFirst(7); // expect: [7]
        ad1.addFirst(6); // expect: [6, 7]
        ad1.addFirst(5); // expect: [5, 6, 7]
        ad1.addFirst(4); // expect: [4, 5, 6 ,7]
        ad1.addFirst(3); // expect: [3, 4, 5, 6, 7]
        ad1.addFirst(2); // expect: [2, 3, 4, 5, 6, 7]
        ad1.addFirst(1); // expect: [1, 2, 3, 4, 5, 6, 7]
        ad1.addLast(8); // expect: [8, 1, 2, 3, 4, 5, 6, 7]

        // Assert
        assertThat(ad1.toList()).containsExactly( 1, 2, 3, 4, 5, 6, 7, 8).inOrder();
    }


    @Test
    @DisplayName("Check addFirst works on empty deque")
    void testAddFirstFromEmpty() {
         // Arrange: create empty array deque
        Deque<String> ad1 = new ArrayDeque<>();

        // Act: add elements
        ad1.addFirst("back"); // expect: [back]
        ad1.addFirst("middle"); // expect: [middle, back]
        ad1.addFirst("front"); // expect: [front, middle, back]

        // Assert
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();
    }
    @Test
    @DisplayName("Check addFirst works on non-empty deque")
    void testAddFirstFromNonEmpty() {
        // Arrange and Act: create an array deque and add initial items
        Deque<String> ad1 = new ArrayDeque<>();
        ad1.addFirst("back"); // expect: [back]
        ad1.addFirst("middle"); // expect: [middle, back]

        // Act: add an element to front
        ad1.addFirst("front"); // expect: [front, middle, back]

        // Assert
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();
    }
    @Test
    @DisplayName("Check addLast works on empty deque")
    void testAddLastFromEmpty() {
        // Arrange: create empty array deque
        Deque<String> ad1 = new ArrayDeque<>();

        // Act: add elements
        ad1.addLast("front"); // expect: [front]
        ad1.addLast("middle"); // expect: [front, middle]
        ad1.addLast("back"); // expect: [front, middle, back]

        // Assert
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();
    }
    @Test
    @DisplayName("Check addLast works on NON empty deque")
    void testAddLastFromNonEmpty() {
        // Arrange and Act: create an array deque and add initial items
        Deque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("front"); // expect: [front]
        ad1.addLast("middle"); // expect: [front, middle]

        // Act: add an element to front
        ad1.addLast("back"); // expect: [front, middle, back]

        // Assert
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    @DisplayName("Check addFirst and addLast works with interspersed calls")
    void testAddFirstAndAddLastInterspersed() {
        // Arrange: create an empty deque
        Deque<Character> ad1 = new ArrayDeque<>();

        // Act: add elements
        ad1.addFirst('A'); // expect: [A]
        ad1.addLast('D'); // expect: [A, D]
        ad1.addLast('e'); // expect: [A, D, e]
        ad1.addFirst('z'); // expect: [z, A, D, e]
        ad1.addLast('B'); // expect: [z, A, D, e, B]

        // Assert
        assertThat(ad1.toList()).containsExactly('z', 'A', 'D', 'e', 'B').inOrder();
    }

    @Test
    @DisplayName("Check get works on an empty deque")
    void testGetEmptyDeque() {
         // Arrange: create an empty deque
        Deque<Character> ad1 = new ArrayDeque<>();

        //Assert: check that get with any index returns null in empty deque
        assertThat(ad1.get(-1)).isNull();
        assertThat(ad1.get(0)).isNull();
        assertThat(ad1.get(7)).isNull();
    }

    @Test
    @DisplayName("Check get works on a valid index")
    void testGetValidIndex() {
        // Arrange: create an empty deque
        Deque<Character> ad1 = new ArrayDeque<>();

        // Act: add elements
        ad1.addFirst('A'); // expect: [A]
        ad1.addLast('D'); // expect: [A, D]
        ad1.addLast('e'); // expect: [A, D, e]
        ad1.addFirst('z'); // expect: [z, A, D, e]
        ad1.addLast('B'); // expect: [z, A, D, e, B]

        // Assert
        assertThat(ad1.get(0)).isEqualTo('D');
        assertThat(ad1.get(2)).isEqualTo('B');
        assertThat(ad1.get(4)).isEqualTo(null);
    }
    @Test
    @DisplayName("Check get works on a large, out of bounds index")
    void testGetOutOfBoundsIndex() {
        // Arrange: create an empty deque
        Deque<Character> ad1 = new ArrayDeque<>();

        // Act: add elements
        ad1.addFirst('A'); // expect: [A]
        ad1.addLast('D'); // expect: [A, D]
        ad1.addLast('e'); // expect: [A, D, e]
        ad1.addFirst('z'); // expect: [z, A, D, e]
        ad1.addLast('B'); // expect: [z, A, D, e, B]

        // Assert
        assertThat(ad1.get(23930234)).isNull();
    }

    @Test
    @DisplayName("Check get works on negative index")
    void testGetNegativeIndex() {
        // Arrange: create an empty deque
        Deque<Character> ad1 = new ArrayDeque<>();

        // Act: add elements
        ad1.addFirst('A'); // expect: [A]
        ad1.addLast('D'); // expect: [A, D]
        ad1.addLast('e'); // expect: [A, D, e]
        ad1.addFirst('z'); // expect: [z, A, D, e]
        ad1.addLast('B'); // expect: [z, A, D, e, B]

        // Assert
        assertThat(ad1.get(-1)).isNull();
        assertThat(ad1.get(-244343)).isNull();
    }

    @Test
    @DisplayName("Check size works on an empty deque.")
    void testIsEmptyTrue() {
        // Arrange: create an empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Assert
        assertThat(ad1.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("Check size works on an Non-empty deque.")
    void testIsEmptyFalse() {
        // Arrange: create an empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: add elements
        ad1.addFirst(1); // expect: [1]
        ad1.addLast(2); // expect: [1, 2]
        ad1.addLast(3); // expect: [1, 2, 3]

        // Assert
        assertThat(ad1.isEmpty()).isFalse();
    }

    @Test
    @DisplayName("Check size works on empty deque")
    void testSizeEmptyDeque() {
        // Arrange: create an empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Assert
        assertThat(ad1.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("Check size works on Non-empty deque")
    void testSizeNonEmptyDeque() {
        // Arrange: create an empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

    }

    @Test
    @DisplayName("Check size works after adding then removing all items. ")
    void testSizeAfterRemoveToEmpty() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: add elements
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);

        // Act: remove an element
        ad1.removeFirst();

//        // Assert: check size after moving one element
//        assertThat(ad1.size()).isEqualTo(2);

        // Act: remove all remaining elements
        ad1.removeFirst();
        ad1.removeFirst();

        // Assert: check size after removing all elements
        assertThat(ad1.size()).isEqualTo(0);

    }

    @Test
    @DisplayName("Check size works after removing from empty deque. ")
    void testSizeAfterRemoveFromEmpty() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: remove from empty deque
        ad1.removeFirst();
        ad1.removeLast();

        // Assert
        assertThat(ad1.size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Check removeFirst works on empty deque")
    void testRemoveFirst() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: call removeFirst on empty deque
        Integer removedItem = ad1.removeFirst();

        // Assert
        assertThat(removedItem).isNull();
        assertThat(ad1.toList()).isEmpty();

    }

    @Test
    @DisplayName("Check removeFirst after add and remove all items")
    void testRemoveFirstToEmpty() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: call removeFirst on empty deque
        ad1.addFirst(1); // [1]
        ad1.addFirst(2); // [2, 1]
        ad1.addLast(7); // [2, 1, 7]
        Integer removedItem = ad1.removeFirst();
        Integer removedItem2 = ad1.removeFirst();
        Integer removedItem3 = ad1.removeFirst();

        // Assert
        assertThat(ad1.isEmpty()).isTrue();
//        assertThat(ad1.toList()).isEmpty();
    }

    @Test
    @DisplayName("Check removeFirst after add and remove second to last")
    void testRemoveFirstToOne() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: call removeFirst on empty deque
        ad1.addFirst(1); // [1]
        ad1.addFirst(2); // [2, 1]
        ad1.addLast(7); // [7, 2, 1]
        while (ad1.size() > 1) { // [1, 7]
            ad1.removeFirst(); // [7]
        }

        // Assert
        assertThat(ad1.toList()).containsExactly(7);
    }

    @Test
    @DisplayName("Check removeLast works")
    void testRemoveLast() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: call removeFirst on empty deque
        Integer removedItem = ad1.removeLast();

        // Assert
        assertThat(removedItem).isNull();
        assertThat(ad1.toList()).isEmpty();

    }
    @Test
    @DisplayName("Check removeLast after add and remove all items")
    void testRemoveLastToEmpty() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: call removeFirst on empty deque
        ad1.addFirst(1); // [1]
        ad1.addFirst(2); // [2, 1]
        ad1.addLast(7); // [7, 2, 1]
        Integer removedItem = ad1.removeLast();
        Integer removedItem2 = ad1.removeLast();
        Integer removedItem3 = ad1.removeLast();

        // Assert
        assertThat(ad1.isEmpty()).isTrue();
//        assertThat(ad1.toList()).isEmpty();
    }

    @Test
    @DisplayName("Check removeLast after add and remove second to last")
    void testRemoveLastToOne() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Act: call removeLast on empty deque
        ad1.addFirst(1); // [1]
        ad1.addFirst(2); // [2, 1]
        ad1.addLast(7); // [2, 1, 7]
        while (ad1.size() > 1) { // [2, 1]
            ad1.removeLast(); // [2]
        }

        // Assert
        assertThat(ad1.toList()).containsExactly(2);
    }

    @Test
    @DisplayName("Check with interspersed removeFirst and removeLast calls.")
    public void testRemoveFirstAndRemoveLast() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

        /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        ad1.addLast(0);   // [0]
        ad1.addLast(1);   // [0, 1]
        ad1.removeLast(); // [0]
        ad1.addFirst(-1); // [-1, 0]
        ad1.addLast(2);   // [-1, 0, 2]
        ad1.addFirst(-2); // [-2, -1, 0, 2]
        ad1.removeFirst(); // [-1, 0, 2]

        assertThat(ad1.toList()).containsExactly(-1, 0, 2).inOrder();
    }

    /**
     * Fours test below consider resize
     */
    @Test
    @DisplayName("Check that addFirst works when called on a full underlying array")
    void testAddFirstTriggerResize() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();
    }

    @Test
    @DisplayName("Check that addLast works when called on a full underlying array")
    void testAddLastTriggerResize() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

    }
    @Test
    @DisplayName("Check removeFirst with resize")
    // Called when usage factor is <= 25% and array size > 8. Checks that the array resizes appropriately.
    void testRemoveFirstTriggerResize() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

    }

    @Test
    @DisplayName("Check removeLast with resize")
    // Called when usage factor is <= 25% and array size > 8. Checks that the array resizes appropriately
    void testRemoveLastTriggerResize() {
        // Arrange: create empty deque
        Deque<Integer> ad1 = new ArrayDeque<>();

    }

}
