import java.util.Arrays;
import java.util.Objects; 

public class ArrayVector<T> implements IVector<T> {
    private static final int INITIAL_CAPACITY = 10;
    private T[] elements;
    private int currentSize; 

    public ArrayVector() {
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        this.currentSize = 0;
    }


    @Override
    public void add(T element) {
        ensureCapacity();
        elements[currentSize] = element;
        currentSize++;
    }

    // O(n)
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        ensureCapacity();
        // move elementos para direita para espaço
        for (int i = currentSize; i > index; i--) {
            elements[i] = elements[i - 1];
        }
        elements[index] = element;
        currentSize++;
    }

    // O(n)
    @Override
    public T remove(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        T removedElement = elements[index];
        // move elementos para esquerda para prencher
        for (int i = index; i < currentSize - 1; i++) {
            elements[i] = elements[i + 1];
        }
        elements[currentSize - 1] = null; 
        currentSize--;
        return removedElement;
    }

    // O(n)
    @Override
    public boolean remove(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (Objects.equals(elements[i], element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    // O(1)
    @Override
    public T get(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return elements[index];
    }

    // O(n)
    @Override
    public int indexOf(T element) {
        for (int i = 0; i < currentSize; i++) {
            if (Objects.equals(elements[i], element)) {
                return i;
            }
        }
        return -1; // Elemento nao encontrardo
    }

    // O(n)
    @Override
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    // O(1)
    @Override
    public int size() {
        return currentSize;
    }

    // O(1)
    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    // O(1)
    @Override
    public void clear() {
        // reinicializa o array para capacidade inicial 
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        this.currentSize = 0;
    }

    // O(n) 
    @Override
    public void print() {
        System.out.print("[");
        for (int i = 0; i < currentSize; i++) {
            System.out.print(elements[i]);
            if (i < currentSize - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    
    // O(n)
    private void ensureCapacity() {
        if (currentSize == elements.length) {
            int newCapacity = elements.length * 2;
            elements = Arrays.copyOf(elements, newCapacity);
            System.out.println("Array resized to: " + newCapacity); 
        }
    }
}