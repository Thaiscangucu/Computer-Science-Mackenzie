//Usando linguagem C implemente um programa que, dado um arquivo contendo 
// código fonte escrito na linguagem C, verifique se as chaves do programa 
// fonte estão aninhadas de forma correta.

#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    char data;
    struct Node* next;
} Node;

// Função para empilhar (push)
void push(Node** top, char val) {
    Node* newNode = (Node*)malloc(sizeof(Node));
    if (!newNode) {
        printf("Erro de memória!\n");
        exit(1);
    }
    newNode->data = val;
    newNode->next = *top;
    *top = newNode;
}

// Função para desempilhar (pop)
char pop(Node** top) {
    if (*top == NULL) return '\0';
    Node* temp = *top;
    char val = temp->data;
    *top = (*top)->next;
    free(temp);
    return val;
}

int main()
{
    FILE *arq;
    char c;
    Node* stack = NULL;

    arq = fopen("codigo_fonte.c", "r");

    // 2. Verificar se o arquivo abriu corretamente
    if (arq == NULL) {
        printf("Erro ao abrir o arquivo! Verifique se o caminho esta correto.\n");
        return 1;
    }

    // Lê o arquivo caractere por caractere até o fim (EOF)
    while ((c = fgetc(arq)) != EOF) {

        if (c == '{') {
            push(&stack, '{');
        } 
        else if (c == '}') {
            if (stack == NULL) {
                printf("Erro: Chave de fechamento '}' sem abertura.\n");
                fclose(arq);
                return 0;
            }
            pop(&stack);
        }
    }

    if (stack == NULL) {
        printf("Sucesso: As chaves estao balanceadas corretamente.\n");
    } else {
        printf("Erro: Ha chaves '{' que nao foram fechadas ate o fim do arquivo.\n");
    }

    // 4. Fechar o arquivo
    fclose(arq);
    return 0;
}