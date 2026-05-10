#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//Nome:  Pedro Moniz Canto             RA: 10426546
//Nome:  Thais Ferreira Canguçu        RA: 10403283

/*
SAYOOD, Khalid. *Introduction to Data Compression*. 5. ed. Cambridge: Morgan Kaufmann, 2017. 
DAVDATA. *LZW Compression Algorithm*. Disponível em: <https://www.davdata.nl/math/lzw.html>. Acesso em: 15 nov. 2024.
KING, K. N. *C Programming: A Modern Approach*. 2. ed. Nova Iorque: W. W. Norton & Company, 2008.
ROSETTA CODE. *LZW Compression*. Disponível em: <https://rosettacode.org/wiki/LZW_compression>. Acesso em: 16 nov. 2024.
 */

// Definição do tamanho máximo do dicionário
#define MAX_DICT_SIZE 4096 // 2^12, tamanho típico para LZW
#define MAX_CHAR 256       
#define MAX_INPUT 4096

// Funções auxiliares para inicializar os dicionários
void inicializarDicionarioCompressao(char **dicionario);
void inicializarDicionarioDescompressao(char **dicionario);

// Funções principais do LZW
void compressaoLZW(char *entrada, int **saida, int *tamanhoSaida);
void descompressaoLZW(int *entrada, int tamanhoEntrada, char **saida);

// Menu principal
void exibirMenu();

// Implementação do LZW
void compressaoLZW(char *entrada, int **saida, int *tamanhoSaida) {
    char *dicionario[MAX_DICT_SIZE];
    inicializarDicionarioCompressao(dicionario);

    int tamanhoDicionario = MAX_CHAR;
    char prefixo[MAX_DICT_SIZE] = {0};
    char simbolo[2] = {0};
    *saida = (int *)malloc(strlen(entrada) * sizeof(int));
    *tamanhoSaida = 0;

    for (int i = 0; entrada[i] != '\0'; i++) {
        simbolo[0] = entrada[i];
        simbolo[1] = '\0';

        char combinacao[MAX_DICT_SIZE];
        snprintf(combinacao, sizeof(combinacao), "%s%s", prefixo, simbolo);

        int encontrado = -1;
        for (int j = 0; j < tamanhoDicionario; j++) {
            if (strcmp(dicionario[j], combinacao) == 0) {
                encontrado = j;
                break;
            }
        }

        if (encontrado != -1) {
            strcpy(prefixo, combinacao);
        } else {
            for (int j = 0; j < tamanhoDicionario; j++) {
                if (strcmp(dicionario[j], prefixo) == 0) {
                    (*saida)[(*tamanhoSaida)++] = j;
                    break;
                }
            }

            if (tamanhoDicionario < MAX_DICT_SIZE) {
                dicionario[tamanhoDicionario] = (char *)malloc(strlen(combinacao) + 1);
                strcpy(dicionario[tamanhoDicionario], combinacao);
                tamanhoDicionario++;
            }

            strcpy(prefixo, simbolo);
        }
    }

    if (strlen(prefixo) > 0) {
        for (int j = 0; j < tamanhoDicionario; j++) {
            if (strcmp(dicionario[j], prefixo) == 0) {
                (*saida)[(*tamanhoSaida)++] = j;
                break;
            }
        }
    }

    for (int i = 0; i < tamanhoDicionario; i++) {
        free(dicionario[i]);
    }
}

void descompressaoLZW(int *entrada, int tamanhoEntrada, char **saida) {
    char *dicionario[MAX_DICT_SIZE];
    inicializarDicionarioDescompressao(dicionario);

    int tamanhoDicionario = MAX_CHAR;
    *saida = (char *)malloc(tamanhoEntrada * MAX_DICT_SIZE * sizeof(char));
    (*saida)[0] = '\0';

    char *stringAnterior = (char *)malloc(MAX_DICT_SIZE * sizeof(char));
    strcpy(stringAnterior, dicionario[entrada[0]]);
    strcat(*saida, stringAnterior);

    for (int i = 1; i < tamanhoEntrada; i++) {
        char *stringAtual;
        if (entrada[i] < tamanhoDicionario) {
            stringAtual = dicionario[entrada[i]];
        } else {
            snprintf(stringAnterior, MAX_DICT_SIZE, "%s%c", stringAnterior, stringAnterior[0]);
            stringAtual = stringAnterior;
        }

        strcat(*saida, stringAtual);

        if (tamanhoDicionario < MAX_DICT_SIZE) {
            char novoItem[MAX_DICT_SIZE];
            snprintf(novoItem, sizeof(novoItem), "%s%c", stringAnterior, stringAtual[0]);
            dicionario[tamanhoDicionario] = (char *)malloc(strlen(novoItem) + 1);
            strcpy(dicionario[tamanhoDicionario], novoItem);
            tamanhoDicionario++;
        }

        strcpy(stringAnterior, stringAtual);
    }

    for (int i = 0; i < tamanhoDicionario; i++) {
        free(dicionario[i]);
    }
    free(stringAnterior);
}

void inicializarDicionarioCompressao(char **dicionario) {
    for (int i = 0; i < MAX_CHAR; i++) {
        dicionario[i] = (char *)malloc(2 * sizeof(char));
        dicionario[i][0] = (char)i;
        dicionario[i][1] = '\0';
    }
}

void inicializarDicionarioDescompressao(char **dicionario) {
    for (int i = 0; i < MAX_CHAR; i++) {
        dicionario[i] = (char *)malloc(2 * sizeof(char));
        dicionario[i][0] = (char)i;
        dicionario[i][1] = '\0';
    }
}

void exibirMenu() {
    int opcao;
    do {
        printf("\nMenu:\n");
        printf("1. Digitar texto e realizar compressão/descompressão\n");
        printf("2. Usar texto pré-definido (*Bee Movie*)\n");
        printf("3. Sair\n");
        printf("Escolha uma opção: ");
        scanf("%d", &opcao);
        getchar(); // Limpa o buffer de entrada

        if (opcao == 1) {
            char entrada[MAX_INPUT];
            printf("Digite o texto para compressão: ");
            fgets(entrada, MAX_INPUT, stdin);
            entrada[strcspn(entrada, "\n")] = '\0';

            int *saidaCompactada = NULL;
            int tamanhoSaida;
            char *saidaDescompactada;

            compressaoLZW(entrada, &saidaCompactada, &tamanhoSaida);

            printf("\nTexto compactado: ");
            for (int i = 0; i < tamanhoSaida; i++) {
                printf("%d ", saidaCompactada[i]);
            }
            printf("\n");

            descompressaoLZW(saidaCompactada, tamanhoSaida, &saidaDescompactada);
            printf("Texto descompactado: %s\n", saidaDescompactada);

            free(saidaCompactada);
            free(saidaDescompactada);

        } else if (opcao == 2) {
            // Roteiro do Bee Movie
            char entrada[] =
                "Segundo todas as leis conhecidas da aviação, uma abelha não deveria ser capaz de voar.\n"
                "Suas asas são pequenas demais para levantar seu corpinho gordo do chão.\n"
                "Mas, é claro, a abelha voa assim mesmo, porque não se importa com o que os humanos acham impossível.\n"
                "\n"
                "Barry: Amarelo, preto. Amarelo, preto. Amarelo, preto. Amarelo, preto. Opa! Preto e amarelo!\n"
                "Adam: Barry, o café está pronto!\n"
                "Barry: Já vou! Só um segundo! Olá? Adam?\n"
                "Adam: Dá pra acreditar que hoje é a formatura?\n"
                "Barry: Nem consigo! Três dias de escola primária, três de ensino médio, três de faculdade. E agora estamos aqui!\n"
                "\n"
                "Dean Buzzwell: Bem-vindos, turma de 9:15 de Nova Colmeia! Hoje vocês começam suas carreiras na Honex!\n";

            int *saidaCompactada = NULL;
            int tamanhoSaida;
            char *saidaDescompactada;

            printf("\nTexto pré-definido: \n%s\n", entrada);

            compressaoLZW(entrada, &saidaCompactada, &tamanhoSaida);

            printf("Texto compactado: ");
            for (int i = 0; i < tamanhoSaida; i++) {
                printf("%d ", saidaCompactada[i]);
            }
            printf("\n");

            descompressaoLZW(saidaCompactada, tamanhoSaida, &saidaDescompactada);
            printf("Texto descompactado: %s\n", saidaDescompactada);

            free(saidaCompactada);
            free(saidaDescompactada);
        }
    } while (opcao != 3);

    printf("Encerrando o programa.\n");
}

int main() {
    exibirMenu();
    return 0;
}
