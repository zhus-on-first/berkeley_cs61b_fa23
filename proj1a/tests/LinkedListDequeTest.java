import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Random;

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

    /** This tests isEmpty by adding and then removing elements. */
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
    @Test
    public void addElementsAndRemoveSize() {
        // Arrange: create empty deque
        Deque<Integer> lld1 = new LinkedListDeque<>();

        // Act: add elements
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);

        // Act: remove an element
        lld1.removeLast();

        // Assert: check size after moving one element
        assertThat(lld1.size()).isEqualTo(2);

        // Act: remove all remaining elements
        lld1.removeLast();
        lld1.removeLast();

        // Assert: check size after removing all elements
        assertThat(lld1.size()).isEqualTo(0);

    }

    /** Test case for get() of with empty deque */
    @Test
    public void testEmptyDequeInvalidArgumentGet() {
        // Arrange: create empty deque
        Deque<Character> lld1 = new LinkedListDeque<>();

        // Assert: check that get with any index returns null in empty deque
        assertThat(lld1.get(-1)).isNull();
        assertThat(lld1.get(0)).isNull();
        assertThat(lld1.get(2)).isNull();
    }

    /** Test case for get() of non-empty deque */
    @Test
    public void testNonEmptyDequeInvalidArgumentGet() {
        // Arrange: create empty deque
        Deque<Character> lld2 = new LinkedListDeque<>();

        // Act: add some elements to deque
        lld2.addLast('A');
        lld2.addLast('B');
        lld2.addLast('C');

        // Assert: check that invalid index return null
        assertThat(lld2.get(-1)).isNull(); // Negative index
        assertThat(lld2.get(3)).isNull(); // index equal to or greater than size
        assertThat(lld2.get(28723)).isNull(); // Out of bounds
    }

    /** Test case for get() with non-empty deque with valid index */
    @Test
    public void testNonEmptyListValidArgumentGet() {
        // Arrange: create empty deque
        Deque<Integer> lld3 = new LinkedListDeque<>();

        // Act: add elements
        lld3.addFirst(10);
        lld3.addLast(15);
        lld3.addFirst(5);

        // Assert: check valid index returns expected element
        assertThat(lld3.get(0)).isEqualTo(5);
        assertThat(lld3.get(1)).isEqualTo(10);
        assertThat(lld3.get(2)).isEqualTo(15);

    }

    /** Check that removeFirst works */
    @Test
    @Tag("remove_first")
    public void testRemoveFirst() {
        // Arrange: create empty deque
        Deque<Integer> lld1 = new LinkedListDeque<>();

        // Act: call removeFirst on empty deque
        Integer removedElement = lld1.removeFirst();

        // Assert: check that result is null or what is appropriate
        assertThat(removedElement).isNull();
        assertThat(lld1.toList()).isEmpty();

    }

    /** Add one element. Check that removeFirst works */
    @Test
    public void testAddAnElementRemoveFirst() {
        // Arrange: create empty deque
        Deque<Integer> lld2 = new LinkedListDeque<>();

        // Act: add and call removeFirst
        lld2.addFirst(1);
        Integer removedElement = lld2.removeFirst();

        // Assert: check that result is null or what is appropriate
        assertThat(removedElement).isEqualTo(1);
        assertThat(lld2.toList()).isEmpty();

    }

    /** Add multiple elements. Check that removeFirst works */
    @Test
    public void testAddElementsRemoveFirst() {
        // Arrange: create empty deque
        Deque<Integer> lld1 = new LinkedListDeque<>();

        // Act: add and call removeFirst
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]
        lld1.removeFirst();

        // Assert: check that result is [-1, 0, 1, 2]
        assertThat(lld1.toList()).containsExactly(-1, 0, 1, 2).inOrder();
    }


    /** Add elements and remove almost all of them.
     * Check removing last element with removeFirst. */
    @Test
    @Tag("remove_first_to_empty")
    public void testAddElementsRemoveFirstToEmpty() {
        // Arrange: create empty deque
        Deque<Integer> lld3 = new LinkedListDeque<>();

        // Act: add elements and remove all but last one
        lld3.addLast(0);   // [0]
        lld3.addLast(1);   // [0, 1]
        lld3.addFirst(-1); // [-1, 0, 1]
        lld3.addLast(2);   // [-1, 0, 1, 2]
        lld3.addFirst(-2); // [-2, -1, 0, 1, 2]
        while (!lld3.isEmpty()) {
            lld3.removeFirst(); // []
        }

        // Assert: check that deque is now empty
        assertThat(lld3.toList()).isEmpty();

    }

    /** Add some elements and remove almost all of them.
     * Check removing second to last element with removeFirst. */
    @Test
    @Tag("remove_first_to_one")
    public void testAddElementsRemoveFirstToOne() {
        // Arrange: create empty deque
        Deque<Integer> lld3 = new LinkedListDeque<>();

        // Act: add elements and remove almost all.
        lld3.addLast(0);   // [0]
        lld3.addLast(1);   // [0, 1]
        lld3.addFirst(-1); // [-1, 0, 1]
        lld3.addLast(2);   // [-1, 0, 1, 2]
        lld3.addFirst(-2); // [-2, -1, 0, 1, 2]
        while (lld3.size() > 1) { // [1, 2]
            lld3.removeFirst(); // [2]
        }

        // Assert: check that deque contains the last element
        assertThat(lld3.toList()).containsExactly(2);
    }

    /** Check that removeLast works */
    @Test
    @Tag("remove_last")
    public void testRemoveLast() {
        // Arrange: create empty deque
        Deque<Integer> lld1 = new LinkedListDeque<>();

        // Act: call removeLast on empty deque
        lld1.removeLast();

        // Assert: check that result is empty or what is appropriate
        assertThat(lld1.toList()).isEmpty();
    }

    /** Add some elements and remove almost all of them.
     * Check removing last element with removeLast will return empty/null deque. */
    @Test
    @Tag("remove_last_to_empty")
    public void testAddElementsRemoveLastToEmpty() {
        // Arrange: create empty deque
        Deque<Integer> lld3 = new LinkedListDeque<>();

        // Act: add elements and remove all but last one
        Random random = new Random();

        int elementsToAdd = random.nextInt(10);

        for (int i = 0; i < elementsToAdd; i++) {
            lld3.addLast(i);
        }

        while (!lld3.isEmpty()) {
            lld3.removeLast();
        }

        // Assert: check that deque is now empty
        assertThat(lld3.toList()).isEmpty();

    }

    /** Add some elements and remove almost all of them.
     * Check removing second to last element with removeLast. */
    @Test
    @Tag("remove_last_to_one")
    public void testAddElementsRemoveLastToOne() {
        // Arrange: create empty deque
        Deque<Float> lld3 = new LinkedListDeque<>();

        // Act: add elements and remove all but last one
        Random random = new Random();
        int numberOfElementsToAdd = random.nextInt(11);
        for (int i = 0; i < numberOfElementsToAdd; i++) {
            int randomInt = random.nextInt(31) - 15; // Generate random integer between -15 and 15
            float randomFloat = (float) randomInt;
            lld3.addLast(randomFloat);
        }

        while (lld3.size() > 1) {
            lld3.removeLast();
        }

        // Assert: check that deque has 1 element left
        assertThat(lld3.toList()).hasSize(1);

    }

    @Test
    /* This test performs interspersed removeFirst and removeLast calls. */
    public void testRemoveFirstAndRemoveLast() {
        Deque<Integer> lld1 = new LinkedListDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.removeLast(); // [0]
        lld1.addFirst(-1); // [-1, 0]
        lld1.addLast(2);   // [-1, 0, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 2]
        lld1.removeFirst(); // [-1, 0, 2]

        assertThat(lld1.toList()).containsExactly(-1, 0, 2).inOrder();
    }


}