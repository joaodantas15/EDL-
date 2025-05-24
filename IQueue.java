public interface IQueue<T> {
    void enqueue(T element); // Adiciona um elemento ao final da fila
    T dequeue();             // Remove e retorna o elemento do início da fila
    T peek();                // Retorna o elemento do início da fila sem removê-lo
    int size();              // Retorna o número de elementos na fila
    boolean isEmpty();       // Verifica se a fila está vazia
    void clear();            // Remove todos os elementos da fila
    void print();            // Imprime os elementos da fila
}

