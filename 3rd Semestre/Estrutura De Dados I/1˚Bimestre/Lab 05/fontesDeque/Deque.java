public class Deque<T> {
    private static final int TAM_DEQUE = 5;
    private int inicio, fim, qtde;
    private T[] e; // Array para armazenar elementos do tipo genérico T

    // Construtor que Inicia o Deque d como vazio e tamanho padrão
    public Deque() {
        this(TAM_DEQUE);
    }

    // Construtor que Inicia o Deque d como vazio e tamanho informado
    public Deque(int tamanho) {
        this.inicio = 0;
        this.fim = 0;
        this.qtde = 0;
        this.e = (T[]) new Object[tamanho]; // Criação do array genérico
    }

    // Verifica se o Deque d está vazio
    public boolean dIsEmpty() {
        return qtde == 0;
    }

    // Verifica se o Deque d está cheio
    public boolean dIsFull() {
        return qtde == TAM_DEQUE;
    }

    // Obtém o elemento do início do Deque
    public T getLeft() {
        if (!dIsEmpty()) {
            return e[inicio];
        } else {
            System.out.println("deque empty");
            return null;
        }
    }

    // Obtém o elemento do fim do deque D
    public T getRight() {
        if (!dIsEmpty()) {
            if (fim == 0)
                return e[TAM_DEQUE - 1];
            else
                return e[fim - 1];
        } else {
            System.out.println("deque empty");
            return null;
        }
    }

    // Insere no “início-1” do Deque D
    public void enqueueLeft(T obj) {
        if (!dIsFull()) {
            if (inicio == 0) {
                this.e[TAM_DEQUE - 1] = obj;
                inicio = TAM_DEQUE - 1;
            } else {
                this.e[--inicio] = obj;
            }
            qtde++;
        } else {
            System.out.println("deque overflow");
        }
    }

    // Insere no “fim” do Deque D
    public void enqueueRight(T obj) {
        if (!dIsFull()) {
            this.e[fim++] = obj;
            fim = fim % TAM_DEQUE;
            qtde++;
        } else {
            System.out.println("deque overflow");
        }
    }

    // Remove e retorna um elemento do início do Deque d
    public T dequeueLeft() {
        T aux;
        if (!dIsEmpty()) {
            aux = e[inicio];
            inicio = ++inicio % TAM_DEQUE;
            qtde--;
            return aux;
        } else {
            System.out.println("deque underflow");
            return null;
        }
    }

    // Remove e retorna um elemento do final do Deque d
    public T dequeueRight() {
        T aux;
        if (!dIsEmpty()) {
            if (fim == 0) {
                aux = e[TAM_DEQUE - 1];
                fim = TAM_DEQUE - 1;
            } else {
                aux = e[fim - 1];
                fim--;
            }
            qtde--;
            return aux;
        } else {
            System.out.println("deque underflow");
            return null;
        }
    }

    // Retorna o total de elementos armazenados no deque
    public int size() {
        return qtde;
    }

    @Override
    public String toString() {
        int indiceNovo = (inicio + qtde) % e.length;

        StringBuilder sb = new StringBuilder();
        sb.append("[Deque] quantidade: ")
          .append(qtde)
          .append(", capacidade: ")
          .append(e.length);
        if (qtde != 0) {
            sb.append(", primeiro (Esquerda): ")
              .append(getLeft())
              .append(", último (Direita): ")
              .append(getRight());
        }

        sb.append("\nConteúdo do Deque: [ ");
        if (qtde != 0) {
            if (indiceNovo <= inicio) {
                for (int i = inicio; i < e.length; ++i)
                    sb.append("[").append(e[i]).append("] ");
                for (int i = 0; i < indiceNovo; ++i)
                    sb.append("[").append(e[i]).append("] ");
            } else {
                for (int i = inicio; i < indiceNovo; ++i)
                    sb.append("[").append(e[i]).append("] ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public void imprimirDeque() {
        if (dIsEmpty()) {
            System.out.println("Deque está vazio.");
            return;
        }

        int indiceNovo = (inicio + qtde) % e.length;

        System.out.print("Conteúdo do Deque: [ ");
        if (indiceNovo <= inicio) {
            // Caso onde o deque está "circular"
            for (int i = inicio; i < e.length; ++i) {
                System.out.print(e[i] + " ");
            }
            for (int i = 0; i < indiceNovo; ++i) {
                System.out.print(e[i] + " ");
            }
        } else {
            // Caso onde o deque está "linear"
            for (int i = inicio; i < indiceNovo; ++i) {
                System.out.print(e[i] + " ");
            }
        }
        System.out.println("]");
    }
}