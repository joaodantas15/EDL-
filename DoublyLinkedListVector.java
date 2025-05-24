import java.util.Objects;

public class DoublyLinkedListVector<T> implements IVector<T> {
    private Node<T> head; // aponta ara o primeiro no 
    private Node<T> tail; // aponta para o ultimo no 
    private int currentSize;

    public DoublyLinkedListVector() {
        this.head = null;
        this.tail = null;
        this.currentSize = 0;
    }

    // O(1)
    @Override
    public void add(T element) {
        Node<T> newNode = new Node<>(element);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        currentSize++;
    }

    // O(n)
    @Override
    public void add(int index, T element) {
        if (index < 0 || index > currentSize) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        if (index == currentSize) { // Adicionar no final
            add(element);
            return;
        }
        if (index == 0) { // adicionar no comeco
            Node<T> newNode = new Node<>(element);
            newNode.next = head;
            head.previous = newNode;
            head = newNode;
        } else { // Add in the middle
            Node<T> current = getNode(index); // O(n)
            Node<T> newNode = new Node<>(element);
            newNode.next = current;
            newNode.previous = current.previous;
            current.previous.next = newNode;
            current.previous = newNode;
        }
        currentSize++;
    }

    // O(n)
    @Override
    public T remove(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node<T> nodeToRemove = getNode(index); // O(n)
        return removeNode(nodeToRemove); // O(1) 
    }

    // O(n)
    @Override
    public boolean remove(T element) {
        Node<T> current = head;
        while (current != null) {
            if (Objects.equals(current.element, element)) {
                removeNode(current); // O(1) 
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // O(n)
    @Override
    public T get(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        return getNode(index).element;
    }

    // O(n)
    @Override
    public int indexOf(T element) {
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            if (Objects.equals(current.element, element)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
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
        head = null;
        tail = null;
        currentSize = 0;
    }

    // O(n)
    @Override
    public void print() {
        System.out.print("[");
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.element);
            if (current.next != null) {
                System.out.print(", ");
            }
            current = current.next;
        }
        System.out.println("]");
    }

    
    // O(n)
    private Node<T> getNode(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException("Index out of bounds: " + index);
        }
        Node<T> current;
        
        if (index < currentSize / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
        } else {
            current = tail;
            for (int i = currentSize - 1; i > index; i--) {
                current = current.previous;
            }
        }
        return current;
    }

    
    private T removeNode(Node<T> node) {
        if (node == null) {
            return null; // joga excessao 
        }

        if (node == head) {
            head = node.next;
        }
        if (node == tail) {
            tail = node.previous;
        }
        if (node.previous != null) {
            node.previous.next = node.next;
        }
        if (node.next != null) {
            node.next.previous = node.previous;
        }
        currentSize--;
        node.next = null; 
        node.previous = null; 
        return node.element;
    }
}