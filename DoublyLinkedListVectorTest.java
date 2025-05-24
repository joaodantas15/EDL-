public class DoublyLinkedListVectorTest {
    public static void main(String[] args) {
        System.out.println("--- Testing DoublyLinkedListVector ---");
        DoublyLinkedListVector<Integer> myVector = new DoublyLinkedListVector<>();

        System.out.println("Is empty? " + myVector.isEmpty()); // true

        myVector.add(10);
        myVector.add(20);
        myVector.add(30);
        myVector.add(40);
        myVector.add(50);
        myVector.print(); // [10, 20, 30, 40, 50]
        System.out.println("Size: " + myVector.size()); // 5

        myVector.add(1, 15);
        myVector.print(); // [10, 15, 20, 30, 40, 50]
        System.out.println("Size: " + myVector.size()); // 6

        myVector.add(0, 5); // Add to the beginning
        myVector.print(); // [5, 10, 15, 20, 30, 40, 50]
        System.out.println("Size: " + myVector.size()); // 7

        myVector.add(7, 55); // Add to the end
        myVector.print(); // [5, 10, 15, 20, 30, 40, 50, 55]
        System.out.println("Size: " + myVector.size()); // 8

        System.out.println("Element at index 3: " + myVector.get(3)); // 20

        System.out.println("Index of '30': " + myVector.indexOf(30)); // 4
        System.out.println("Contains '10'? " + myVector.contains(10)); // true
        System.out.println("Contains '100'? " + myVector.contains(100)); // false

        Integer removed = myVector.remove(0);
        System.out.println("Removed from index 0: " + removed); // 5
        myVector.print(); // [10, 15, 20, 30, 40, 50, 55]
        System.out.println("Size: " + myVector.size()); // 7

        removed = myVector.remove(6); // Remove the last
        System.out.println("Removed from last index: " + removed); // 55
        myVector.print(); // [10, 15, 20, 30, 40, 50]
        System.out.println("Size: " + myVector.size()); // 6

        System.out.println("Remove '30': " + myVector.remove(30)); // true
        myVector.print(); // [10, 15, 20, 40, 50]
        System.out.println("Size: " + myVector.size()); // 5

        System.out.println("Remove '99': " + myVector.remove(99)); // false
        myVector.print();

        myVector.clear();
        System.out.println("After clearing:");
        myVector.print(); // []
        System.out.println("Size: " + myVector.size()); // 0
        System.out.println("Is empty? " + myVector.isEmpty()); // true
    }
}

