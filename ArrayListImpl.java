public class ArrayListImpl<T> implements Lista<T> {
    private Object[] array;
    private int count;
    private static final int INITIAL_CAPACITY = 10;

    public ArrayListImpl() {
        array = new Object[INITIAL_CAPACITY];
        count = 0;
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void add(T element) {
        if (count == array.length) {
            expandCapacity();
        }
        array[count++] = element;
    }

    private void expandCapacity() {
        Object[] newArray = new Object[array.length * 2];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }

    @Override
    public boolean remove(T element) {
        for (int i = 0; i < count; i++) {
            if (array[i].equals(element)) {
                for (int j = i; j < count - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[--count] = null;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T element) {
        for (int i = 0; i < count; i++) {
            if (array[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void printList() {
        System.out.print("ArrayList: ");
        for (int i = 0; i < count; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
