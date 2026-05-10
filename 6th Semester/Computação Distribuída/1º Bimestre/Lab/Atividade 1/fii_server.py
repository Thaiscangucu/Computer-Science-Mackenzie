# Thais Ferreira Canguçu - 10403283

import socket  # importa modulo socket

# Trocar endereço de IP
TCP_IP = "172.20.10.3"  # endereço IP do servidor
TCP_PORTA = 8080       # porta disponibilizada pelo servidor
TAMANHO_BUFFER = 1024  # definição do tamanho do buffer

dados = {
    'HGLG11': {'Preco': 160.00, 'Provento': 1.10},
    'KNRI11': {'Preco': 155.50, 'Provento': 0.80},
    'MXRF11': {'Preco': 10.50, 'Provento': 0.12}
}

# Criação de socket TCP
servidor = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

# IP e porta que o servidor deve aguardar a conexão
servidor.bind((TCP_IP, TCP_PORTA))

# Define o limite de conexões
servidor.listen(1)

print("Servidor disponível na porta 5005 e escutando...")

# Aceita conexão
conn, addr = servidor.accept()
print('Endereço conectado:', addr)

while True:
    data = conn.recv(TAMANHO_BUFFER)

    if not data:
        break

    # Converte bytes para string
    mensagem = data.decode('utf-8')
    print("Mensagem recebida:", mensagem)

    partes = mensagem.split(";")
    comando = partes[0]
    codigo = partes[1]

    resposta = ""

    if codigo in dados:
        if comando == "PRECO":
            resposta = str(dados[codigo]["Preco"])
        elif comando == "PROVENTO":
            resposta = str(dados[codigo]["Provento"])
        elif comando == "STATUS":
            resposta = f"{codigo}; {dados[codigo]}"
        else:
            resposta = "Comando inválido"
    else:
        resposta = "ERRO: FII nao encontrado"

    # Envia resposta para o cliente
    conn.send(resposta.encode('utf-8'))

conn.close()
servidor.close()
