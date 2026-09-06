package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    /**
     * Check init, basic add, remove and size count
     */
    public void sizeCountTest() {
        ArrayDeque<String> deque = new ArrayDeque<>();
        assertTrue(deque.isEmpty());

        deque.addFirst("Hello");
        assertEquals(1, deque.size());
        assertFalse(deque.isEmpty());

        deque.removeLast();
        assertTrue(deque.isEmpty());

        deque.addFirst("Hello");
        assertEquals(1, deque.size());
        deque.addFirst("Hello");
        assertEquals(2, deque.size());

        deque.printDeque();

    }

    @Test
    /**
     * test resize
     */
    public void resizeTest() {
        ArrayDeque<Integer> arr = new ArrayDeque<>();
        for (int i = 0; i < 16; i++) {
            arr.addFirst(i);
        }
        assertEquals(16, arr.size());

        for (int i = 0; i < 16; i++) {
            arr.addFirst(i);
        }
        assertEquals(32, arr.size());

        for (int i = 0; i < 16; i++) {
            System.out.print(arr.removeLast() + " ");
        }
        assertEquals(16, arr.size());



    }
}
