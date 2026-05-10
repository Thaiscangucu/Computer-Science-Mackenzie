#include <stdio.h>
#include <stdbool.h>

/**
 * Implementação do autômato que aceita palavras com um 'a's que não sejam consecutivos.
 */
bool automatoComGotos(const char *palavra) {
    int i = 0;

q0: 
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

q1: 
    if (palavra[i] == '\0') return true; // Palavra acabou em q1 -> REJEITA
    if (palavra[i] == 'a') {
        i++;
        goto q2; 
    } else if (palavra[i] == 'b') {
        i++;
        goto q0; 
    } else {
        return false; // Caractere inválido
    }


q2: // Estado de REJEIÇÃO (qualquer caractere inválido ou 'a' consecutivo)
    if (palavra[i] == '\0') return false; // Palavra acabou em q1 -> REJEITA
    if (palavra[i] == 'a' || palavra[i] == 'b') {
        i++;
        goto q2; // Leu 'a', volta para PAR
    } 
    else {
        return false; // Caractere inválido
    }
}

int main() {
    char teste[100];
    printf("Digite a palavra (alfabeto {a, b}): ");
    scanf("%s", teste);

    if (automatoComGotos(teste)) {
        printf("Resultado: ACEITA (Não existe a's consecutivos)\n");
    } else {
        printf("Resultado: REJEITADA (Presença de a's consecutivos)\n");
    }

    return 0;
}