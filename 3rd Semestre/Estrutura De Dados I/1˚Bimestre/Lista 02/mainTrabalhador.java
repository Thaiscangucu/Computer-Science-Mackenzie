// Exercício 3

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class mainTrabalhador {
    public static void main(String[] args) {
        // Cria Map com as chaves (Nome, Salário etc.) e os valores inseridos pelo usuário.
        Map<String, Trabalhador> trabalhadores = new HashMap<>();
        Scanner s = new Scanner(System.in);
        System.out.print("Número de trabalhadores: ");
        int N_trabalhadores = s.nextInt();
        s.nextLine();
        
        if (N_trabalhadores < 50) {
            // Laço for para receber dados inseridos pelo usuário.
            for (int i = 0; i < N_trabalhadores; i++) {
                Trabalhador ti = new Trabalhador();
                
                System.out.print("Nome do Trabalhador " + (i + 1) + ": ");
                ti.nome = s.nextLine();
                
                System.out.print("Salário do Trabalhador " + (i + 1) + ": ");
                ti.salario = s.nextFloat();  
                s.nextLine(); 
                
                System.out.print("CPF do Trabalhador " + (i + 1) + ": ");
                ti.cpf = s.nextLine();
                
                System.out.print("Idade do Trabalhador " + (i + 1) + ": ");
                ti.idade = s.nextInt();
                s.nextLine();
                
                System.out.print("Sexo do Trabalhador " + (i + 1) + " (M/F): ");
                ti.sexo = s.nextLine().charAt(0);
                
                System.out.print("Endereço do Trabalhador " + (i + 1) + ": ");
                ti.endereco = s.nextLine();
                
                trabalhadores.put(ti.cpf, ti); // Armazena dados no Map.
            }
        }

        // Calcula a média dos salários de todos os trabalhadores.
        float somaSalarios = 0;
        int contador = 0;
        Trabalhador mulherMenorSalario = null;

        for (Trabalhador t : trabalhadores.values()) {
            somaSalarios += t.salario;
            contador++;

            // Verifica a mulher com menor salário.
            if (t.sexo == 'F' && (mulherMenorSalario == null || t.salario < mulherMenorSalario.salario)) {
                mulherMenorSalario = t;
            }
        }

        if (contador > 0) {
            float mediaSalarios = somaSalarios / contador;
            // Mostra a média dos salários de todos os trabalhadores.
            System.out.println("\nA média dos salários é: " + mediaSalarios + "\n");
            
            // Mostra dados dos homens com salário acima da média total.
            System.out.println("Homens com salário acima da média:");
            for (Trabalhador t : trabalhadores.values()) {
                if (t.sexo == 'M' && t.salario > mediaSalarios) {
                    System.out.println("Nome: " + t.nome);
                    System.out.println("Salário: " + t.salario);
                    System.out.println("CPF: " + t.cpf);
                    System.out.println("Idade: " + t.idade);
                    System.out.println("Endereço: " + t.endereco);
                    System.out.println("");
                }
            }

            // Mostra os dados da mulher com menor salário, se existir. 
            if (mulherMenorSalario != null) {
                System.out.println("Mulher com menor salário:");
                System.out.println("Nome: " + mulherMenorSalario.nome);
                System.out.println("Salário: " + mulherMenorSalario.salario);
                System.out.println("CPF: " + mulherMenorSalario.cpf);
                System.out.println("Idade: " + mulherMenorSalario.idade);
                System.out.println("Endereço: " + mulherMenorSalario.endereco);
                System.out.println("");
            }
        } else {
            System.out.println("Nenhum trabalhador foi registrado.");
        }

        // Calcula a média dos salários dos homens com idade superior a 30 anos.
        float somaSalarios30 = 0;
        int contador_media_30 = 0;
        int contador_abaixo_media = 0;

        for (Trabalhador t : trabalhadores.values()) {
            if (t.idade >= 30) {
                somaSalarios30 += t.salario;
                contador_media_30++;
            }
        }
        if (contador_media_30 > 0) {
            float mediaSalarios30 = somaSalarios30 / contador_media_30;
            for (Trabalhador t : trabalhadores.values()) {
                if (t.idade > 30 && t.salario < mediaSalarios30) {
                    contador_abaixo_media++;
                }
            }
            // Mostraa média dos salários dos homens com idade superior a 30 anos.
            System.out.println("\nA média dos salários de homens com mais de 30 anos é de: R$" + mediaSalarios30);
            // mostra a quantidade de homens com 30 ou mais com o salário abaixo dessa média.
            System.out.println("\nHomens com 30 anos ou mais com salário abaixo da média: " + contador_abaixo_media);
        }
    }
}