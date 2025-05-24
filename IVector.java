public interface IVector<T> {
    void add(T element);
    void add(int index, T element);
    T remove(int index);
    boolean remove(T element);
    T get(int index);
    int indexOf(T element);
    boolean contains(T element);
    int size();
    boolean isEmpty();
    void clear();
    void print();
}