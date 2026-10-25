import java.util.Scanner;

public class Main {
    
    // Função para validar as expresões inseridas
    private static boolean validarExpressao(String expressao) {
        expressao = expressao.replaceAll("\\s+", ""); // Remove espaços extras
        
        if (!expressao.matches("[0-9()+\\-*/.]+")) { // Verifica caracteres inválidos (permite apenas números, operadores e parênteses)
            System.out.println("Erro: Expressão contém caracteres inválidos!");
            return false;
        }
        
        if (expressao.matches(".*[+\\-*/]$")) {  // Verifica se não termina com um operador (exemplo: "1+2*")
            System.out.println("Erro: Expressão não pode terminar com um operador!");
            return false;
        }
        
        if (expressao.matches("^[+*/].*")) {  // Verifica se não começa com um operador inválido
            System.out.println("Erro: Expressão não pode começar com +, * ou /!");
            return false;
        }
        
        // Verifica se os parênteses estão balanceados
        int contadorParenteses = 0;
        for (char c : expressao.toCharArray()) {
            if (c == '(') contadorParenteses++;
                if (c == ')') contadorParenteses--;
            if (contadorParenteses < 0) {
                System.out.println("Erro: Parênteses desbalanceados!");
                return false;
            }
        }
        if (contadorParenteses != 0) {
            System.out.println("Erro: Parênteses desbalanceados!");
            return false;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Arvore arvore = new Arvore();
        String expressaoInfixa = "";
        
        while (true) {
            System.out.println("1. Entrada da expressão aritmética na notação infixa.");
            System.out.println("2. Criação da árvore binária de expressão aritmética.");
            System.out.println("3. Exibição da árvore binária de expressão aritmética.");
            System.out.println("4. Cálculo da expressão (realizando o percurso da árvore).");
            System.out.println("5. Encerramento do programa.");
            System.out.println("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            
            switch (opcao) {
            case 1:
                System.out.print("Insira uma expressão aritmética em notação infixa: ");
                expressaoInfixa = scanner.nextLine();  // Recebe a expressão infixa
                // Se for inválida volta a ser vazia
                if (!validarExpressao(expressaoInfixa)) {
                    expressaoInfixa = "";
                    break;
                }
                // Se for válida mostra a expressão inserida
                System.out.println("Expressão inserida: " + expressaoInfixa);
                break;
            case 2:
                // Verifica se uma expressao foi inserida antes de criar uma árvore
                if(expressaoInfixa == ""){
                    System.out.println("Erro! Insira uma expressão aritmética para criar uma árvore");
                    break;
                }
                else{
                    // Converte a expressão para pós fixa e cria a árvore
                    String posfixa = Converter.converter(expressaoInfixa);
                    arvore.inserir(posfixa);
                    System.out.println("Árvore de expressão criada: ");
                    arvore.exibirArvore();
                    break;
                }
            case 3:
                /* Verifica se existe uma árvore criada antes de exibir e se existir exibe
                 nos formatos pré ordem, em ordem e pós ordem. */
                if(arvore.raiz == null) {
                    System.out.println("Erro: nenhuma árvore criada.");
                    break;
                }
                else{
                    System.out.println("Pré-ordem:");
                    arvore.preOrdem();
                    System.out.println();
                    System.out.println("Em ordem:");
                    arvore.emOrdem();
                    System.out.println();
                    System.out.println("Pós-ordem:");
                    arvore.posOrdem();
                    System.out.println();
                    break;
                }
                
            case 4:
                // Calcula o resultado da expressão
                System.out.println(expressaoInfixa + " = " + arvore.avaliar());
                break;
            case 5:
                System.out.println("Encerrando o programa...");
                scanner.close();
                return;
            default:
                System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
}
