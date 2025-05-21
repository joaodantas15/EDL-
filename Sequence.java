// Node of the doubly linked list
class Node<T> {
    T data;
    Node<T> prev;
    Node<T> next;

    Node(T data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }
}

// Abstract Data Type: Sequence using Doubly Linked List
public class Sequence<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public Sequence() {
        head = null;
        tail = null;
        size = 0;
    }

    public void insertAtBeginning(T element) {
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public void insertAtEnd(T element) {
        Node<T> newNode = new Node<>(element);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void insertAt(int index, T element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Invalid index");

        if (index == 0) {
            insertAtBeginning(element);
        } else if (index == size) {
            insertAtEnd(element);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node<T> newNode = new Node<>(element);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    public T removeAt(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index");

        Node<T> current;

        if (index == 0) {
            current = head;
            head = head.next;
            if (head != null)
                head.prev = null;
            else
                tail = null;
        } else if (index == size - 1) {
            current = tail;
            tail = tail.prev;
            if (tail != null)
                tail.next = null;
            else
                head = null;
        } else {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.prev.next = current.next;
            current.next.prev = current.prev;
        }

        size--;
        return current.data;
    }

    public T getAt(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid index");

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public int getSize() {
        return size;
    }

    public void printSequence() {
        Node<T> current = head;
        System.out.print("Sequence: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
