/*
    PROJETO ANALISADOR SINTÁTICO RECURSIVO DESCEDENTE

 Beatriz Bellini Prado Garcia - 10419741
 Fabio Oliveira da Silva - 10420458
 Patrick Rocha de Andrade 10410902
 Thais Ferreira Canguçu - 10403283
 */

#include<stdio.h>
#include<stdlib.h>

/*
    N={ X,E, R, T, Z, F, N, D } 
    T={ +, -, *, /, %, (, ), 0, 1, 2, 3, 4, 5, 6, 7, 8, 9} 

     X -> E$ 
     E -> T R 
     R -> + T R 
     R -> - T R 
     R -> e 
     T -> F Z 
     Z -> * F Z 
     Z -> / F Z 
     Z -> % F Z 
     Z -> e 
     F -> (E) 
     F -> N 
     F -> -N 
     N -> 0|1D|2D|3D|4D|5D|6D|7D|8D|9D 
     D -> 0D|1D|2D|3D|4D|5D|6D|7D|8D|9D 
     D -> e
     
     ** e = palavra vazia
 */

// Declarações para não dar erro de declaração implicita
int X(char palavra[], int *pos);
int E(char palavra[], int *pos);
int R(char palavra[], int *pos);
int T(char palavra[], int *pos);
int Z(char palavra[], int *pos);
int F(char palavra[], int *pos);
int N(char palavra[], int *pos);
int D(char palavra[], int *pos);
 
char lookahead;   /* Excepcionalmente variavel global */
int posicao_erro; /* Para tratamento de erro */
int chave_erro; /* Para personalização do tipo de erro */
int flag_par = 0; /* Flag de parenteses abertos, se não acabar em 0, erro de parêntese */

// Match reutilizado do exemplo
int  match(char t, char palavra[], int *pos){
	if (lookahead == t){
		lookahead= palavra[++(*pos)];
		return(1);
	}
	return(0);  
}

// X -> E$
int X(char palavra[], int *pos){
    if (E(palavra, pos)){
        if(match('$', palavra, pos)) return(1);
        else{
            posicao_erro = *pos;
            chave_erro = 2;
            return (0);
        }
    } 
    else return(0);
}

// E -> T R
int E(char palavra[], int *pos){
    if(T(palavra, pos) &&
       R(palavra, pos)) return(1);
    return(0);
}

// R -> + T R    R -> - T R     R -> e
int R(char palavra[], int *pos){
    if(lookahead == '+' || lookahead == '-'){
        if ((match('+',palavra,pos) || match('-',palavra,pos)) &&
            T(palavra,pos) &&
            R(palavra,pos))
        return(1);
        else return(0);
    }
    else return(1); //Palavra vazia
}

// T -> F Z
int T(char palavra[], int *pos){
    if (F(palavra,pos) &&
        Z(palavra,pos))
    return(1);
    else return(0);
}

// F -> (E)    F -> N     F -> -N
int F(char palavra[], int *pos){
    // F -> N
    if(N(palavra,pos)) return(1); 

    // F -> (E)
    else if(lookahead == '('){
        flag_par += 1;  // Um parêntese aberto
        if (match('(',palavra,pos) &&
            E(palavra,pos)){
                if(match(')',palavra,pos)){
                    flag_par -= 1;
                    return(1);
                }
                else{
                    posicao_erro = *pos;
                    return(0);
                }
            }
        else return(0);
    }

    // F -> -N
    else if(lookahead == '-'){
        if (match('-',palavra,pos) &&
            N(palavra,pos))
        return(1);
        else return(0);
    }
    else return(0);
}

// Z -> * F Z    Z -> / F Z    Z -> % F Z    Z -> e
int Z(char palavra[], int *pos){
    // Z -> * F Z  ou  Z -> / F Z  ou  Z -> % F Z  
    if(lookahead == '*' || lookahead == '/' || lookahead == '%'){
        if (match(lookahead,palavra,pos) &&
            F(palavra,pos) &&
            Z(palavra,pos)) return(1);
        else return(0);
    }   

    // Palavra vazia
    else return (1);
}

// D -> 0D|1D|2D|3D|4D|5D|6D|7D|8D|9D      D -> e
int D(char palavra[], int *pos){
    if(lookahead >= '0' && lookahead <= '9'){
        if (match(lookahead,palavra,pos) &&
            D(palavra,pos)) return(1);
        return(0);
    }
    // Palavra vazia
    else return(1); 
}

// N -> 0|1D|2D|3D|4D|5D|6D|7D|8D|9D
int N(char palavra[], int *pos){
    // N -> 1D|2D|3D|4D|5D|6D|7D|8D|9D
    if(lookahead > '0' && lookahead <= '9'){
        if (match(lookahead,palavra,pos) &&
            D(palavra,pos)) return(1);
        else return(0);
    }

    // N -> 0
    else if(lookahead == '0'){
        if(match('0',palavra,pos)) return(1);
        else return(0);
    }
    else{
        chave_erro = 1;
        posicao_erro = *pos;
        return(0);
     } 
}

// Tratamento de erros
void trataErro(char palavra[]){
    /* Os erros tratam o primeiro erro de sintaxe apenas */
    printf("\n\nERRO DE SINTAXE\n\n");
    printf("\nErro na posição: %d\n", posicao_erro);

    // Trata primeiro parenteses abertos e não fechados
    if(flag_par > 0 && chave_erro != 1){
        printf("%d parêntese(s) não fechado(s)\n", flag_par);
    }
    else{
        switch(chave_erro){
            case 1:
                printf("Caractere inválido ou posição errada: '%c'\n", palavra[posicao_erro]);
                break;
            case 2:
                if(palavra[posicao_erro] == ')') printf("Parêntese fechado sem abertura.\n");
                else if(palavra[posicao_erro] == '(') printf("Parêntese aberto em posição inválida.\n");
                else printf("Falta de '$' ao final da palavra\n");
                break;
            default:
                printf("Erro desconhecido\n");
        }
    }
}

int main(){
	char palavra[]= "-7+(54+(8))$";
	int  pos=0;
	
	lookahead= palavra[pos];
	if (X(palavra, &pos))
	    printf("\nPalavra %s reconhecida\n\n", palavra);
	else 
	    trataErro(palavra);
	system("PAUSE");
	return(0);
}