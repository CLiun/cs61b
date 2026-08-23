package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> expected = new AListNoResizing<>();
        BuggyAList<Integer> actual = new BuggyAList<>();
        int lower = 4;
        int upper = 7;
        for (int i = lower; i < upper; i++) {
            expected.addLast(i);
            actual.addLast(i);
        }

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.removeLast(), actual.removeLast());
        assertEquals(expected.removeLast(), actual.removeLast());
        assertEquals(expected.removeLast(), actual.removeLast());
    }
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> buglist = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
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
                // getLast
                if (L.size() == 0) {
                    continue;
                }
                int last = L.getLast();
                int lasting = buglist.getLast();
                assertEquals(last, lasting);
                // System.out.println("getLast(" + last + ")");
            } else if (operationNumber == 3) {
                // removeLast
                if (L.size() == 0) {
                    continue;
                }
                int last = L.removeLast();
                int lasting = buglist.removeLast();
                assertEquals(last, lasting);
                // System.out.println("removeLast(" + last + ")");
            }
        }
    }
}
