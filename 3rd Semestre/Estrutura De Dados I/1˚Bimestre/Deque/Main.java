import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        
        System.out.print("Digite uma frase: ");
        String frase = s.nextLine();
        
        Deque<Character> deque = new ArrayDeque<>();
        
        // Separando a frase letra por letra e armazenando no Deque
        for (char letra : frase.toCharArray()) {
            deque.addLast(letra);  
        }
        
        // Imprimindo a frase invertida letra por letra
        System.out.print("Frase invertida: ");
        while (!deque.isEmpty()) {
            System.out.print(deque.removeLast());  // Remove do final e imprime
        }
        
        // Fechando o scanner
        s.close();
    }
}