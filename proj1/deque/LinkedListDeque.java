package deque;

/*
initialize a deque with a sentinel node.
The first node is sentinel.next
The last node is sentinel.prev
size() return the item number of deque(sentinel exclusive),
 */
public class LinkedListDeque<T> {
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

    LinkedListDeque() {
        currentSize = 0;
        sentinel = new LLNode();
    }

    // Get deque size.
    public int size() {
        return currentSize;
    }

    // Check if deque is empty.
    public boolean isEmpty() {
        return currentSize == 0;
    }

    // Add item at first of deque (first valid item is sentinel.next)
    public void addFirst(T item) {
        sentinel.next = new LLNode(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        currentSize += 1;
    }

    // Add item at last of deque
    public void addLast(T item) {
        sentinel.prev.next = new LLNode(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
        currentSize += 1;
    }

    // Iterate deque, find node at index, return node.val
    public T get(int index) {
        if (index >= size()) {
            return null;
        }

        LLNode p = sentinel;

        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.val;
    }

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

    public void printDeque() {
        LLNode p = sentinel;
        while (p.next != sentinel) {
            p = p.next;
            System.out.println(p.val);
        }
    }
    public static void main(String[] args) {
        LinkedListDeque<Integer> LLDeque1 = new LinkedListDeque<>();
        LLDeque1.size();
    }
}
