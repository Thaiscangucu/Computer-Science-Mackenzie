
import java.util.Scanner;

// 1.	Escreva um programa que leia uma string e indique se o texto informado é ou não é
// um palíndromo. Seu programa deve usar uma pilha para auxiliar na verificação do palíndromo. EX: Mussum.

class Main{
  public static void main(String args[]){
      //System.out.println("  texto sem   espaços em  branco".trim().replaceAll("\\s+", ""));
      Scanner scanner = new Scanner(System.in);
      String texto = scanner.nextLine();
      System.out.println(texto);
    
  }
}
