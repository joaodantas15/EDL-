public class Main {
    public static void main(String[] args) {
        System.out.println("List with sentinel:");
        ListaCom listWithSentinel = new ListaCom();
        listWithSentinel.insertAtBeginning(2);
        listWithSentinel.insertAtEnd(4);
        listWithSentinel.insertAtBeginning(1);
        listWithSentinel.display();

        System.out.println("List without sentinel:");
        ListaSem listWithoutSentinel = new ListaSem();
        listWithoutSentinel.insertAtBeginning(2);
        listWithoutSentinel.insertAtEnd(4);
        listWithoutSentinel.insertAtBeginning(1);
        listWithoutSentinel.display();
    }
}
