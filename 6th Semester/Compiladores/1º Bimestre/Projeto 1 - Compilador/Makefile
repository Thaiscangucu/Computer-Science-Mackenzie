# Variáveis de compilação
CC = gcc
CFLAGS = -Wall -Wextra -std=c99
TARGET = salc

# Lista de todos os arquivos .c (módulos)
SRCS = main.c lex.c parser.c symtab.c diag.c opt.c log.c
# Gera a lista de arquivos .o (objetos) a partir dos .c
OBJS = $(SRCS:.c=.o)

# Regra principal: compilar o executável
$(TARGET): $(OBJS)
	$(CC) $(CFLAGS) -o $(TARGET) $(OBJS)

# Regra para compilar cada arquivo .c em um .o
%.o: %.c
	$(CC) $(CFLAGS) -c $< -o $@

# Limpa os arquivos gerados (para recomeçar do zero)
clean:
	rm -f $(OBJS) $(TARGET) *.tk *.ts *.trc

# Atalho para recompilar tudo
rebuild: clean $(TARGET)