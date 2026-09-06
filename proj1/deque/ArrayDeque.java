package deque;

/**
 * Circular array, need calculate current first item.
 * First item index is nextFirst + 1 % length
 * i-th item use index as offset based on first item.
 * need modular length.
 */
public class ArrayDeque<T> {
    T[] items;
    int currSize;
    int nextFirst;
    int nextLast;

    public ArrayDeque() {
        currSize = 0;
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
    }

    private void resize() {
        T[] bigger = (T[]) new Object[size() * 2];
        System.arraycopy(items, 0, bigger, 0, size());
    }

    private void resizeSmaller() {
        T[] smaller = (T[]) new Object[size() / 2];
        for (int i = 0; i < size(); i++) {
            smaller[i] = get(i);
        }
    }
    public void addFirst(T item) {
        // Consider resizing later.
        if (size() == items.length) {
            resize();
        }
        items[nextFirst] = item;
        // Case out of boundry
        nextFirst = (nextFirst - 1) % items.length;
        currSize += 1;
    }

    public void addLast(T item) {
        if (size() == items.length) {
            resize();
        }
        items[nextLast] = item;
        // Case next last out of boundry
        nextLast = (nextLast + 1) % items.length;
        currSize += 1;
    }

    public T removeFirst() {
        if (items.length > 8 && (items.length / size()) > 4) {
            resizeSmaller();
        }

        nextFirst = (nextFirst + 1) % items.length;
        currSize -= 1;
        return items[nextFirst];
    }

    public T removeLast() {
        if (items.length > 8 && (items.length / size()) > 4) {
            resizeSmaller();
        }

        nextLast = (nextLast - 1) % items.length;
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
        int firstIndex = (nextFirst + 1) % items.length;
        int offsetIndex = (firstIndex + index) % items.length;
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

    }


}
