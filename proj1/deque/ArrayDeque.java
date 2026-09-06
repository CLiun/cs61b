package deque;

import afu.org.checkerframework.checker.oigj.qual.O;

import java.util.Iterator;

/**
 * Java modulo operator %, may return a negative number.
 * Use Math.floorMod(x, y) instead.
 * Circular array, need calculate current first item.
 * First item index is nextFirst + 1 % length
 * i-th item use index as offset based on first item.
 * need modular length.
 */
public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int currSize;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        currSize = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize(int newSize) {
        T[] newItems = (T[]) new Object[newSize];
        for (int i = 0; i < size(); i++) {
            newItems[i] = get(i);
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size();
    }

    @Override
    public void addFirst(T item) {
        // Consider resizing later.
        if (size() == items.length) {
            resize(size() * 2);
        }
        items[nextFirst] = item;
        // Case out of boundry
        nextFirst = Math.floorMod(nextFirst - 1, items.length);
        currSize += 1;
    }

    @Override
    public void addLast(T item) {
        if (size() == items.length) {
            resize(size() * 2);
        }
        items[nextLast] = item;
        // Case next last out of boundry
        nextLast = Math.floorMod(nextLast + 1, items.length);
        currSize += 1;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (items.length > 8 && (items.length / size()) > 4) {
            resize(items.length / 2);
        }

        nextFirst = Math.floorMod(nextFirst + 1, items.length);
        currSize -= 1;
        return items[nextFirst];
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (items.length > 8 && (items.length / size()) > 4) {
            resize(items.length / 2);
        }

        nextLast = Math.floorMod(nextLast - 1, items.length);
        currSize -= 1;
        return items[nextLast];
    }

    @Override
    public int size() {
        return currSize;
    }

    @Override
    public T get(int index) {
        if (index >= size() || index < 0) {
            return null;
        }
        int firstIndex = Math.floorMod(nextFirst + 1, items.length);
        int offsetIndex = Math.floorMod(firstIndex + index, items.length);
        return items[offsetIndex];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    private int changeIndex(int i) {
        return ((i % items.length) + items.length) % items.length;
    }
    public Iterator<T> iterator() {
        return new ADIterator();
    }
    private class ADIterator implements Iterator<T>{

        private int nextIndex;

        public ADIterator() {
            nextIndex = 0;
        }
        @Override
        public boolean hasNext() {
            return nextIndex < size();
        }

        @Override
        public T next() {
            T retVal = get(nextIndex);
            nextIndex += 1;
            return retVal;
        }
    }

    @Override
    public boolean equals(Object o1) {
        if (this == o1) {
            return true;
        }
        if (o1 instanceof Deque deque1) {
            if (this.size() != deque1.size()) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if (get(i) != deque1.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static void main(String[] args) {
        ArrayDeque<Integer> arr1 = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            arr1.addFirst(i);
        }
        ArrayDeque<Integer> arr2 = new ArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            arr2.addFirst(i);
        }
        System.out.println(arr1.equals(arr2));
        Iterator<Integer> iterator = arr1.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next()+ " ");
        }

    }

}
