package deque;

/**
 * Java modulo operator %, may return a negative number.
 * Use Math.floorMod(x, y) instead.
 * Circular array, need calculate current first item.
 * First item index is nextFirst + 1 % length
 * i-th item use index as offset based on first item.
 * need modular length.
 */
public class ArrayDeque<T> {
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

    public void addLast(T item) {
        if (size() == items.length) {
            resize(size() * 2);
        }
        items[nextLast] = item;
        // Case next last out of boundry
        nextLast = Math.floorMod(nextLast + 1, items.length);
        currSize += 1;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (items.length > 8 && (items.length / size()) > 4) {
            resize(size() / 2);
        }

        nextFirst = Math.floorMod(nextFirst + 1, items.length);
        currSize -= 1;
        return items[nextFirst];
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (items.length > 8 && (items.length / size()) > 4) {
            resize(size() / 2);
        }

        nextLast = Math.floorMod(nextLast - 1, items.length);
        currSize -= 1;
        return items[nextLast];
    }

    public int size() {
        return currSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T get(int index) {
        if (index >= size()) {
            return null;
        }
        int firstIndex = Math.floorMod(nextFirst + 1, items.length);
        int offsetIndex = Math.floorMod(firstIndex + index, items.length);
        return items[offsetIndex];
    }

    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> arr1 = new ArrayDeque<>();
        System.out.println(Math.floorMod(-1, 3));
    }

}
