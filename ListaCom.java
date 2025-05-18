public class ListaCom{

    private class Node {
        int value;
        Node prev, next;

        Node(int value) {
            this.value = value;
        }
    }

    private Node sentinel;

    public ListaCom() {
        sentinel = new Node(0); 
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public void insertAtBeginning(int value) {
        Node newNode = new Node(value);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
    }

    public void insertAtEnd(int value) {
        Node newNode = new Node(value);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
    }

    public void display() {
        Node current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}
