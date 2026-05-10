# Thais Ferreira Canguçu - 10403283

import socket
import threading

# Mudar o endereço de IP da rede
TCP_IP = "172.20.10.3"
TCP_PORTA = 8080
TAMANHO_BUFFER = 1024
MAIOR_LANCE = 0
CLIENTES_CONECTADOS = []
mutex = threading.Lock()

servidor = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
servidor.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
servidor.bind((TCP_IP, TCP_PORTA))
servidor.listen(10)

print(f'Servidor disponível na porta {TCP_PORTA} e escutando.....')

def realizar_broadcast(mensagem, remetente_conn=None):
    with mutex:
        for cliente in CLIENTES_CONECTADOS[:]:
            if cliente != remetente_conn:
                try:
                    cliente.send(mensagem.encode('utf-8'))
                except:
                    CLIENTES_CONECTADOS.remove(cliente)

def worker(conn, addr):
    global MAIOR_LANCE
    print(f'Novo cliente conectado: {addr}')
    
    try:
        while True:
            data = conn.recv(TAMANHO_BUFFER)
            if not data:
                break
            
            try:
                texto = data.decode('utf-8').strip()
                if not texto: continue

                lance_atual = float(texto)
                print(f"Lance de {addr}: {lance_atual}")

                msg_resposta = ""
                flag_broadcast = False

                # Verifica se o lance enviado é maior que o atual ou nao
                with mutex:
                    if lance_atual > MAIOR_LANCE:
                        MAIOR_LANCE = lance_atual
                        msg_resposta = f"PARABENS! NOVO MAIOR LANCE: {MAIOR_LANCE}"
                        flag_broadcast = True
                    else:
                        msg_resposta = "LANCE RECUSADO: Valor Baixo."
                
                # Envia resposta para quem mandou o lance
                conn.send(msg_resposta.encode('utf-8'))


                # Se foi recorde, avisa todos os clientes
                if flag_broadcast:
                    msg_b = f"\nNOVO LANCE: {MAIOR_LANCE} por USUARIO {addr[1]}"
                    realizar_broadcast(msg_b, remetente_conn=conn)

            except ValueError:
                conn.send("Erro: Envie apenas numeros.".encode('utf-8'))

    except Exception as e:
        print(f"Erro com {addr}: {e}")
        
    finally:
        with mutex:
            if conn in CLIENTES_CONECTADOS:
                CLIENTES_CONECTADOS.remove(conn)
        conn.close()
        print(f"Cliente {addr} desconectado.")

while True:
    conn, addr = servidor.accept()
    with mutex:
        CLIENTES_CONECTADOS.append(conn)
        thread = threading.Thread(target=worker, args=(conn, addr))
        thread.daemon = True
        thread.start()
