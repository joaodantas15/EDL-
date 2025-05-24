package com.yourpackage;

import java.util.EmptyStackException;

public class TwoColorStackTest {
    public static void main(String[] args) {
        System.out.println("--- Testando a Pilha de Duas Cores ---");
        TwoColorStack<String> stack = new TwoColorStack<>();

        // Testando se está vazia inicialmente
        System.out.println("Pilha vermelha vazia? " + stack.isRedEmpty());
        System.out.println("Pilha preta vazia? " + stack.isBlackEmpty());
        stack.printStacks();

        // --- Testando Adições (push) ---
        System.out.println("\n--- Adicionando elementos ---");
        stack.pushRed("Red1");
        stack.pushRed("Red2");
        stack.pushBlack("Black1");
        stack.pushRed("Red3");
        stack.pushBlack("Black2");
        stack.printStacks(); // Capacidade: 10, Total Ocupado: 5

        // Adicionando mais para forçar o redimensionamento
        stack.pushRed("Red4");
        stack.pushRed("Red5");
        stack.pushRed("Red6");
        stack.pushBlack("Black3");
        stack.pushBlack("Black4");
        stack.printStacks(); // Capacidade: 10, Total Ocupado: 10 (cheio)

        // Este pushRed deve forçar a duplicação para 20
        stack.pushRed("Red7");
        stack.printStacks(); // Capacidade: 20, Total Ocupado: 11

        stack.pushBlack("Black5");
        stack.pushBlack("Black6");
        stack.pushBlack("Black7");
        stack.pushRed("Red8");
        stack.printStacks();

        System.out.println("\nTopo Vermelho: " + stack.peekRed());   // Red8
        System.out.println("Topo Preto: " + stack.peekBlack());     // Black7
        System.out.println("Tamanho Pilha Vermelha: " + stack.sizeRed());
        System.out.println("Tamanho Pilha Preta: " + stack.sizeBlack());

        // --- Testando Remoções (pop) e Redução ---
        System.out.println("\n--- Removendo elementos ---");
        System.out.println("Pop Red: " + stack.popRed()); // Red8
        stack.printStacks();

        System.out.println("Pop Black: " + stack.popBlack()); // Black7
        stack.printStacks();

        System.out.println("Pop Red: " + stack.popRed());   // Red7
        System.out.println("Pop Red: " + stack.popRed());   // Red6
        System.out.println("Pop Red: " + stack.popRed());   // Red5
        System.out.println("Pop Red: " + stack.popRed());   // Red4
        stack.printStacks(); // Pilhas juntas 5+4 = 9 elementos. Capacidade 20. 9 <= 20/3 (6.66) -> NÃO REDUZ.
                            // Espera, 9 > 6.66. Então não deveria reduzir.
                            // Mas se continuar removendo...

        System.out.println("Pop Red: " + stack.popRed()); // Red3. Total: 8 elementos.
        stack.printStacks();

        System.out.println("Pop Black: " + stack.popBlack()); // Black6. Total: 7 elementos.
        stack.printStacks();

        System.out.println("Pop Black: " + stack.popBlack()); // Black5. Total: 6 elementos.
        stack.printStacks(); // Capacidade 20, Total Ocupado 6. 6 <= 20/3 (6.66). DEVE REDUZIR para 10.

        System.out.println("Pop Red: " + stack.popRed()); // Red2. Total: 5 elementos.
        stack.printStacks();

        System.out.println("Pop Black: " + stack.popBlack()); // Black4. Total: 4 elementos.
        stack.printStacks(); // Capacidade 10, Total Ocupado 4. 4 <= 10/3 (3.33) -> NÃO REDUZ.
                            // Opa, 4 > 3.33. Não deveria reduzir para 5. Mas se for 3 elementos...

        System.out.println("Pop Black: " + stack.popBlack()); // Black3. Total: 3 elementos.
        stack.printStacks(); // Capacidade 10, Total Ocupado 3. 3 <= 10/3 (3.33). DEVE REDUZIR para 5.

        System.out.println("Pop Red: " + stack.popRed());   // Red1. Total: 2 elementos.
        stack.printStacks();

        System.out.println("Pop Black: " + stack.popBlack()); // Black2. Total: 1 elemento.
        stack.printStacks(); // Capacidade 5, Total Ocupado 1. 1 <= 5/3 (1.66). DEVE REDUZIR para INITIAL_CAPACITY (10) ou 2 (se não tivesse a regra inicial)

        // Testando erro de pilha vazia
        try {
            stack.popRed(); // Red1. Total: 0.
            stack.printStacks();
            stack.popRed(); // Tentando remover de uma pilha vermelha vazia
        } catch (EmptyStackException e) {
            System.out.println("Exceção esperada: " + e.getMessage());
        }

        try {
            stack.popBlack(); // Tentando remover de uma pilha preta vazia
        } catch (EmptyStackException e) {
            System.out.println("Exceção esperada: " + e.getMessage());
        }
        stack.printStacks(); // Deve estar com as duas pilhas vazias
        System.out.println("Pilha vermelha vazia? " + stack.isRedEmpty());
        System.out.println("Pilha preta vazia? " + stack.isBlackEmpty());
    }
}