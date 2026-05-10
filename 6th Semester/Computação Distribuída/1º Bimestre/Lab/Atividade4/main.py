
# Thais Ferreira Canguçu - 10403283
# Miguel Piñeiro Coratolo Simões - 10427085


from mpi4py import MPI
import random

# Inicialização do MPI
comm = MPI.COMM_WORLD
rank = comm.Get_rank()
size = comm.Get_size()


# Função para gerar logs
def gerar_logs(qtd):
    ips = [f"192.168.1.{i}" for i in range(2,254)]
    endpoints = [
        "/",
        "/login",
        "/products",
        "/cart",
        "/checkout",
        "/api/users",
        "/api/orders"
    ]
    metodos = ["GET", "POST"]
    status = ["200", "200", "200", "404", "500"]
    logs = []
    
    for i in range(qtd):
        ip = random.choice(ips)
        endpoint = random.choice(endpoints)
        metodo = random.choice(metodos)
        log_status = random.choice(status)
        
        log = f"{ip} {metodo} {endpoint} {log_status}"
        logs.append(log)
        
    return logs


# Processo 0 gera o dataset
logs_divididos = None
TOTAL_LOGS = 100000

if rank == 0:
    print("\nGerando dataset de logs...\n")
    logs = gerar_logs(TOTAL_LOGS)
    
    logs_divididos = []
    tamanho_fatia = len(logs) // size
    
    for i in range(0, len(logs), tamanho_fatia):
        logs_divididos.append(logs[i: i + tamanho_fatia])

    # O NÓ MASTER TAMBÉM PROCESSA SUA PARTE DO DATASET

logs_locais = comm.scatter(logs_divididos, root=0)

erros = 0

for log in logs_locais:
    partes = log.split()
    log_status = partes[3]
    
    if log_status == "404" or log_status == "500":
        erros += 1


# Nós Master
# Imprime os resultados de cada nó worker e seu também
# A variável 'logs_locais' representa a fatia recebida e 'erros' o contador
print(f"Processo {rank} analisou {len(logs_locais)} linhas "
      f"e encontrou {erros} erros."
)
