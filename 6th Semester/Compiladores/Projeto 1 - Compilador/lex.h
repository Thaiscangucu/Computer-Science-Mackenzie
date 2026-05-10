#ifndef LEX_H
#define LEX_H

#include <stdio.h>

typedef enum {
    // Identificadores e Constantes
    sIDENTIF, sCTEINT, sCTECHAR, sSTRING,
    // Palavras Reservadas
    sBOOL, sCHAR, sINT, sIF, sELSE, sWHILE, sLOOP, sUNTIL, 
    sFOR, sTO, sSTEP, sDO, sMATCH, sWHEN, sOTHERWISE, 
    sSTART, sEND, sGLOBALS, sLOCALS, sFN, sPROC, 
    sRET, sPRINT, sSCAN, sTRUE, sFALSE, sMODULE, sMAIN,
    // Operadores e Delimitadores
    sSOMA, sSUBRAT, sMULT, sDIV, sIGUAL, sDIFERENTE, 
    sMAIOR, sMAIORIG, sMENOR, sMENORIG, sAND, sOR, sNEG,
    sATRIB, sPONTO_VIRG, sVIRGULA, sDOIS_PONTOS, sPTOPTO, sIMPLIC,
    sABRE_PAR, sFECHA_PAR, sABRE_COLCH, sFECHA_COLCH,
    // Controle
    sEOF, sERRO
} TokenType;

typedef struct {
    TokenType categoria; 
    char lexema[256];    
    int linha;           
} Token;

void lex_init(FILE *f);
Token lex_next();

#endif