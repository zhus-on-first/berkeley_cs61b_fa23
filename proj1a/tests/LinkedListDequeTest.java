import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

     @Test
     @DisplayName("LinkedListDeque has no fields besides nodes and primitives")
     void noNonTrivialFields() {
         Class<?> nodeClass = NodeChecker.getNodeClass(LinkedListDeque.class, true);
         List<Field> badFields = Reflection.getFields(LinkedListDeque.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(nodeClass) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not nodes or primitives").that(badFields).isEmpty();
     }

     @Test
     /* In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /* In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque<String> lld1 = new LinkedListDeque<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /* This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque.

    /* This tests isEmpty with empty deque */
    @Test
    public void emptyDequeIsEmpty() {
        // Arrange: Create empty deque
        Deque<String> lld1 = new LinkedListDeque<>();

        // Assert: check that deque is initially empty
        assertThat(lld1.isEmpty()).isTrue();

    }

    /* This tests isEmpty after adding elements */
    @Test
    public void addElementIsNotEmpty() {
        // Arrange: Create empty deque
        Deque<String> lld1 = new LinkedListDeque<>();

        // Act: add element
        lld1.addFirst("test");

        // Assert: Check that deque is no longer empty
        assertThat(lld1.isEmpty()).isFalse();
    }

    /** This tests isEmpty by adding and then removing elements.
     * In sequence of this lab, remove methods not available yet.
     * TODO: Return to add these checks using remove methods */

    /*
    @Test
    public void addAndRemoveIsEmpty() {
        // Arrange: Create empty deque
        Deque<String> lld1 = new LinkedListDeque<>();

        // Act: add element
        lld1.addFirst("test");
        // Act: Remove added element.
        lld1.removeFirst();

        // Assert: Check that deque is empty again after removal
        assertThat(lld1.isEmpty()).isTrue();
    }
*/

    /** The following tests size() calls. */
    @Test
    public void emptyDequeSize() {
        // Arrange: create empty deque
        Deque<Integer> lld1 = new LinkedListDeque<>();

        // Assert: check that deque size is initially 0;
        assertThat(lld1.size()).isEqualTo(0);

    }

    @Test
    public void addElementsSize() {
        // Arrange: create empty deque
        Deque<Integer> lld1 = new LinkedListDeque<>();

        // Act: add element
        lld1.addFirst(1);

        // Assert: check size after adding 1 element
        assertThat(lld1.size()).isEqualTo(1);

        // Act: more elements
        lld1.addLast(2);
        lld1.addLast(3);

        // Assert: check size after adding more elements
        assertThat(lld1.size()).isEqualTo(3);

    }
    /** In sequence of this lab, remove methods not available yet.
     * TODO: Return to add these checks using remove methods
     * */
//    @Test
//    public void addElementsAndRemoveSize() {
//        // Arrange: create empty deque
//        Deque<Integer> lld1 = new LinkedListDeque<>();
//
//        // Act: add elements
//        lld1.addLast(1);
//        lld1.addLast(2);
//        lld1.addLast(3);
//
//        // Act: remove an element
////        lld1.removeLast();
//
//        // Assert: check size after moving one element
////        assertThat(lld1.size()).isEqualTo(2);
//
//        // Act: remove all remaining elements
////        lld1.removeLast();
////        lld1.removeLast();
//
//        // Assert: check size after emoving all elements
////        assertThat(lld1.size()).isEqualTo(0);
//
//    }

}