package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> dequeConparator;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        dequeConparator = c;
    }

    public T max() {
        T tempMax = get(0);
        for (int i = 0; i < size(); i++) {
            T currItem = get(i);
            if (dequeConparator.compare(tempMax, currItem) < 0) {
                tempMax = currItem;
            }
        }
        return tempMax;
    }

    public T max(Comparator<T> c) {
        T tempMax = get(0);
        for (int i = 0; i < size(); i++) {
            T currItem = get(i);
            if (c.compare(tempMax, currItem) < 0) {
                tempMax = currItem;
            }
        }
        return tempMax;
    }
}
