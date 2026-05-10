// Universidade Presbiteriana Mackenzie
// 16 de Agosto de 2024, São Paulo
// Ciência da Computação
// Projeto e Análise de Algoritmos

//Nome: Thais Ferreira Canguçu - RA: 10403283
//Nome: Pedro Moniz Canto - RA: 10426546

#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_WORDS 10000

// Função para ler palavras do arquivo
int ler_palavras(char *filename, char *palavras[]) {
    FILE *file = fopen(filename, "r");
    if (!file) {
        perror("Erro ao abrir o arquivo");
        return 0;
    }

    int count = 0;
    char buffer[1024];
    while (fscanf(file, "%1023s", buffer) != EOF && count < MAX_WORDS) {
        palavras[count] = strdup(buffer);
        count++;
    }

    fclose(file);
    return count;
}

// Função para escrever palavras no arquivo
void escrever_palavras(char *filename, char *palavras[], int n) {
    FILE *file = fopen(filename, "w");
    if (!file) {
        perror("Erro ao abrir o arquivo");
        return;
    }

    for (int i = 0; i < n; i++) {
        fprintf(file, "%s\n", palavras[i]);
    }

    fclose(file);
}

// Função Mostrar os Arrays
void mostrar(char *v[], int n) {
    printf("[ ");
    for (int i = 0; i < n; i++) {
        printf("%s ", v[i]);
    }
    printf("]\n");
}

// Função para mesclar dois subarrays de strings
void mesclar(char *v[], int n, char *vetor1[], int n1, char *vetor2[], int n2) {
    int i = 0, i1 = 0, i2 = 0;

    while (i1 < n1 && i2 < n2) {
        if (strcmp(vetor1[i1], vetor2[i2]) > 0) {
            v[i] = vetor2[i2];
            i2++;
        } else {
            v[i] = vetor1[i1];
            i1++;
        }
        i++;
    }

    while (i1 < n1) {
        v[i] = vetor1[i1];
        i++;
        i1++;
    }

    while (i2 < n2) {
        v[i] = vetor2[i2];
        i++;
        i2++;
    }
}

// Merge Sort 
void merge_sort(char *v[], int n, int *passos) {
    if (n <= 1) {
        return;
    }

    int n1 = n / 2;
    int n2 = n - n1;

    char *vetor1[n1];
    char *vetor2[n2];

    for (int i = 0; i < n1; i++) {
        vetor1[i] = v[i];
    }
    for (int i = 0; i < n2; i++) {
        vetor2[i] = v[n1 + i];
    }

    merge_sort(vetor1, n1, passos);
    merge_sort(vetor2, n2, passos);

    mesclar(v, n, vetor1, n1, vetor2, n2);
    (*passos)++;
}

// Insertion Sort
void insertion_sort(char *v[], int n, int *passos) {
    for (int i = 1; i < n; i++) {
        char *chave = v[i];
        int j = i - 1;

        while (j >= 0 && strcmp(v[j], chave) > 0) {
            v[j + 1] = v[j];
            j--;
            (*passos)++;
        }
        v[j + 1] = chave;
        (*passos)++;
    }
}

int main(void) {
    char *palavras[MAX_WORDS];
    int n = ler_palavras("in.txt", palavras);

    printf("Array desordenado:\n");
    mostrar(palavras, n);

    int passos_insertion = 0;
    int passos_merge = 0;

    remove("out1.txt");
    remove("out2.txt");

    // Ordenar usando Insertion Sort
    insertion_sort(palavras, n, &passos_insertion);
    escrever_palavras("out1.txt", palavras, n);

    // Ler novamente as palavras para ordenar com Merge Sort
    n = ler_palavras("in.txt", palavras);

    // Ordenar usando Merge Sort
    merge_sort(palavras, n, &passos_merge);
    escrever_palavras("out2.txt", palavras, n);

    printf("\n");
    
    printf("Array ordenado:\n");
    mostrar(palavras, n);

    printf("Passos Insertion Sort: %d\n", passos_insertion);
    printf("Passos Merge Sort: %d\n", passos_merge);

    return 0;
}
