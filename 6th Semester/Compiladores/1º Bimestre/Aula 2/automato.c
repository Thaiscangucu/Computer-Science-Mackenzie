#include <stdio.h>
#include <stdbool.h>

/**
 * Implementação do autômato que aceita palavras com um número par de 'a's.
 */
bool automatoComGotos(const char *palavra) {
    int i = 0;

q0: // Estado PAR (Inicial e de Aceitação)
    if (palavra[i] == '\0') return true;  // Palavra acabou em q0 -> ACEITA
    
    if (palavra[i] == 'a') {
        i++;
        goto q1; // Leu 'a', vai para ÍMPAR
    } else if (palavra[i] == 'b') {
        i++;
        goto q0; // Leu 'b', continua em PAR
    } else {
        return false; // Caractere inválido
    }

q1: // Estado ÍMPAR
    if (palavra[i] == '\0') return false; // Palavra acabou em q1 -> REJEITA
    if (palavra[i] == 'a') {
        i++;
        goto q0; // Leu 'a', volta para PAR
    } else if (palavra[i] == 'b') {
        i++;
        goto q1; // Leu 'b', continua em ÍMPAR
    } else {
        return false; // Caractere inválido
    }
}

int main() {
    char teste[100];
    printf("Digite a palavra (alfabeto {a, b}): ");
    scanf("%s", teste);

    if (automatoComGotos(teste)) {
        printf("Resultado: ACEITA (Par de a's)\n");
    } else {
        printf("Resultado: REJEITADA (Ímpar ou inválida)\n");
    }

    return 0;
}