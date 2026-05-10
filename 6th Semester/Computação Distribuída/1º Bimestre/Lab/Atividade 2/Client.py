import socket
import threading
import time
import random

# Thais Ferreira Canguçu - 10403283
# Miguel Piñeiro Coratolo Simões - 10427085

NUM_CLIENTES = 30
TCP_IP = '127.0.0.1'
TCP_PORTA = 12346
threads = []


def handle_client(id_cliente):
    # Socket TCP
    cliente = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    cliente.connect((TCP_IP, TCP_PORTA))

    
    for i in range(random.randint(10, 20)):
    
        # Enviar CONSULTAR para servidor
        cliente.send("CONSULTAR".encode('UTF-8'))
        
        resp = cliente.recv(1024).decode('UTF-8')
        
        time.sleep(random.uniform(0.1, 1.0))
        
        # Enviar COMPRAR para servidor
        cliente.send("COMPRAR".encode('UTF-8'))
        try:
            resp = cliente.recv(1024).decode('UTF-8')
            print(f"[Cliente {id_cliente}] {resp}")
            
            # Se recebermos que está esgotado, não precisamos mais tentar comprar
            if "esgotado" in resp.lower() and "aviso" not in resp.lower():
                break
        except socket.timeout:
            pass
        
        for _ in range(2):
            try:
                aviso = cliente.recv(1024).decode('UTF-8')
                if aviso:
                    print(f">>> [Cliente {id_cliente} recebeu broadcast]: {aviso}")
            except socket.timeout:
                continue

    cliente.close()
    
    
print(f"Iniciando teste com {NUM_CLIENTES} clientes simulados...\n")

for i in range(NUM_CLIENTES):
    thread = threading.Thread(target=handle_client, args=(i+1,))
    threads.append(thread)
    thread.start()
    
for thread in threads:
    thread.join()
    
