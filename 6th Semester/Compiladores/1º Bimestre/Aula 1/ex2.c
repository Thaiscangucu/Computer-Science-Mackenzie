//Crie um programa na linguagem C capaz de ler um arquivo 
//texto contendo código fonte também escrito em C,
// contando o quantidade de ocorrências das: letras maiúsculas (A...Z), 
//letras minúsculas (a..z), dígitos (0...9) e espaços em branco. Ao final, 
//o programa deve apresentar as quantidades em cada categoria na tela.
//Obs: Não é necessário contar caracteres especiais ou acentuados. 
//Caso não haja qualquer ocorrência de um determinado grupo,
// não é necessário imprimir a quantidade 0.

// Implemente um programa em C que leia o conteúdo de um arquivo texto e contabilize a quantidade de linhas que há nele. Ao final, o programa deve imprimir na tela a quantidade total de linhas do arquivo.
#include <stdio.h>
#include <stdlib.h>

int main()
{
    FILE *arq;
    char c;
    int maisculuas = 0;
    int minusculas = 0;
    int digitos = 0;
    int espacos = 0;
    
    // 1. Abrir o arquivo para leitura ("r")
    arq = fopen("codigo_fonte.c", "r");

    // 2. Verificar se o arquivo abriu corretamente
    if (arq == NULL) {
        printf("Erro ao abrir o arquivo! Verifique se o caminho esta correto.\n");
        return 1;
    }

    // Lê o arquivo caractere por caractere até o fim (EOF)
    while ((c = fgetc(arq)) != EOF) {
        // Verificar se o caractere é uma letra maiúscula
        if (c >= 'A' && c <= 'Z') {
            // Incrementar contador de letras maiúsculas
            maisculuas++;
        }
        // Verificar se o caractere é uma letra minúscula
        else if (c >= 'a' && c <= 'z') {
            // Incrementar contador de letras minúsculas
            minusculas++;
        }
        // Verificar se o caractere é um dígito
        else if (c >= '0' && c <= '9') {
            // Incrementar contador de dígitos
            digitos++;
        }
        // Verificar se o caractere é um espaço em branco
        else if (c == ' ') {
            // Incrementar contador de espaços em branco
            espacos++;
        }
    }

    // 4. Fechar o arquivo
    fclose(arq);
    printf("Letras maiúsculas: %d\n", maisculuas);
    printf("Letras minúsculas: %d\n", minusculas);
    printf("Dígitos: %d\n", digitos);
    printf("Espaços em branco: %d\n", espacos);

    return 0;
}