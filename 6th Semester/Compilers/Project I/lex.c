#include "lex.h"
#include <ctype.h>
#include <string.h>

static FILE *source_file = NULL;
static int current_line = 1;

// Tabela de palavras reservadas para busca rápida
static struct {
    char *texto;
    TokenType cat;
} keywords[] = {
    {"bool", sBOOL}, {"char", sCHAR}, {"int", sINT}, {"if", sIF},
    {"else", sELSE}, {"while", sWHILE}, {"loop", sLOOP}, {"until", sUNTIL},
    {"for", sFOR}, {"to", sTO}, {"step", sSTEP}, {"do", sDO},
    {"match", sMATCH}, {"when", sWHEN}, {"otherwise", sOTHERWISE},
    {"start", sSTART}, {"end", sEND}, {"globals", sGLOBALS},
    {"locals", sLOCALS}, {"fn", sFN}, {"proc", sPROC}, {"ret", sRET},
    {"print", sPRINT}, {"scan", sSCAN}, {"true", sTRUE}, {"false", sFALSE},
    {"module", sMODULE}, {"main", sMAIN}, {NULL, 0}
};

void lex_init(FILE *f) {
    source_file = f;
    current_line = 1;
}

Token lex_next() {
    Token t;
    int i = 0;
    char c;

ESTADO_INICIAL:
    i = 0;
    c = fgetc(source_file);

    if (c == EOF) goto FIM_ARQUIVO;
    if (isspace(c)) {
        if (c == '\n') current_line++;
        goto ESTADO_INICIAL; // Ignora espaços e reinicia
    }

    t.linha = current_line;

    // Decisão de caminho baseada no primeiro caractere
    if (c == '@') goto ESTADO_COMENTARIO;
    if (isalpha(c) || c == '_') goto ESTADO_IDENTIF;
    if (isdigit(c)) goto ESTADO_NUMERO;
    if (c == '\'') goto ESTADO_STRING;
    
    // Operadores e Delimitadores
    if (c == ':') goto ESTADO_DOIS_PONTOS;
    if (c == '.') goto ESTADO_PONTO;
    if (c == '=') goto ESTADO_IGUAL;
    if (c == '<') goto ESTADO_MENOR;
    if (c == '>') goto ESTADO_MAIOR;
    
    // Símbolos de caractere único
    t.lexema[0] = c; t.lexema[1] = '\0';
    switch (c) {
        case ';': t.categoria = sPONTO_VIRG; return t;
        case ',': t.categoria = sVIRGULA; return t;
        case '+': t.categoria = sSOMA; return t;
        case '-': t.categoria = sSUBRAT; return t;
        case '*': t.categoria = sMULT; return t;
        case '/': t.categoria = sDIV; return t;
        case '^': t.categoria = sAND; return t;
        case 'v': t.categoria = sOR; return t;
        case '~': t.categoria = sNEG; return t;
        case '(': t.categoria = sABRE_PAR; return t;
        case ')': t.categoria = sFECHA_PAR; return t;
        case '[': t.categoria = sABRE_COLCH; return t;
        case ']': t.categoria = sFECHA_COLCH; return t;
    }

    goto ESTADO_ERRO;

ESTADO_IDENTIF:
    t.lexema[i++] = c;
    c = fgetc(source_file);
    if (isalnum(c) || c == '_') goto ESTADO_IDENTIF;
    
    ungetc(c, source_file); // Devolve o caractere que não pertence ao nome
    t.lexema[i] = '\0';
    
    // Verifica se é palavra reservada
    t.categoria = sIDENTIF;
    for (int j = 0; keywords[j].texto != NULL; j++) {
        if (strcmp(t.lexema, keywords[j].texto) == 0) {
            t.categoria = keywords[j].cat;
            break;
        }
    }
    return t;

ESTADO_NUMERO:
    t.lexema[i++] = c;
    c = fgetc(source_file);
    if (isdigit(c)) goto ESTADO_NUMERO;
    
    ungetc(c, source_file);
    t.lexema[i] = '\0';
    t.categoria = sCTEINT;
    return t;

ESTADO_STRING:
    // Captura tudo entre plicas '...'
    c = fgetc(source_file);
    if (c == '\'') {
        t.lexema[i] = '\0';
        t.categoria = (i == 1) ? sCTECHAR : sSTRING;
        return t;
    }
    if (c == EOF || c == '\n') goto ESTADO_ERRO;
    t.lexema[i++] = c;
    goto ESTADO_STRING;

ESTADO_DOIS_PONTOS:
    c = fgetc(source_file);
    if (c == '=') {
        strcpy(t.lexema, ":=");
        t.categoria = sATRIB;
    } else {
        ungetc(c, source_file);
        strcpy(t.lexema, ":");
        t.categoria = sDOIS_PONTOS;
    }
    return t;

ESTADO_PONTO:
    c = fgetc(source_file);
    if (c == '.') {
        strcpy(t.lexema, "..");
        t.categoria = sPTOPTO;
        return t;
    }
    ungetc(c, source_file); // Ponto isolado não existe em SAL
    goto ESTADO_ERRO;

ESTADO_IGUAL:
    c = fgetc(source_file);
    if (c == '>') {
        strcpy(t.lexema, "=>");
        t.categoria = sIMPLIC;
    } else {
        ungetc(c, source_file);
        strcpy(t.lexema, "=");
        t.categoria = sIGUAL;
    }
    return t;

ESTADO_MENOR:
    c = fgetc(source_file);
    if (c == '=') { strcpy(t.lexema, "<="); t.categoria = sMENORIG; }
    else if (c == '>') { strcpy(t.lexema, "<>"); t.categoria = sDIFERENTE; }
    else { ungetc(c, source_file); strcpy(t.lexema, "<"); t.categoria = sMENOR; }
    return t;

ESTADO_MAIOR:
    c = fgetc(source_file);
    if (c == '=') { strcpy(t.lexema, ">="); t.categoria = sMAIORIG; }
    else { ungetc(c, source_file); strcpy(t.lexema, ">"); t.categoria = sMAIOR; }
    return t;

ESTADO_COMENTARIO:
    c = fgetc(source_file);
    if (c == '{') { // Início de bloco @{
    BLOCO:
        c = fgetc(source_file);
        if (c == EOF) goto ESTADO_ERRO;
        if (c == '\n') current_line++;
        if (c == '}') {
            char prox = fgetc(source_file);
            if (prox == '@') goto ESTADO_INICIAL; // Fim do bloco }@
            ungetc(prox, source_file);
        }
        goto BLOCO;
    } else { // Comentário de linha @...
    LINHA:
        if (c == '\n' || c == EOF) {
            if (c == '\n') current_line++;
            goto ESTADO_INICIAL;
        }
        c = fgetc(source_file);
        goto LINHA;
    }

FIM_ARQUIVO:
    t.categoria = sEOF;
    strcpy(t.lexema, "EOF");
    return t;

ESTADO_ERRO:
    t.categoria = sERRO;
    t.lexema[0] = c; t.lexema[1] = '\0';
    return t;
}