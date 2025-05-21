public class SequenceTest {
    public static void main(String[] args) {
        Sequence<Integer> sequence = new Sequence<>();

        System.out.println("Inserting elements...");
        sequence.insertAtEnd(10);
        sequence.insertAtEnd(20);
        sequence.insertAtBeginning(5);
        sequence.insertAt(1, 15); 
        sequence.printSequence(); 

        System.out.println("Element at index 2: " + sequence.getAt(2)); 

        System.out.println("Removing from index 1: " + sequence.removeAt(1)); 
        sequence.printSequence(); 

        System.out.println("Current size: " + sequence.getSize()); 
    }
}

