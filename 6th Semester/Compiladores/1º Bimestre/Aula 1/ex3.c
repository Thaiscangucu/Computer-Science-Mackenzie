//Codifique um programa (em C) que leia um código fonte em C e 
//apresente o seu conteúdo na tela, porém
//modificando todas as palavras reservadas da linguagem para maiúsculo.

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
#include <ctype.h>

int main()
{
    FILE *arq;
    char c;
    char C;
    
    arq = fopen("codigo_fonte.c", "r");

    // 2. Verificar se o arquivo abriu corretamente
    if (arq == NULL) {
        printf("Erro ao abrir o arquivo! Verifique se o caminho esta correto.\n");
        return 1;
    }

    // Lê o arquivo caractere por caractere até o fim (EOF)
    while ((c = fgetc(arq)) != EOF) {
        C = toupper(c);
        // printf("%c", c);
        printf("%c", C);
    }

    // 4. Fechar o arquivo
    fclose(arq);
    return 0;
}