
import java.util.Random;
import java.util.Scanner;

public class AplicacaoArvoreBST {

   public static void main(String []args){
        
        Arvore ar = new Arvore(); 
        Arvore ar2 = new Arvore();
        Scanner sc = new Scanner(System.in);
        Random r = new Random(7);
        final int AMPLITUDE_SORTEIOS = 100;
        boolean continua = true;
        int opcao, valor, qtd_sorteio;
        do {
            System.out.println("\n1 - insira um elemento");
            System.out.println("2 - insira um sequencia aleatória de n elementos");
            System.out.println("3 - remova um elemento");
            System.out.println("4 - remova um sequencia aleatória de n elementos");
            System.out.println("5 - busque um valor");
            System.out.println("6 - percorra a árvore EmOrdem");
            System.out.println("7 - percorra a árvore PréOrdem");
            System.out.println("8 - percorra a árvore PósOrdem");
            System.out.println("9 - percorra a árvore em largura");
            System.out.println("10 - sucessor de um elemento");
            System.out.println("11 - antecessor de um elemento");
            System.out.println("12 - ancestrais de um elemento");
            System.out.println("13 - exibir a quantidade de nós da árvore");
            System.out.println("14 - exibir árvore");
            System.out.println("15 - exibir árvore em ordem decrescente");
            System.out.println("16 - inverter nós de uma árvore");
            System.out.println("17 - excluir árvore");
            System.out.println("18 - clonar árvore");
            System.out.println("19 - sair");
            System.out.println("20 - contar nós iterativo");
            System.out.print("\nDigite sua opção: ");
            opcao = sc.nextInt();
            switch(opcao) {
                case 1:
                    System.out.print("Digite o valor a ser inserido na árvore: ");
                    valor = sc.nextInt();
                    ar.inserir(valor);
                    System.out.print("Árvore resultante: ");
                    ar.emOrdem();
                    break;
                case 2:
                    System.out.print("Digite a quantidade de números a serem sorteados para a inserção: ");
                    qtd_sorteio = sc.nextInt();
                    long inicio = System.currentTimeMillis();
                    for(int i = 0; i < qtd_sorteio; i++)
                        ar.inserir(r.nextInt(AMPLITUDE_SORTEIOS));
                    long fim = System.currentTimeMillis(); // Marca o fim
                    System.out.println("Tempo de execução: " + (fim - inicio) + " ms");
                    System.out.print("Árvore resultante: ");
                    ar.emOrdem();                    
                    break;
                case 3:
                    System.out.print("Digite o valor a ser removido na árvore: ");
                    valor = sc.nextInt();
                    ar.raiz = Arvore.remover(ar.raiz, valor);
                    System.out.print("\nÁrvore resultante: ");
                    ar.emOrdem();
                    break;
                case 4:
                    int sorteio = 0;
                    System.out.print("Digite a quantidade de números a serem sorteados para remoção: ");
                    qtd_sorteio = sc.nextInt();
                    System.out.print("\nValores sorteados: ");
                    for(int i = 0; i < qtd_sorteio; i++) {
                        sorteio = r.nextInt(AMPLITUDE_SORTEIOS);
                        System.out.print(sorteio + " ");
                        ar.raiz = Arvore.remover(ar.raiz, sorteio);
                    }
                    System.out.print("\nÁrvore resultante: ");
                    ar.emOrdem();
                    break;
                case 5:
                    No noBusca;
                    System.out.print("Digite o valor a ser encontrado na árvore: ");
                    valor = sc.nextInt();
                    noBusca = ar.buscar(valor);
                    if(noBusca != null) 
                        System.out.println("Achou o valor " + noBusca.valor);
                    else
                        System.out.println("NÃO Achou o valor " + valor);
                    break;
                case 6: 
                    ar.emOrdem();
                    break;
                case 7:
                    ar.preOrdem();
                    break;
                case 8:
                    ar.posOrdem();
                    break;
                case 9:
                    ar.emLargura();
                    break;
                case 10:
                    No sucessor;
                    System.out.print("Digite o nó que deseja conhecer seu sucessor: ");
                    opcao = sc.nextInt();
                    sucessor = ar.sucessor(opcao);
                    if(sucessor != null)
                        System.out.print("o sucessor de " + opcao + "é " + sucessor.valor);
                    else
                        System.out.print("o nó de valor " + opcao + "não possui sucessor");
                    break;
                case 11: // antecessor
                    No antecessor;
                    System.out.print("Digite o nó que deseja conhecer seu antecessor: ");
                    opcao = sc.nextInt();
                    antecessor = ar.antecessor(opcao);
                    if(antecessor != null)
                        System.out.print("o antesucessor de " + opcao + "é " + antecessor.valor);
                    else
                        System.out.print("o nó de valor " + opcao + "não possui antecessor");
                    break;
                case 12:
                    System.out.print("Digite o elemento que deseja exibir seus ancestrais: ");
                    opcao = sc.nextInt();
                    ar.ancestrais(opcao);
                    break;
                case 13:
                    System.out.println("A árvore possui " + ar.quantidadedenos() + " de nós");
                    break;
                case 14:
                    ar.exibirArvore();
                    break;
                case 15:
                    ar.emOrdemDec();
                    break; 
                case 16:
                    ar.inverteNos();
                    break;    
                case 17:
                    ar.excluirArvore();
                    break;
                case 18:
                    ar2.raiz = ar.copiar();
                    System.out.println("ARVORE ORIGINAL");
                    ar.exibirArvore();
                    System.out.println("ARVORE CÓPIA");
                    ar2.exibirArvore();
                    break;
                case 19:
                    continua = false;
                    break;
                case 20:
                    System.out.println("A árvore possui " + ar.quantidadeNosIterativo() + " de nós");
                    break;
                default:
                    System.out.println("\nDigite uma opção válida!\n");
            }            
        } while(continua);
    }
    
}
