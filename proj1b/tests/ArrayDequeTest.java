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
    @DisplayName("Check that toList works with empty array deque")
    void testToListEmpty() {
        // Arrange
        Deque<Integer> ad1 = new ArrayDeque<>();

        // Assert
        assertThat(ad1.toList()).isEmpty();
    }

    @Test
    @DisplayName("Check that toList works with NON-empty array deque")
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
    @DisplayName("Check that addFirst works on empty deque")
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
    @DisplayName("Check that addFirst works on non-empty deque")
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
    @DisplayName("Check that addLast works on empty deque")
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
    @DisplayName("Check that addLast works on NON empty deque")
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
    @DisplayName("Check that addFirst and addLast works with interspersed calls")
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
    @DisplayName("Check that get works on an empty deque")
    void testGetEmptyDeque() {
         // Arrange: create an empty deque
        Deque<Character> ad1 = new ArrayDeque<>();

        //Assert: check that get with any index returns null in empty deque
        assertThat(ad1.get(-1)).isNull();
        assertThat(ad1.get(0)).isNull();
        assertThat(ad1.get(7)).isNull();
    }

    @Test
    @DisplayName("Check that get works on a valid index")
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
    @DisplayName("Check that get works on a large, out of bounds index")
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
    @DisplayName("Check that get works on negative index")
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

}
