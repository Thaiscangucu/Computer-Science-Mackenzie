public class PilhaChar {

    private static final int TAM_DEFAULT = 100;  // Tamanho padrão da pilha
    private int topoPilha;
    private char[] elementos;  // Array para armazenar os caracteres

    // Construtor que define o tamanho da pilha
    public PilhaChar(int tamanho) {
        this.elementos = new char[tamanho];
        this.topoPilha = -1;  // Pilha vazia, topo é -1
    }

    // Construtor padrão que usa o tamanho default
    public PilhaChar() {
        this(TAM_DEFAULT);
    }

    // Verifica se a pilha está vazia
    public boolean isEmpty() {
        return this.topoPilha == -1;
    }

    // Verifica se a pilha está cheia
    public boolean isFull() {
        return this.topoPilha == this.elementos.length - 1;
    }

    // Método para empilhar (adicionar) um caractere na pilha
    public void push(char c) {
        if (!this.isFull()) {
            this.elementos[++this.topoPilha] = c;
        } else {
            System.out.println("Overflow - Estouro de pilha");
        }
    }

    // Método para desempilhar (remover) o topo da pilha
    public char pop() {
        if (!this.isEmpty()) {
            return this.elementos[this.topoPilha--];
        } else {
            System.out.println("Underflow - Pilha vazia");
            return '\0';  // Retorna caractere nulo em caso de underflow
        }
    }

    // Método para verificar o topo da pilha sem remover
    public char topo() {
        if (!this.isEmpty()) {
            return this.elementos[this.topoPilha];
        } else {
            System.out.println("Pilha vazia");
            return '\0';
        }
    }

    // Método para obter o número de elementos na pilha
    public int size() {
        return this.topoPilha + 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[PilhaChar] Elementos: [ ");
        for (int i = 0; i <= topoPilha; i++) {
            if (i != topoPilha) {
                sb.append(elementos[i]).append(", ");
            } else {
                sb.append(elementos[i]);
            }
        }
        sb.append(" ]");
        return sb.toString();
    }
}
