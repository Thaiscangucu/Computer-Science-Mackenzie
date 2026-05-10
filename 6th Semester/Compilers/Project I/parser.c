#include "parser.h"
#include "lex.h"
#include "opt.h"
// #include "error.h"

static Token lookahead;

static int nivel_trace = 0;

int indentacao = 0; // Controla os espaços para ficar bonito

void entrar_regra(char *nome) {
    if (opts_get()->show_trace) {
        // Imprime espaços para a direita
        for(int i=0; i<indentacao; i++) printf("  "); 
        printf("<%s>\n", nome);
        indentacao++; // Aumenta o recuo para a próxima regra
    }
}

void sair_regra(char *nome) {
    if (opts_get()->show_trace) {
        indentacao--; // Diminui o recuo
        for(int i=0; i<indentacao; i++) printf("  ");
        printf("</%s>\n", nome);
    }
}

void parse_if() {
    entrar_regra("if_stmt");  // Avisa que entrou na regra do IF
    
    check(sIF);
    check(sABRE_PARENTESE);
    parse_expr();
    check(sFECHA_PARENTESE);
    parse_cmd();
    
    if (lookahead.categoria == sELSE) {
        check(sELSE);
        parse_cmd();
    }
    
    sair_regra("if_stmt"); // Avisa que terminou a regra do IF
}

parse_ini(){} // A função principal (ponto de entrada). Verifica o module, chama globais, sub-rotinas e o main.
parse_glob(){} //Processa a seção globals.
parse_subs(){} //Gerencia a lista de funções (fn) e procedimentos (proc).
parse_princ(){} //Processa especificamente o bloco proc main().
parse_bco(){} //Processa um bloco de comandos entre start e end.
parse_decls(){} //Processa as declarações de variáveis (ex: x, y : int;).
parse_tpo(){} //Analisa o tipo (int, bool, char) e se é um vetor (presença de [ ]).
parse_param(){} //Processa a lista de parâmetros dentro dos parênteses de uma função/procedimento.
parse_cmd(){} //A função "roteadora". Ela olha o token atual e decide se chama parse_if, parse_wh, etc.
parse_out(){} //Comando print.
parse_inp(){} //Comando scan.
parse_if(){} //Comando if e o opcional else.
parse_mat(){} //Comando match (incluindo when e otherwise).
parse_fr(){} //Comando for.
parse_wh(){} //Comando loop while.
parse_rpt(){} //Comando loop ... until.
parse_atr(){} //Comando de atribuição (id = expressão).
parse_call(){} //Chamada de procedimento (call).
parse_ret(){} //Comando de retorno (ret).
parse_expr(){} //Nível do OR (V).
parse_exlog(){} //Nível do AND (A).
parse_exrel(){} //Operadores relacionais (=, <>, >, <, etc.).
parse_exari(){} //Adição e Subtração (+, -).
parse_exarp(){} //Multiplicação e Divisão (*, /).
parse_fator(){} //O nível mais alto. Processa constantes, identificadores, parênteses e negações (not, -).
parse_vec(){} // Para tratar acessos a vetores (ex: vetor[i]).
parse_elem(){} //Usado no print para aceitar tanto expressões quanto strings literais.