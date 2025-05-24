import java.util.EmptyStackException;

public class TwoColorStack<T> {
    private static final int INITIAL_CAPACITY = 10; // Capacidade inicial do array
    private T[] elements;
    private int redTop;   // Índice do topo da pilha vermelha (cresce da esquerda para a direita)
    private int blackTop; // Índice do topo da pilha preta (cresce da direita para a esquerda)

    // Construtor padrão, usa a capacidade inicial
    public TwoColorStack() {
        this(INITIAL_CAPACITY);
    }

    // Construtor que permite definir uma capacidade inicial
    public TwoColorStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }
        // Cria um array de Object e faz um cast para T[]
        this.elements = (T[]) new Object[capacity];
        this.redTop = -1;                     // Pilha vermelha começa vazia (topo antes do primeiro elemento)
        this.blackTop = capacity;             // Pilha preta começa vazia (topo depois do último elemento)
    }

    // --- Operações da Pilha Vermelha (Red Stack) ---

    // Adiciona um elemento à pilha vermelha. O(1) amortizado.
    public void pushRed(T element) {
        ensureCapacity(); // Verifica e duplica o array se necessário
        elements[++redTop] = element; // Incrementa o topo e adiciona o elemento
    }

    // Remove e retorna o elemento do topo da pilha vermelha. O(1) amortizado.
    public T popRed() {
        if (isRedEmpty()) {
            throw new EmptyStackException(); // Lança exceção se a pilha estiver vazia
        }
        T element = elements[redTop];
        elements[redTop--] = null; // Remove a referência para o Garbage Collector
        shrinkIfNeeded(); // Verifica e reduz o array se necessário
        return element;
    }

    // Retorna o elemento do topo da pilha vermelha sem removê-lo. O(1).
    public T peekRed() {
        if (isRedEmpty()) {
            throw new EmptyStackException();
        }
        return elements[redTop];
    }

    // Retorna o número de elementos na pilha vermelha. O(1).
    public int sizeRed() {
        return redTop + 1;
    }

    // Verifica se a pilha vermelha está vazia. O(1).
    public boolean isRedEmpty() {
        return redTop == -1;
    }

    // --- Operações da Pilha Preta (Black Stack) ---

    // Adiciona um elemento à pilha preta. O(1) amortizado.
    public void pushBlack(T element) {
        ensureCapacity(); // Verifica e duplica o array se necessário
        elements[--blackTop] = element; // Decrementa o topo e adiciona o elemento
    }

    // Remove e retorna o elemento do topo da pilha preta. O(1) amortizado.
    public T popBlack() {
        if (isBlackEmpty()) {
            throw new EmptyStackException();
        }
        T element = elements[blackTop];
        elements[blackTop++] = null; // Remove a referência para o Garbage Collector
        shrinkIfNeeded(); // Verifica e reduz o array se necessário
        return element;
    }

    // Retorna o elemento do topo da pilha preta sem removê-lo. O(1).
    public T peekBlack() {
        if (isBlackEmpty()) {
            throw new EmptyStackException();
        }
        return elements[blackTop];
    }

    // Retorna o número de elementos na pilha preta. O(1).
    public int sizeBlack() {
        return elements.length - blackTop;
    }

    // Verifica se a pilha preta está vazia. O(1).
    public boolean isBlackEmpty() {
        return blackTop == elements.length;
    }

    // --- Operações de Gerenciamento de Capacidade e Utilitários ---

    // Retorna o número total de elementos nas duas pilhas. O(1).
    public int totalSize() {
        return sizeRed() + sizeBlack();
    }

    // Retorna a capacidade atual do array interno. O(1).
    public int capacity() {
        return elements.length;
    }

    // Verifica se as pilhas colidiram (array "cheio"). O(1).
    public boolean isFull() {
        return redTop + 1 == blackTop;
    }

    // Método privado para garantir capacidade. O(N) quando há redimensionamento.
    private void ensureCapacity() {
        if (isFull()) {
            int oldCapacity = elements.length;
            int newCapacity = oldCapacity * 2; // Duplica a capacidade
            System.out.println("DEBUG: Array cheio. Redimensionando de " + oldCapacity + " para " + newCapacity);

            T[] newElements = (T[]) new Object[newCapacity];

            // Copia elementos da pilha vermelha para o início do novo array
            // System.arraycopy é eficiente para cópias de arrays
            System.arraycopy(elements, 0, newElements, 0, sizeRed());

            // Copia elementos da pilha preta para o final do novo array
            // Calcula a nova posição inicial do topo da pilha preta
            int newBlackTop = newCapacity - sizeBlack();
            System.arraycopy(elements, blackTop, newElements, newBlackTop, sizeBlack());

            this.elements = newElements;       // Atualiza a referência para o novo array
            this.blackTop = newBlackTop;       // Atualiza o topo da pilha preta
        }
    }

    // Método privado para reduzir o array se a utilização for baixa. O(N) quando há redução.
    private void shrinkIfNeeded() {
        int currentCapacity = elements.length;
        int currentTotalSize = totalSize();

        // Calcula a nova capacidade proposta (metade da atual)
        int newCapacity = currentCapacity / 2;

        // Garante que a nova capacidade não seja menor que a capacidade inicial
        if (newCapacity < INITIAL_CAPACITY) {
            newCapacity = INITIAL_CAPACITY;
        }

        // Condição para redução:
        // 1. Utilização total <= 1/3 da capacidade atual
        // 2. A nova capacidade calculada é suficiente para todos os elementos atuais
        // 3. A nova capacidade é realmente menor que a capacidade atual (para evitar reduções desnecessárias)
        if (currentTotalSize <= currentCapacity / 3 && newCapacity >= currentTotalSize && newCapacity < currentCapacity) {
            System.out.println("DEBUG: Utilização baixa (" + currentTotalSize + "/" + currentCapacity + "). Reduzindo de " + currentCapacity + " para " + newCapacity);

            T[] newElements = (T[]) new Object[newCapacity];

            // Copia elementos da pilha vermelha
            System.arraycopy(elements, 0, newElements, 0, sizeRed());

            // Copia elementos da pilha preta
            int newBlackTop = newCapacity - sizeBlack();
            System.arraycopy(elements, blackTop, newElements, newBlackTop, sizeBlack());

            this.elements = newElements;
            this.blackTop = newBlackTop;
        }
    }

    // Método para imprimir o estado das pilhas e do array interno. O(N).
    public void printStacks() {
        System.out.println("\n--- Estado Atual das Pilhas (Capacidade: " + capacity() + ", Total Ocupado: " + totalSize() + ") ---");
        System.out.print("Array Interno: [");
        for (int i = 0; i < elements.length; i++) {
            // Imprime o elemento ou um '_' se a posição estiver vazia
            System.out.print(elements[i] != null ? elements[i] : "_");
            if (i < elements.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        System.out.println("Topo Vermelho (redTop): " + redTop + " (Elemento: " + (redTop != -1 ? elements[redTop] : "N/A") + ")");
        System.out.println("Topo Preto (blackTop): " + blackTop + " (Elemento: " + (blackTop != elements.length ? elements[blackTop] : "N/A") + ")");
        System.out.println("----------------------------------------------------------------------------------");
    }
}
