package deque;

// import jh61b.junit.In;

import java.util.Iterator;

/*
initialize a deque with a sentinel node.
The first node is sentinel.next
The last node is sentinel.prev
size() return the item number of deque(sentinel exclusive),
 */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int currentSize;
    private LLNode sentinel;

    private class LLNode {
        T val;
        LLNode next;
        LLNode prev;

        LLNode() {
            val = null;
            next = this;
            prev = this;
        }
        LLNode(T val, LLNode next, LLNode prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public LinkedListDeque() {
        currentSize = 0;
        sentinel = new LLNode();
    }

    // Get deque size.
    @Override
    public int size() {
        return currentSize;
    }

    // Add item at first of deque (first valid item is sentinel.next)
    @Override
    public void addFirst(T item) {
        sentinel.next = new LLNode(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        currentSize += 1;
    }

    // Add item at last of deque
    @Override
    public void addLast(T item) {
        sentinel.prev.next = new LLNode(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
        currentSize += 1;
    }

    // Iterate deque, find node at index, return node.val
    @Override
    public T get(int index) {
        if (index >= size() || index < 0) {
            return null;
        }

        LLNode p = sentinel;

        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.val;
    }

    private T getHelper(LLNode head, int index) {
        if (index == 0) {
            return head.val;
        }
        return getHelper(head.next, index - 1);
    }
    public T getRecursive(int index) {
        if (index >= size() || index < 0) {
            return null;
        }
        return getHelper(sentinel.next, index);
    }
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        LLNode p = sentinel;
        T removeVal = sentinel.next.val;
        p.next.next.prev = sentinel;
        p.next = p.next.next;
        currentSize -= 1;
        return removeVal;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T removeVal = sentinel.prev.val;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        currentSize -= 1;
        return removeVal;
    }

    @Override
    public void printDeque() {
        LLNode p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            System.out.print(p.val + " ");
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return new LLDIterator();
    }

    private class LLDIterator implements Iterator<T> {
        LLNode p;
        int count;

        LLDIterator() {
            p = sentinel.next;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < size();
        }

        @Override
        public T next() {
            T retVal = p.val;
            p = p.next;
            count += 1;
            return retVal;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Deque) {
            Deque<T> deque2 = (Deque<T>) other;
            if (this.size() != deque2.size()) {
                return false;
            }
//            Iterator<T> iterator1 = iterator();
//            Iterator<T> iterator2 = deque2.iterator();
//            while (iterator1.hasNext()) {
//                if (!iterator1.next().equals(iterator2.next())) {
//                    return false;
//                }
//            }
            int length = size();
            for (int i = 0; i < length; i++) {
                if (!get(i).equals(deque2.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static void main(String[] args) {
        LinkedListDeque<String> linkedListDeque1 = new LinkedListDeque<>();
        for (int i = 0; i < 10; i++) {
            linkedListDeque1.addFirst("1");
        }
        ArrayDeque<String> arr2 = new ArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            arr2.addFirst("1");
        }
        System.out.println(linkedListDeque1.equals(arr2));

        for (String i : linkedListDeque1) {
            System.out.print(i + " ");
        }
    }
}
