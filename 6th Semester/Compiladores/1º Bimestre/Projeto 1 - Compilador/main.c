
#include <stdio.h>
#include "opt.h"
#include "lex.h"
#include "parser.h"

int main(int argc, char *argv[]) {
    opts_parse(argc, argv);
    Options *config = opts_get();

    FILE *arq = fopen(config->input_file, "r");
    if (arq == NULL) {
        printf("Erro: Nao foi possivel abrir %s\n", config->input_file);
        return 1;
    }

    // Passa o arquivo para o léxico cuidar
    lex_init(arq);

    Token t; 
    do {
        // Pedimos o próximo TOKEN completo para o léxico
        t = lex_next();

        // Agora imprimimos as informações do Token
        // %d mostra o número da categoria (ex: 5 para sPONTO_VIRG)
        // %s mostra o texto (ex: ";")
        printf("Linha %d | Categoria: %d | Lexema: [%s]\n", 
                t.linha, t.categoria, t.lexema);

    } while (t.categoria != sEOF && t.categoria != sERRO);

    fclose(arq);
    return 0;
}