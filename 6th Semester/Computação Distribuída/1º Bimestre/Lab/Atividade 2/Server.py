import socket
import threading

# Thais Ferreira Canguçu - 10403283
# Miguel Piñeiro Coratolo Simões - 10427085

estoque = 10
lock_estoque = threading.Lock()

clientes_conectados = []
lock_clientes = threading.Lock()

def broadcast():
    aviso = "AVISO: O estoque esgotou"
    with lock_clientes:
        for conn in clientes_conectados:
            conn.send(aviso.encode('utf-8'))

def realizar_venda(conn, addr):
    print(f"Conectado por {addr}")
    global estoque
    
    # Registro do cliente na lista global para futuro Broadcast
    with lock_clientes:
        clientes_conectados.append(conn)
    
    while 1:
        data = conn.recv(1024)
        if not data:
            break
        comando = data.decode('utf-8').strip().upper()
        
        if comando == "CONSULTAR":
            conn.send(f"Estoque atual: {estoque}".encode('utf-8'))
            
        elif comando == "COMPRAR":
            with lock_estoque:
                if estoque > 0:
                    estoque -= 1
                    conn.send(f"Compra realizada. Estoque restante: {estoque}".encode('utf-8'))
                    
                    # Flag Broadcast
                    if estoque == 0:
                        broadcast()
                else:
                    conn.send("ERRO: Produto esgotado".encode('utf-8'))
                    
    with lock_clientes:
        if conn in clientes_conectados:
            clientes_conectados.remove(conn)
            
    conn.close()
    
    
def main():
    # Criação de socket TCP
    # SOCK_STREAM, indica que será TCP.
    servidor = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    servidor.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    # IP e porta que o servidor deve aguardar a conexão
    servidor.bind(('0.0.0.0', 12346))
    #Define o limite de conexões.
    servidor.listen()
    
    print(f"Servidor dispoivel na porta 12346. Aguardando conexões...\n")
    
    while 1:
        # Aceita conexão
        conn, addr = servidor.accept()

        # Cria e inicializa as threads
        thread = threading.Thread(target=realizar_venda, args=(conn, addr))
        thread.start()
        
    server.close()


main()
