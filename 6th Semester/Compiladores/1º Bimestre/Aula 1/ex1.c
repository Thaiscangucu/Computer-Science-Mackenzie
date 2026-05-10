// Implemente um programa em C que leia o conteúdo de um arquivo texto e contabilize a quantidade de linhas que há nele. Ao final, o programa deve imprimir na tela a quantidade total de linhas do arquivo.
#include <stdio.h>

int main()
{
    FILE *arq;
    char linha[100];
    int linhas = 0; 
    // 1. Abrir o arquivo para leitura ("r")
    arq = fopen("arquivo.txt", "r");

    // 2. Verificar se o arquivo abriu corretamente
    if (arq == NULL) {
        printf("Erro ao abrir o arquivo!\n");
        return 1;
    }

    while (fgets(linha, sizeof(linha), arq) != NULL) {
        linhas += 1; 
    }

    // 4. Fechar o arquivo
    fclose(arq);
    
    printf("Linhas no arquivo.txt é de: %d", linhas);

    return 0;
}