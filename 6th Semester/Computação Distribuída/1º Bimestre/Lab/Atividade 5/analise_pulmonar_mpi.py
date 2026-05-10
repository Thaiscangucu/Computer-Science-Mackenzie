from mpi4py import MPI
import numpy as np
import time
import sys

def main():
    comm = MPI.COMM_WORLD
    rank = comm.Get_rank()
    size = comm.Get_size()

    # --- Etapa 1 & 2: Inicialização e Geração da Imagem (Root) ---
    img_config = None
    if rank == 0:
        print(f"--- Sistema de Análise Distribuída iniciado com {size} processos ---")
        # Configurações sugeridas: 2000x2000 até 6000x6000
        rows, cols = 2000, 2000 
        
        # Gerando imagem simulada
        # Fundo (0-50), Pulmões (regiões mais escuras 30-100), 
        # Coração/Ossos (100-180), Patologias (200-255)
        img = np.random.randint(20, 150, (rows, cols), dtype=np.uint8)
        
        # Simulando "manchas" suspeitas (anomalias)
        # Pulmão Esquerdo (colunas 0 a cols//2)
        img[400:600, 200:400] = np.random.randint(180, 245, (200, 200))
        # Pulmão Direito (colunas cols//2 a cols)
        img[1200:1300, 1500:1700] = np.random.randint(210, 255, (100, 200))

        # Parâmetros de análise (Etapa 3)
        img_config = {
            'rows': rows,
            'cols': cols,
            'limiar_leve': 200,
            'limiar_alto': 230,
            'percent_critico': 0.05 # 5%
        }
        start_time = time.time()
    else:
        img = None

    # --- Etapa 3: Broadcast de Parâmetros ---
    img_config = comm.bcast(img_config, root=0)
    
    # --- Etapa 4: Barrier Inicial ---
    comm.Barrier()

    # --- Etapa 5: Divisão da Imagem (Scatter) ---
    # Lógica para tratar divisões não exatas
    rows_per_proc = img_config['rows'] // size
    leftover = img_config['rows'] % size
    
    # O Scatter do mpi4py lida bem com objetos Python, 
    # mas para performance em matrizes grandes, dividimos manualmente:
    chunks = None
    if rank == 0:
        chunks = [img[i*rows_per_proc : (i+1)*rows_per_proc, :] for i in range(size)]
        # Otimização simples: o resto é processado pelo root ou descartado para simetria
        # Para este exercício, assumiremos tamanhos divisíveis ou truncados.
        
    local_img = comm.scatter(chunks, root=0)

    # --- Etapa 6 & 7: Análise Local ---
    rows_local, cols_local = local_img.shape
    total_pixels_local = rows_local * cols_local
    soma_intensidade_local = np.sum(local_img)
    max_local = np.max(local_img)
    
    # Máscaras de suspeita
    suspeito_mask = local_img > img_config['limiar_leve']
    alto_suspeito_mask = local_img > img_config['limiar_alto']
    
    # Divisão Pulmão Esquerdo/Direito
    meio = cols_local // 2
    suspeito_esq = np.sum(suspeito_mask[:, :meio])
    suspeito_dir = np.sum(suspeito_mask[:, meio:])
    
    total_suspeitos_local = np.sum(suspeito_mask)
    total_alto_suspeito_local = np.sum(alto_suspeito_mask)
    
    # Classificação da faixa (Etapa 7)
    percent_suspeito = total_suspeitos_local / total_pixels_local
    if percent_suspeito > img_config['percent_critico'] or total_alto_suspeito_local > (total_pixels_local * 0.01):
        classificacao = "CRÍTICA"
    elif percent_suspeito > (img_config['percent_critico'] / 2):
        classificacao = "ATENÇÃO"
    else:
        classificacao = "NORMAL"

    # --- Etapa 8: Simulação de Cluster Heterogêneo ---
    if rank % 2 != 0:
        time.sleep(0.5) # Atraso artificial para ranks ímpares

    # --- Etapa 9: Barrier antes da consolidação ---
    comm.Barrier()

    # --- Etapa 10: Consolidação Numérica (Reduce) ---
    global_pixels = comm.reduce(total_pixels_local, op=MPI.SUM, root=0)
    global_soma = comm.reduce(int(soma_intensidade_local), op=MPI.SUM, root=0)
    global_suspeitos = comm.reduce(total_suspeitos_local, op=MPI.SUM, root=0)
    global_alto_suspeitos = comm.reduce(total_alto_suspeito_local, op=MPI.SUM, root=0)
    global_esq = comm.reduce(suspeito_esq, op=MPI.SUM, root=0)
    global_dir = comm.reduce(suspeito_dir, op=MPI.SUM, root=0)
    global_max = comm.reduce(max_local, op=MPI.MAX, root=0)

    # --- Etapa 11: Coleta de Estatísticas (Gather) ---
    local_report = {
        'rank': rank,
        'linhas': (rank * rows_per_proc, (rank + 1) * rows_per_proc),
        'pixels': total_pixels_local,
        'suspeitos': total_suspeitos_local,
        'alto_suspeitos': total_alto_suspeito_local,
        'max': max_local,
        'status': classificacao
    }
    all_reports = comm.gather(local_report, root=0)

    # --- Etapa 12: Relatório Final (Root) ---
    if rank == 0:
        end_time = time.time()
        print("\n" + "="*50)
        print("RELATÓRIO FINAL DE ANÁLISE RADIOGRÁFICA")
        print("="*50)
        print(f"Dimensões: {img_config['rows']}x{img_config['cols']}")
        print(f"Processos MPI: {size}")
        print(f"Tempo de Execução: {end_time - start_time:.4f}s")
        print("-" * 30)
        print(f"Total de Pixels: {global_pixels}")
        print(f"Média de Intensidade: {global_soma / global_pixels:.2f}")
        print(f"Intensidade Máxima: {global_max}")
        print(f"Total Suspeitos: {global_suspeitos} ({ (global_suspeitos/global_pixels)*100:.2f}%)")
        print(f"Total Altamente Suspeitos: {global_alto_suspeitos}")
        print("-" * 30)
        print(f"Pulmão Esquerdo: {global_esq} suspeitos")
        print(f"Pulmão Direito: {global_dir} suspeitos")
        lado_critico = "Esquerdo" if global_esq > global_dir else "Direito"
        print(f"Lado com maior criticidade: {lado_critico}")
        print("-" * 30)
        print("DETALHAMENTO POR PROCESSO:")
        for rep in all_reports:
            print(f"Rank {rep['rank']} | Linhas {rep['linhas']} | Suspeitos: {rep['suspeitos']} | Status: {rep['status']}")
        
        # Classificação Geral
        perc_global = global_suspeitos / global_pixels
        if perc_global > img_config['percent_critico']:
            print("\nCLASSIFICAÇÃO GERAL: EXAME COM ALTA CONCENTRAÇÃO DE ÁREAS SUSPEITAS")
        elif perc_global > (img_config['percent_critico'] / 2):
            print("\nCLASSIFICAÇÃO GERAL: ATENÇÃO CLÍNICA")
        else:
            print("\nCLASSIFICAÇÃO GERAL: SEM INDÍCIOS RELEVANTES")
        print("="*50)

if __name__ == "__main__":
    main()