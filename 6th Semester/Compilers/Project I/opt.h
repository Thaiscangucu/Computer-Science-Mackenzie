#ifndef OPT_H
#define OPT_H

#include <stdbool.h>

// Estrutura para guardar o que o usuário escolheu na linha de comando
typedef struct {
    char *input_file;
    bool show_tokens;
    bool show_symtab;
    bool show_trace;
} Options;

// Funções públicas do módulo
void opts_parse(int argc, char *argv[]);
Options* opts_get(void);

#endif