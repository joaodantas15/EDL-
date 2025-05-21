public class Main {
    public static void main(String[] args) {
        System.out.println("=== Testing ArrayList Implementation ===");
        Lista<Integer> arrayList = new ArrayListImpl<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.printList();
        arrayList.remove(2);
        arrayList.printList();
        System.out.println("Contains 3? " + arrayList.contains(3));
        System.out.println("Is empty? " + arrayList.isEmpty());
        System.out.println("Size: " + arrayList.size());

        System.out.println("\n=== Testing DoublyLinkedList Implementation ===");
        Lista<String> linkedList = new DoublyLinkedListImpl<>();
        linkedList.add("apple");
        linkedList.add("banana");
        linkedList.add("cherry");
        linkedList.printList();
        linkedList.remove("banana");
        linkedList.printList();
        System.out.println("Contains apple? " + linkedList.contains("apple"));
        System.out.println("Is empty? " + linkedList.isEmpty());
        System.out.println("Size: " + linkedList.size());
    }
}
