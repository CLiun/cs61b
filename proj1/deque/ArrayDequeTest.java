package deque;

import edu.princeton.cs.algs4.StdRandom;
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

    @Test
    public void randomizedTest() {
        Deque<Integer> L = new ArrayDeque<>();
        LinkedListDeque<Integer> buglist = new LinkedListDeque<>();
        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 6);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                buglist.addLast(randVal);
                // System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizebug = buglist.size();
                assertEquals(size, sizebug);
                // System.out.println("size: " + size);
            } else if (operationNumber == 2) {
                // removeLast
                if (L.size() == 0) {
                    continue;
                }
                int last = L.removeLast();
                int lasting = buglist.removeLast();
                assertEquals(last, lasting);
                // System.out.println("getLast(" + last + ")");
            } else if (operationNumber == 3) {
                // addFirst
                int revVal = StdRandom.uniform(0, 100);
                L.addFirst(revVal);
                buglist.addFirst(revVal);
            } else if (operationNumber == 4) {
                // removeFirst
                if (L.size() == 0) {
                    continue;
                }
                int first = L.removeFirst();
                int first1 = buglist.removeFirst();
                assertEquals(first, first1);
            } else if (operationNumber == 5) {
                // get
                if (L.size() == 0) {
                    continue;
                }
                int index = StdRandom.uniform(0, L.size());
                int retVal1 = L.get(index);
                int retVal2 =  buglist.getRecursive(index);
                assertEquals(retVal1, retVal2);
            }
        }
    }

}
