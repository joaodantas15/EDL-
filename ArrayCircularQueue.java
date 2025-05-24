
import java.util.EmptyStackException; 
                                    

public class ArrayCircularQueue<T> implements IQueue<T> {
    private static final int INITIAL_CAPACITY = 5; // Capacidade inicial do array
    private T[] elements;
    private int front;      // Índice do primeiro elemento (cabeça da fila)
    private int rear;       // Índice da próxima posição livre (cauda da fila)
    private int currentSize; // Número atual de elementos na fila

    public ArrayCircularQueue() {
        this(INITIAL_CAPACITY);
    }

    public ArrayCircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("A capacidade deve ser maior que zero.");
        }
        this.elements = (T[]) new Object[capacity];
        this.front = 0;       // Início da fila
        this.rear = 0;        // Próxima posição para adicionar
        this.currentSize = 0; // Fila inicialmente vazia
    }

    @Override
    public void enqueue(T element) {
        ensureCapacity(); // Verifica e duplica o array se necessário

        elements[rear] = element; // Adiciona o elemento na posição 'rear'
        rear = (rear + 1) % elements.length; // Move 'rear' circularmente
        currentSize++; // Incrementa o tamanho
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new EmptyStackException(); // Usando uma exceção comum, ou criar QueueEmptyException
        }

        T element = elements[front]; // Pega o elemento do 'front'
        elements[front] = null;      // Libera a referência para o Garbage Collector
        front = (front + 1) % elements.length; // Move 'front' circularmente
        currentSize--; // Decrementa o tamanho
        return element;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[front]; // Retorna o elemento do 'front' sem remover
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public void clear() {
        // Redefine o array e os ponteiros para o estado inicial
        this.elements = (T[]) new Object[INITIAL_CAPACITY];
        this.front = 0;
        this.rear = 0;
        this.currentSize = 0;
    }

    @Override
    public void print() {
        System.out.print("[");
        if (!isEmpty()) {
            int count = 0;
            int current = front;
            while (count < currentSize) {
                System.out.print(elements[current]);
                if (count < currentSize - 1) {
                    System.out.print(", ");
                }
                current = (current + 1) % elements.length;
                count++;
            }
        }
        System.out.println("] (Capacidade: " + elements.length + ", Itens: " + currentSize + ")");
    }

    // --- Métodos de Gerenciamento de Capacidade ---

    // O(N) no pior caso (quando precisa duplicar)
    private void ensureCapacity() {
        if (currentSize == elements.length) { // Se a fila estiver cheia
            int oldCapacity = elements.length;
            int newCapacity = oldCapacity * 2; // Duplica a capacidade
            System.out.println("DEBUG: Fila cheia. Redimensionando de " + oldCapacity + " para " + newCapacity);

            T[] newElements = (T[]) new Object[newCapacity];

            // Copia os elementos para o novo array, mantendo a ordem lógica da fila
            // A cópia precisa lidar com a natureza circular do array antigo
            for (int i = 0; i < currentSize; i++) {
                newElements[i] = elements[(front + i) % oldCapacity];
            }

            this.elements = newElements;  // Atualiza a referência do array
            this.front = 0;               // O 'front' agora é o início do novo array
            this.rear = currentSize;      // O 'rear' é a posição logo após o último elemento
        }
    }
}