#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

int main() {
    FILE *arq, *saida;
    char c, prox;

    arq = fopen("codigo_fonte.c", "r");
    saida = fopen("codigo_limpo.c", "w");

    if (arq == NULL || saida == NULL) {
        printf("Erro ao abrir os arquivos!\n");
        return 1;
    }

    while ((c = fgetc(arq)) != EOF) {
        // 1. Checa se iniciou um comentário de múltiplas linhas: /*
        if (c == '/') {
            prox = fgetc(arq);
            if (prox == '*') {
                // ESTADO: Dentro do comentário. Pula tudo até achar */
                while ((c = fgetc(arq)) != EOF) {
                    if (c == '*') {
                        prox = fgetc(arq);
                        if (prox == '/') break; // Fim do comentário
                    }
                }
                continue; // Volta para o início do loop principal
            } else {
                // Não era comentário, devolve o próximo caractere e escreve o '/'
                fputc(c, saida);
                ungetc(prox, arq); 
            }
        } 
        // 2. Se não for comentário, escreve no arquivo de saída
        else {
            fputc(c, saida);
        }
    }

    fclose(arq);
    fclose(saida);
    printf("Arquivo limpo gerado com sucesso!\n");
    return 0;
}