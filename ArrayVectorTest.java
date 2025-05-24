public class ArrayVectorTest {
    public static void main(String[] args) {
        System.out.println("--- Testing ArrayVector ---");
        ArrayVector<String> myVector = new ArrayVector<>();

        System.out.println("Is empty? " + myVector.isEmpty()); // verdadeiro

        myVector.add("Apple");
        myVector.add("Banana");
        myVector.add("Orange");
        myVector.add("Grape");
        myVector.add("Peach");
        myVector.print(); // [maca, nanana, laranja, usa, pera]
        System.out.println("Size: " + myVector.size()); // 5

        myVector.add(1, "Pineapple");
        myVector.print(); // [maca, abacaxi, banana, laranja, uva, pera]
        System.out.println("Size: " + myVector.size()); // 6

        System.out.println("Element at index 2: " + myVector.get(2)); // banana

        System.out.println("Index of 'Orange': " + myVector.indexOf("Orange")); // 4
        System.out.println("Contains 'Apple'? " + myVector.contains("Apple")); // verdadeiro
        System.out.println("Contains 'Kiwi'? " + myVector.contains("Kiwi")); // falso

        String removed = myVector.remove(0);
        System.out.println("Removed from index 0: " + removed); // maca
        myVector.print(); // [abacaxi, banana, laranja, uva, pera]
        System.out.println("Size: " + myVector.size()); // 5

        System.out.println("Remove 'Grape': " + myVector.remove("Grape")); // verdade
        myVector.print(); // [abacaxi, banana, laranja, pera]
        System.out.println("Size: " + myVector.size()); // 4

        System.out.println("Remove 'Kiwi': " + myVector.remove("Kiwi")); // falso
        myVector.print();

        myVector.clear();
        System.out.println("After clearing:");
        myVector.print(); // []
        System.out.println("Size: " + myVector.size()); // 0
        System.out.println("Is empty? " + myVector.isEmpty()); // verdadeiro

        // testanto redimencionar 
        ArrayVector<Integer> numbers = new ArrayVector<>();
        for (int i = 0; i < 15; i++) {
            numbers.add(i);
        }
        numbers.print();
        System.out.println("Size of numbers: " + numbers.size());
    }
}

