#include "opt.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>

static Options global_opts; // Variável interna (privada) deste módulo

void opts_parse(int argc, char *argv[]) {
    // Valores padrão
    global_opts.input_file = NULL;
    global_opts.show_tokens = false;
    global_opts.show_symtab = false;
    global_opts.show_trace = false;

    if (argc < 2) {
        printf("Uso: salc <arquivo.sal> [--tokens | --symtab | --trace]\n");
        exit(1);
    }

    // O primeiro argumento (índice 1) deve ser o nome do arquivo
    global_opts.input_file = argv[1];

    // Verifica os outros argumentos
    for (int i = 2; i < argc; i++) {
        if (strcmp(argv[i], "--tokens") == 0) global_opts.show_tokens = true;
        else if (strcmp(argv[i], "--symtab") == 0) global_opts.show_symtab = true;
        else if (strcmp(argv[i], "--trace") == 0) global_opts.show_trace = true;
    }
}

Options* opts_get(void) {
    return &global_opts;
}