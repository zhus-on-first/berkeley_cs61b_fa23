import deque.MaxArrayDeque;
import org.junit.Test;
import org.junit.jupiter.api.*;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest {

    @Test
    @DisplayName("Check that a MaxArrayDeque can be created with a comparator")
    public void testMaxArrayDequeBasic() {
        // Arrange
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);
        MaxArrayDeque<Integer> ad = new MaxArrayDeque<>(comparator);

        // Act

        // Assert
        assertThat(ad).isEmpty();

    }

    @Test
    @DisplayName("Check max method using constructor's comparator")
    public void testMaxWithDefaultComparator() {
        // Arrange
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);
        MaxArrayDeque<Integer> ad = new MaxArrayDeque<>(comparator);

        // Act
        ad.addLast(3); // 3
        ad.addLast(1); // 3, 1
        ad.addLast(4); // 3, 1, 4

        // Assert
        assertThat(ad.max()).isEqualTo(4);

    }

    @Test
    @DisplayName("Check max on empty deque")
    public void testMaxOnEmptyDeque() {
        // Arrange
        Comparator<Integer> comparator = (a, b) -> Integer.compare(a, b);
        MaxArrayDeque<Integer> ad = new MaxArrayDeque<>(comparator);

        // Act

        // Assert
        assertThat(ad.max()).isNull();

    }

    @Test
    @DisplayName("Check max with custom comparator")
    public void testMaxWithCustomComparator() {
        // Arrange
        Comparator<Integer> reverseComparator = (a, b) -> b - a;
        MaxArrayDeque<Integer> ad = new MaxArrayDeque<>(reverseComparator);

        // Act
        ad.addLast(3); // 3
        ad.addLast(1); // 3, 1
        ad.addLast(4); // 3, 1, 4

        // Assert
        assertThat(ad.max(reverseComparator)).isEqualTo(1);

    }

    @Test
    @DisplayName("Check max with custom comparator on empty deque")
    public void testMaxWithCustomComparatorOnEmptyDeque() {
        // Arrange
        Comparator<Integer> reverseComparator = (a, b) -> b - a;
        MaxArrayDeque<Integer> ad = new MaxArrayDeque<>(reverseComparator);

        // Act

        // Assert
        assertThat(ad.max(reverseComparator)).isNull();

    }

}
