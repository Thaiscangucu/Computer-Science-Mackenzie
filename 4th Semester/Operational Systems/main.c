/*
Universidade Presbiteriana Mackenzie - Ciência da Computação
Sistemas Operacionais
Projeto 2 - Simulador de Paginação

Integrantes: 
Nome: Fabio Oliveira da Silva   RA: 10420458
Nome: Patrick Rocha de Andrade  RA: 10410902
Nome: Thais Ferreira Canguçu    RA: 10403283
*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct {
    int presente; // 1 se a página está na memória , 0 caso contrário
    int frame; // Número do frame onde a página está alocada (-1 se não alocada )
    int modificada; // 1 se a página foi modificada , 0 caso contrário
    int referenciada; // 1 se a página foi referenciada recentemente , 0 caso contrário
    int tempo_carga; // Instante em que a página foi carregada na memória
    int ultimo_acesso; // Instante do último acesso à página
    int endereco_virtual; // Endereço virtual criado assim que o processo é criado
} Pagina;

//Estrutura criada para facilitar o acesso a memoria e localizar a página
typedef struct{
    int processo;
    int pagina;
    int tempo_carga;
}PaginaEmMemoria;

typedef struct {
    int pid; // Identificador do processo
    int tamanho; // Tamanho do processo em bytes
    int num_paginas; // Número de páginas do processo
    Pagina * tabela_paginas; // Tabela de páginas do processo
} Processo;

typedef struct {
    int num_frames; // Número total de frames na memória física
    int frames_usados; //Número de frames ocupados para facilitar inserções
    PaginaEmMemoria * frames; // Array de frames (cada elemento contém o pid, a página e o tempo que foi carregado)
} MemoriaFisica;

typedef struct {
    int tempo_atual; // Contador de tempo da simulação
    int tamanho_pagina; // Tamanho da página em bytes
    int tamanho_memoria_fisica; // Tamanho da memória física em bytes
    int num_processos; // Número de processos na simulação
    Processo * processos; // Array de processos
    MemoriaFisica memoria; // Memória física
    // Estatísticas
    int total_acessos; // Total de acessos à memória
    int page_faults; // Total de page faults ocorridos
    // Algoritmo de substituição atual
    int algoritmo; // 0= FIFO , 1=LRU , 2= CLOCK , 3= RANDOM , 4= CUSTOM
} Simulador;

//Calcula o enderço físico de uma página
int endereco_fisico(int frame, int end_virtual){
    return (frame*1000) + (end_virtual % 1000);
}

// Verifica se uma página está presente na memória física
int verificar_pagina_presente ( Simulador *sim , int pid , int endereco_virtual ){
    int end = (endereco_virtual / 1000) - 1;
    if (sim->processos[pid-1].tabela_paginas[end].presente == 1)
        return sim->processos[pid-1].tabela_paginas[end].frame;
    else
        return -1;
}

//Procura frame livre quando a memória não está cheia
int procura_espaco(PaginaEmMemoria memoria[]){
    for(int i = 0 ; i < 4 ; i++){
        if(memoria[i].processo == 0) return i;
    }
}

//Procura frame a ser substituido por FIFO
int FIFO(PaginaEmMemoria memoria[]) {
    int menor = memoria[0].tempo_carga;
    int ind = 0;
    for (int i = 1; i < 4; i++) {
        if (memoria[i].tempo_carga < menor) {
            menor = memoria[i].tempo_carga;
            ind = i;
        }
    }
    return ind;
}

//Substituição de páginas a partir de um indice
void substitui_pagina(Simulador *sim, int indice, int pagina, int processo){
    int pid_subs = sim->memoria.frames[indice].processo;
    int pag_subs = sim->memoria.frames[indice].pagina;

    printf("Substituindo a página %d do processo %d pela página %d do processo %d\n", pag_subs, pid_subs, pagina, processo);

    //Atualiza a tabela de páginas do processo
    sim->processos[processo-1].tabela_paginas[pagina].presente = 1;
    sim->processos[processo-1].tabela_paginas[pagina].frame = indice;
    sim->processos[processo-1].tabela_paginas[pagina].modificada = sim->tempo_atual;
    sim->processos[processo-1].tabela_paginas[pagina].referenciada = sim->tempo_atual;
    sim->processos[processo-1].tabela_paginas[pagina].tempo_carga = sim->tempo_atual;
    sim->processos[processo-1].tabela_paginas[pagina].ultimo_acesso = sim->tempo_atual;

    //Atualiza a tabela de páginas do processo que foi substituído
    sim->processos[pid_subs-1].tabela_paginas[pag_subs].presente = 0;
    sim->processos[pid_subs-1].tabela_paginas[pag_subs].frame = -1;
    sim->processos[pid_subs-1].tabela_paginas[pag_subs].modificada = 0;
    sim->processos[pid_subs-1].tabela_paginas[pag_subs].referenciada = 0;
    sim->processos[pid_subs-1].tabela_paginas[pag_subs].tempo_carga = -1;
    sim->processos[pid_subs-1].tabela_paginas[pag_subs].ultimo_acesso = sim->tempo_atual;

    //Atualiza a memória física
    sim->memoria.frames[indice].processo = processo;
    sim->memoria.frames[indice].pagina = pagina;
    sim->memoria.frames[indice].tempo_carga = sim->tempo_atual;
}

void adiciona_pagina(Simulador *sim, int indice, int pagina, int processo){
    //Atualiza a memória física
    sim->memoria.frames_usados++;
    sim->memoria.frames[indice].processo = processo;
    sim->memoria.frames[indice].pagina = pagina;
    sim->memoria.frames[indice].tempo_carga = sim->tempo_atual;

    //Atualiza a tabela de páginas do processo
    sim->processos[processo-1].tabela_paginas[pagina].presente = 1;
    sim->processos[processo-1].tabela_paginas[pagina].frame = indice;
    sim->processos[processo-1].tabela_paginas[pagina].modificada = 0;
    sim->processos[processo-1].tabela_paginas[pagina].referenciada = 0;
    sim->processos[processo-1].tabela_paginas[pagina].tempo_carga = sim->tempo_atual;
    sim->processos[processo-1].tabela_paginas[pagina].ultimo_acesso = -1;
}

void criar_processo(Simulador *sim){
    Processo novo;
    //Cria o pid e atualiza o contador de processos do simulador
    novo.pid = sim->num_processos + 1; 
    sim->num_processos++;

    //Define os tamanhos do processo
    novo.tamanho = 16; //16KB padrão da simulação
    novo.num_paginas = 4; //4 páginas com 4KB cada, padrão da simulação

    //Preenchendo as páginas
    novo.tabela_paginas = (Pagina*)malloc(sizeof(Pagina) * 4);
    //Inicializa todas as páginas
    for (int i = 1; i <= 4; i++) {
        novo.tabela_paginas[i-1].presente = 0;
        novo.tabela_paginas[i-1].frame = -1;
        novo.tabela_paginas[i-1].modificada = 0;
        novo.tabela_paginas[i-1].referenciada = 0;
        novo.tabela_paginas[i-1].tempo_carga = -1;
        novo.tabela_paginas[i-1].ultimo_acesso = -1;
        novo.tabela_paginas[i-1].endereco_virtual = (i * 1000) + (rand() % 1000); //Cria um endereço virtual para a pagina
    }

    //Realoca o vetor de processos do simulador
    sim->processos = (Processo *) realloc(sim->processos, sizeof(Processo) * sim->num_processos);
    // Copia o novo processo para o vetor
    sim->processos[sim->num_processos - 1] = novo;
}

Simulador* criar_simulador(int tamanho_pagina, int tamanho_memoria_fisica, int algoritmo){
    //Cria o simulador
    Simulador *sim = (Simulador * )malloc(sizeof(Simulador));

    //Configurações base
    sim->tempo_atual = 0;
    sim->tamanho_pagina = tamanho_pagina; //KB
    sim->tamanho_memoria_fisica = tamanho_memoria_fisica; //KB
    sim->num_processos = 0;
    sim->processos = NULL;

    //Criando Memória Física
    sim->memoria.num_frames = 4; //4 frames
    sim->memoria.frames_usados = 0; 
    sim->memoria.frames = (PaginaEmMemoria *)malloc(sizeof(PaginaEmMemoria) * sim->memoria.num_frames); //Cria a memória com 4 espaços

    //Algoritmo de substituição usado na simulação
    sim->algoritmo = algoritmo;

    //Estatisticas
    sim->page_faults = 0;
    sim->total_acessos = 0;

    //Retorna o simulador
    return sim;
}

//Mostra o estado atual da memória física
void mostrar_memoria_fisica(Simulador sim){
    printf("\n--------\n");
    for(int i = 0; i < 4 ; i++){
        if(sim.memoria.frames[i].processo != 0 ) printf("| P%d-%d |\n--------\n", sim.memoria.frames[i].processo, sim.memoria.frames[i].pagina);
        else printf("|      |\n--------\n");
    }
    printf("\n");
}

//Mostra as estatísticas da simulação
void mostrar_estatisticas(Simulador sim){
    printf("\n======== ESTATÍSTICAS DA SIMULAÇÃO ========\n");
    printf("Total de acessos à memória: %d\n", sim.total_acessos);
    printf("Total de page faults: %d/20\n", sim.page_faults);
    printf("Taxa de page faults: %.2f%%\n", (sim.page_faults/20.0)*100);
}

//Função para rodar o simulador
void executar_simulacao(Simulador* sim){
    printf("\n--- NOVA SIMULAÇÃO ---\n");

    printf("Criando Processos \n");
    for(int i = 1 ; i < 4 ; i++){
        criar_processo(sim);
        printf("Processo %d criado: 4 páginas (16384 bytes)\n", i);
    }

    //Especificações da execução
    printf("\nTamanho da página: 4096 bytes (4 KB)\n");
    printf("Tamanho da memória física: 16384 bytes (16 KB)\n");
    printf("Número de frames: 4\n");
    if(sim->algoritmo == 0) printf("Algoritmo de substituição: FIFO\n");
    else printf("Algoritmo de substituição: RANDOM\n");
    printf("Processos ativos: 3\n");

    printf("\nIniciando carregamento em memória...\n\n");

    //Execução
    for(int i = 0 ; i < 5 ; i++){ //Os 3 primeiros serão para alocar todas as páginas, os 2 restantes serão testes com páginas aleatórias
        for(int j = 0 ; j < 4 ; j++){ //Número de paginas em cada um
            int processo;
            int pagina;
            int end_virtual;

            //Testes após todos estarem alocados ao menos uma vez
            if(i>=3){
                printf("TODOS OS PROCESSOS ALOCADOS AO MENOS UMA VEZ\n");
                printf("Escolhendo processos aleatórios por teste...\n");

                processo = (rand() % 3) + 1; //Escolhe processo aleatório
                pagina = rand() % 4; //Escolhe página aleatória
                end_virtual = sim->processos[processo-1].tabela_paginas[pagina].endereco_virtual;
            }
            else{   
                processo = sim->processos[i].pid;
                end_virtual = sim->processos[i].tabela_paginas[j].endereco_virtual;
                pagina = j;
            }

            sim->tempo_atual++;
            int ocupacao_memoria = sim->memoria.frames_usados;
            
            printf("Tempo t = %d : ", sim->tempo_atual);
            printf("Processo %d acessa o endereço virtual %d\n", processo, end_virtual);

            //Verifica page fault
            if(verificar_pagina_presente(sim,processo,end_virtual) == -1){
                printf("PAGE FAULT: Página %d do processo %d não está em memória física\n", pagina, processo);
                printf("Carregando página em memória...\n");
                
                //Aumenta a taxa de page faults
                sim->page_faults++;

                //frame que será substituido por um algoritmo ou alocado
                int indice_frame;

                //Caso a memória esteja cheia
                if(ocupacao_memoria == 4){
                    if(sim->algoritmo == 0){
                        indice_frame = FIFO(sim->memoria.frames);
                        sim->total_acessos++;
                    }
                    else{
                        indice_frame = rand() % 4;
                        sim->total_acessos++;
                    }
                    substitui_pagina(sim, indice_frame, pagina, processo);
                    sim->total_acessos++;
                }

                //Caso a memória não esteja cheia
                else{
                    indice_frame = procura_espaco(sim->memoria.frames);
                    adiciona_pagina(sim, indice_frame, pagina, processo);
                    sim->total_acessos++;
                }
                sim->total_acessos++;

                int frame = sim->processos[processo - 1].tabela_paginas[pagina].frame;

                //Endereço fisico
                int end_fisico = endereco_fisico(frame, end_virtual);
                
                //Dados do tempo
                printf("Endereço virtual: %d ->", end_virtual);
                printf(" Página: %d ->", pagina);
                printf(" Frame: %d ->", end_virtual);
                printf(" Endereço Físico: %d\n", end_fisico);

            }
            else{
                printf("Página %d do processo %d já carregada em memória\n", pagina, processo);
                sim->processos[processo - 1].tabela_paginas[pagina].ultimo_acesso = sim->tempo_atual;
                sim->total_acessos++;

            }
            printf("\nEstado da memória física:\n");
            mostrar_memoria_fisica(*sim);   
        }
    }

    //Mostra as estatísticas
    mostrar_estatisticas(*sim);
}

int main(){
    srand(time(NULL)); //Utilizado pelo random e para criar os endereços virtuais

    Simulador *sim = criar_simulador(4, 16, 0); //Simula com FIFO
    executar_simulacao(sim);

    printf("\n===============================================================\n");

    sim = criar_simulador(4, 16, 3); //Simula com RANDOM
    executar_simulacao(sim);
    
    //Libera o simulador
    free(sim);
    return 0;
}