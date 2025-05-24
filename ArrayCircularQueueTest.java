import java.util.EmptyStackException;

public class ArrayCircularQueueTest {
    public static void main(String[] args) {
        System.out.println("--- Testando a Fila Circular com Array ---");
        ArrayCircularQueue<String> queue = new ArrayCircularQueue<>(5); // Capacidade inicial 5

        System.out.println("Fila vazia? " + queue.isEmpty()); // true
        queue.print(); // [] (Capacidade: 5, Itens: 0)

        // --- Teste de Enqueue (Adicionar) e Duplicação ---
        System.out.println("\n--- Adicionando elementos ---");
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E"); // Fila agora cheia (5/5)
        queue.print(); // [A, B, C, D, E] (Capacidade: 5, Itens: 5)

        // Este deve forçar a duplicação do array para 10
        System.out.println("\n--- Forçando Duplicação ---");
        queue.enqueue("F");
        queue.print(); // [A, B, C, D, E, F] (Capacidade: 10, Itens: 6)

        queue.enqueue("G");
        queue.enqueue("H");
        queue.print(); // [A, B, C, D, E, F, G, H] (Capacidade: 10, Itens: 8)

        System.out.println("Tamanho da fila: " + queue.size()); // 8
        System.out.println("Elemento no topo (peek): " + queue.peek()); // A

        // --- Teste de Dequeue (Remover) e Circularidade ---
        System.out.println("\n--- Removendo elementos (dequeue) ---");
        System.out.println("Removido: " + queue.dequeue()); // A
        queue.print(); // [B, C, D, E, F, G, H] (Capacidade: 10, Itens: 7)

        System.out.println("Removido: " + queue.dequeue()); // B
        System.out.println("Removido: " + queue.dequeue()); // C
        queue.print(); // [D, E, F, G, H] (Capacidade: 10, Itens: 5)

        System.out.println("Elemento no topo (peek): " + queue.peek()); // D

        // Adicionando mais para testar circularidade em array não-cheio
        queue.enqueue("I");
        queue.enqueue("J");
        queue.print(); // [D, E, F, G, H, I, J] (Capacidade: 10, Itens: 7)
        // O array interno pode estar assim: [_, _, D, E, F, G, H, I, J, _]
        // Se a duplicação moveu para o início, ele estará como [D, E, F, G, H, I, J, _, _, _]

        // --- Teste de Limpeza (clear) ---
        System.out.println("\n--- Limpando a fila ---");
        queue.clear();
        System.out.println("Fila vazia após clear? " + queue.isEmpty()); // true
        queue.print(); // [] (Capacidade: 5, Itens: 0)

        // --- Teste de Exceções ---
        System.out.println("\n--- Testando exceções ---");
        try {
            queue.dequeue(); // Tenta remover de fila vazia
        } catch (EmptyStackException e) {
            System.out.println("Exceção esperada ao tentar dequeue em fila vazia: " + e.getMessage());
        }

        try {
            queue.peek(); // Tenta espiar fila vazia
        } catch (EmptyStackException e) {
            System.out.println("Exceção esperada ao tentar peek em fila vazia: " + e.getMessage());
        }

        // Teste final de adicionar após limpar
        System.out.println("\n--- Adicionando novamente após clear ---");
        queue.enqueue("X");
        queue.enqueue("Y");
        queue.print(); // [X, Y] (Capacidade: 5, Itens: 2)
        System.out.println("Tamanho da fila: " + queue.size()); // 2
    }
}

