public class FilaCircular<T> {
    private static final int TAM_DEFAULT = 100;
    private int inicio, fim, qtd;
    private T e[];

    // Método construtor com parâmetros
    @SuppressWarnings("unchecked")
    public FilaCircular(int tamanho) {
        this.inicio = 0;
        this.fim = 0;
        this.qtd = 0;
        e = (T[]) new Object[tamanho]; // Corrigido
    }

    public FilaCircular() {
        this(TAM_DEFAULT); // Corrigido
    }

    public boolean qIsEmpty() {
        return (qtd == 0);
    }

    public boolean qIsFull() {
        return (qtd == e.length);
    }

    public void enqueue(T elemento) throws Exception { 
        if (!qIsFull()) {
            this.e[this.fim++] = elemento;
            this.fim = this.fim % this.e.length;
            this.qtd++;
        } else {
            throw new Exception("Overflow - Estouro de Fila");
        }
    }

    public T dequeue() throws Exception {
        T aux;
        if (!qIsEmpty()) {
            aux = this.e[this.inicio];
            this.inicio = ++this.inicio % this.e.length;
            this.qtd--;
            return aux;
        } else {
            throw new Exception("underflow - Esvaziamento de Fila"); // Corrigido
        }
    }

    public T front() throws Exception {
        if (!qIsEmpty()) {
            return e[inicio];
        } else {
            throw new Exception("underflow - Esvaziamento de Fila"); // Corrigido
        }
    }

    public T rear() throws Exception {
        if (!qIsEmpty()) {
            int pfinal;
            if (this.fim != 0) pfinal = this.fim - 1;
            else pfinal = this.e.length - 1;
            return this.e[pfinal];
        } else {
            throw new Exception("underflow - Esvaziamento de Fila"); // Corrigido
        }
    }

    public int size() {
        return qtd;
    }

    public void printFila() {
        if (qIsEmpty()) {
            System.out.println("Fila está vazia.");
            return;
        }
        System.out.print("Fila [ ");
        for (int i = 0; i < qtd; i++) {
            int index = (inicio + i) % TAM_DEFAULT;
            System.out.print(e[index] + " ");
        }
        System.out.println("]");
    }
}
