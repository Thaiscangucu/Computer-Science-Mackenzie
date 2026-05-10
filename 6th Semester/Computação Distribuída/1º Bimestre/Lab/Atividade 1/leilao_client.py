# Thais Ferreira Canguçu - 10403283

import socket
import threading

# Mudar o endereço de IP da rede
TCP_IP = "172.20.10.3"
TCP_PORTA = 8080
TAMANHO_BUFFER = 1024

# Criação do socket
cliente = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

try:
    cliente.connect((TCP_IP, TCP_PORTA))
    print(f"Conectado ao leilão em {TCP_IP}:{TCP_PORTA}")
except Exception as e:
    print(f"Erro ao conectar: {e}")
    sys.exit()

# Função que escuta o servidor
def ouvir_servidor():
    while True:
        try:
            data = cliente.recv(TAMANHO_BUFFER)
            
            if data:
                mensagem = data.decode('utf-8')
                print(f"\n{mensagem}")
                print("Seu Lance: ", end="", flush=True)
            else:
                print("\nDesconectado pelo servidor.")
                cliente.close()
                break
        except:
            break

# Inicia a thread de escuta
t_escuta = threading.Thread(target=ouvir_servidor)
t_escuta.daemon = True
t_escuta.start()
print("Seu Lance: ")

while True:
    try:
        # Recebe input do usuário
        msg = input()
        if msg.strip():
            cliente.send(msg.encode('utf-8'))
            
    except KeyboardInterrupt:
        break
    except Exception as e:
        print(f"Erro: {e}")
        break

cliente.close()
print("Encerrando...")
