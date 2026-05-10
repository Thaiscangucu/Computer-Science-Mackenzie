import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Deque<Tuple<Integer, Integer>> d = new Deque<>(); // Usando Tuple como tipo genérico do Deque
        Deque<Integer> fila1 = new Deque<>();  // Fila de destino 1
        Deque<Integer> fila2 = new Deque<>();  // Fila de destino 2
        Deque<Integer> fila3 = new Deque<>();  // Fila de destino 3

        while (true) {
            System.out.println("1. Ler Pares");
            System.out.println("2. Imprimir Fila");
            System.out.println("3. Desenfileirar e redistribuir para 3 filas");
            System.out.println("4. Sair");

            System.out.println("Insira uma opção: ");
            int entrada = s.nextInt();

            if (entrada == 1) {
                inserirPares(d);
            } else if (entrada == 2) {
                d.imprimirDeque();
            } else if (entrada == 3) {
                desenfilerarTodosRedistribuir(d, fila1, fila2, fila3);
            } else if (entrada == 4) {
                System.out.println("Saindo...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        s.close();
    }

    public static void inserirPares(Deque<Tuple<Integer, Integer>> d) {
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("Insira um identificador (ou -1 para sair): ");
            int id = s.nextInt();
            if (id == -1) break;  // Permitir saída

            System.out.println("Insira um valor associado ao par (ou -1 para sair): ");
            int par = s.nextInt();
            if (par == -1) break;  // Permitir saída
            
            Tuple<Integer, Integer> t = new Tuple<>(id, par);
            d.enqueueRight(t);  // Inserir o par no deque
            System.out.println("Par inserido: " + t);
        }
    }

    public static void desenfilerarTodosRedistribuir(Deque<Tuple<Integer, Integer>> d, Deque<Integer> fila1, Deque<Integer> fila2, Deque<Integer> fila3) {
        while (!d.dIsEmpty()) {
            Tuple<Integer, Integer> t = d.dequeueLeft();  // Desenfileirar do início
            int id = t.getFirst();  // Obter o identificador
            int valor = t.getSecond
            ();  // Obter o valor associado

            // Redistribuir o valor nas filas com base no identificador
            if (id % 3 == 0) {
                fila1.enqueueRight(valor);
                System.out.println("Valor " + valor + " inserido na Fila 1.");
            } else if (id % 3 == 1) {
                fila2.enqueueRight(valor);
                System.out.println("Valor " + valor + " inserido na Fila 2.");
            } else {
                fila3.enqueueRight(valor);
                System.out.println("Valor " + valor + " inserido na Fila 3.");
            }
        }

        if (d.dIsEmpty()) {
            System.out.println("Deque principal esvaziado e valores redistribuídos.");
        }

        // Imprimir o conteúdo das filas após redistribuição
        System.out.println("Conteúdo da Fila 1:");
        fila1.imprimirDeque();
        System.out.println("Conteúdo da Fila 2:");
        fila2.imprimirDeque();
        System.out.println("Conteúdo da Fila 3:");
        fila3.imprimirDeque();
    }
}