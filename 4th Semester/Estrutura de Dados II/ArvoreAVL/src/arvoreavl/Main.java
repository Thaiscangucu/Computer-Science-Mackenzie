
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvoreavl;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String []args){
        
        ArvoreAVL avl = new ArvoreAVL(); 
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        final int AMPLITUDE_SORTEIOS = 800;
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
            System.out.println("9 - percorra a árvore em Largura");
            System.out.println("10 - percorra a árvore em ordem reversa");
            System.out.println("11 - sucessor de um elemento");
            System.out.println("12 - antecessor de um elemento");
            System.out.println("13 - próximo elemeento (NÃO IMPLEMENTADO)");
            System.out.println("14 - elemento anterior (NÃO IMPLEMENTADO)");        
            System.out.println("15 - ancestrais de um elemento (NÃO IMPLEMENTADO)");
            System.out.println("16 - quantidade de nós da árvore (recursivo)");
            System.out.println("17 - quantidade de nós da árvore (iterativo)");
            System.out.println("18 - exibir árvore");
            System.out.println("19 - menor elemento");
            System.out.println("20 - maior elemento");
            System.out.println("21 - excluir árvore");
            System.out.println("22 - sair\n");
            System.out.print("\nDigite sua opção: ");
            opcao = sc.nextInt();
            switch(opcao) {
                case 1:
                    System.out.print("Digite o valor a ser inserido na árvore: ");
                    valor = sc.nextInt();
                    avl.inserir(valor);
                    System.out.print("Árvore resultante: ");
                    avl.emOrdem();
                    break;
                case 2:
                    System.out.print("Digite a quantidade de números a serem sorteados para a inserção: ");
                    qtd_sorteio = sc.nextInt();
                    for(int i = 0; i < qtd_sorteio; i++)
                        avl.inserir(r.nextInt(AMPLITUDE_SORTEIOS));
                    System.out.print("Árvore resultante: ");
                    avl.emOrdem();                    
                    break;
                case 3:
                    System.out.print("Digite o valor a ser removido na árvore: ");
                    valor = sc.nextInt();
                    avl.remover(valor);
                    System.out.print("Árvore resultante: ");
                    avl.emOrdem();
                    break;
                case 4:
                    int sorteio;
                    System.out.print("Digite a quantidade de números a serem sorteados para remoção: ");
                    qtd_sorteio = sc.nextInt();
                    System.out.print("\nValores sorteados: ");
                    for(int i = 0; i < qtd_sorteio; i++) {
                        sorteio = r.nextInt(AMPLITUDE_SORTEIOS);
                        System.out.print(sorteio + " ");
                        avl.remover(sorteio);
                    }
                    System.out.print("\nÁrvore resultante: ");
                    avl.emOrdem();
                    break;
                case 5:
                    No noBusca;
                    System.out.print("Digite o valor a ser encontrado na árvore: ");
                    valor = sc.nextInt();
                    noBusca = avl.buscar(valor);
                    if(noBusca != null) 
                        System.out.println("Achou o valor " + noBusca.valor);
                    else
                        System.out.println("NÃO Achou o valor " + valor);
                    break;
                case 6: 
                    System.out.println("Visitação Em Ordem = \n");
                    avl.emOrdem();
                    break;
                case 7:
                    System.out.println("Visitação Pré Ordem = \n");
                    avl.preOrdem();
                    break;
                case 8:
                    System.out.println("Visitação Pós Ordem = \n");
                    avl.posOrdem();
                    break;
                case 9:
                    System.out.println("Visitação em largura = \n" + avl.emLargura());
                    break;
                case 10: 
                    System.out.println("Visitação Pós Orde Reversa = \n");
                    avl.emOrdemReversa();
                    break;
                case 11:
                    int x;
                    No sucessor;
                    System.out.print("Digite o nó que deseja conhecer seu sucessor: ");
                    x = sc.nextInt();
                    sucessor = avl.sucessor(x);
                    if(sucessor != null)
                        System.out.print("o sucessor de " + x + " é " + sucessor.valor);
                    else
                        System.out.print("o nó de valor " + x + " não possui sucessor");
                    break;
                case 12: // antecessor
                    int y;
                    No antecessor;
                    System.out.print("Digite o nó que deseja conhecer seu antecessor: ");
                    x = sc.nextInt();
                    antecessor = avl.antecessor(x);
                    if(antecessor != null)
                        System.out.print("o antecessor de " + x + " é " + antecessor.valor);
                    else
                        System.out.print("o nó de valor " + x + " não possui antecessor");
                    break;
                 case 13: // exebie o próximo elemento de um nó na ordem.
                    System.out.print("Digite o elemento que deseja exibir o seu próximo: ");
                    opcao = sc.nextInt();
                    System.out.print("MÉTODO NÃO IMPLEMENTADO!");
                    System.out.println(avl.proximo(opcao));
                    break;
                 case 14: // exebie o elemento anterior de um nó na ordem.
                    System.out.print("Digite o elemento que deseja exibir o seu anterior: ");
                    opcao = sc.nextInt();
                    System.out.print("MÉTODO NÃO IMPLEMENTADO!");
                    System.out.println(avl.anterior(opcao));
                    break;
                case 15:
                    System.out.print("Digite o elemento que deseja exibir seus ancestrais: ");
                    x = sc.nextInt();
                    System.out.print("MÉTODO NÃO IMPLEMENTADO!");
                    avl.ancestrais(x);
                    break;
                case 16:
                    System.out.println("Versão de codificação recursiva...");
                    System.out.println("A árvore possui " + avl.quantidadedenos() + " de nós");
                    break;
                case 17:
                    System.out.println("A árvore possui " + avl.quantidadeNosIterativo() + " nós");
                    break;
                case 18:
                    avl.exibirArvore();
                    break;
                case 19:
                    System.out.println(avl.getMinimo());
                    break;
                case 20:
                    System.out.println(avl.getMaximo());
                    break;
                case 21:
                    avl.excluirArvore();
                    break;
                case 22:
                    continua = false;
                    break;
                default:
                    System.out.println("\nDigite uma opção válida!\n");
            }            
        } while(continua);
    }
}

    
    
