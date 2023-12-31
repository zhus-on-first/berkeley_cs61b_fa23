import deque.ArrayDeque;
import deque.Deque;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.Iterator;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    @DisplayName("Check iterator without toList")
    public void testAddLastTestWithOutToList() {
        // Arrange
        Deque<String> ad1 = new ArrayDeque<>();

        // Act
        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        // Assert
        assertThat(ad1).containsExactly("front", "middle", "back");
    }

    @Test
    @DisplayName("Check iterator directly by calling it")
    public void testAddLastTestWithIterator() {
        // Arrange
        Deque<String> ad1 = new ArrayDeque<>();

        // Act
        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]

        // Assert
        Iterator<String> iterator = ad1.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("front", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("middle", iterator.next());
        assertTrue(iterator.hasNext());
        assertEquals("back", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    @DisplayName("Check equals method")
    public void testEqualDeques() {
        // Arrange
        Deque<String> ad1 = new ArrayDeque<>();
        Deque<String> ad2 = new ArrayDeque<>();

        // Act
        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        ad2.addLast("front");
        ad2.addLast("middle");
        ad2.addLast("back");

        // Assert
        assertThat(ad1).isEqualTo(ad2);
    }

    @Test
    @DisplayName("Check toString method")
    public void testToString() {
        // Arrange
        Deque<String> ad1 = new ArrayDeque<>();

        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        System.out.println(ad1);
    }

}
