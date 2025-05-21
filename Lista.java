public interface Lista<T> {
    boolean isEmpty();
    int size();
    void add(T element);
    boolean remove(T element);
    boolean contains(T element);
    void printList();
}

