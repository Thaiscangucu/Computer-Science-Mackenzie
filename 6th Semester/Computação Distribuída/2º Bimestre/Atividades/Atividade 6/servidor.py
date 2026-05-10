# Miguel Piñeiro - 10427085 | Thais Ferreira - 10403283

import grpc
from concurrent import futures
import notas_pb2
import notas_pb2_grpc

db_notas = {} # Ex: { "123_DISC1": Nota(...), "123_DISC2": Nota(...) }

class GerenciadorNotasServicer(notas_pb2_grpc.GerenciadorNotasServicer):

    def AdicionarNota(self, request, context):
        print(f"Adicionando nota para RA {request.ra} na disciplina {request.cod_disciplina}")
        chave = f"{request.ra}_{request.cod_disciplina}"
        if chave in db_notas:
            return notas_pb2.StatusResponse(sucesso=False, \
                msg="Nota já existe para este aluno/disciplina. Use AlterarNota.")

        nova_nota = notas_pb2.Nota(ra=request.ra, cod_disciplina=request.cod_disciplina, ano=request.ano, semestre=request.semestre,nota=request.nota)
        db_notas[chave] = nova_nota

        return notas_pb2.StatusResponse(sucesso=True, \
            msg="Nota adicionada com sucesso!")
    
    # ... Implementar os outros métodos (AlterarNota, ConsultarNota, CalcularMedia) ...
    def AlterarNota(self, request, context):
        chave = f"{request.ra}_{request.cod_disciplina}"
        if chave not in db_notas:
            return notas_pb2.StatusResponse(sucesso=False, \
                msg="Nota não existe no banco de dados.")
        db_notas[chave] = notas_pb2.Nota(ra=request.ra,cod_disciplina=request.cod_disciplina,ano=request.ano,semestre=request.semestre,nota=request.nota)
        return notas_pb2.StatusResponse(sucesso=True, \
                msg="Nota alterada com sucesso.")
    
    def ConsultarNota(self, request, context):
        chave = f"{request.ra}_{request.cod_disciplina}"
        if chave not in db_notas:
            return notas_pb2.ConsultaNotaResponse(sucesso=False, msg_erro="Nota não encontrada")
        return notas_pb2.ConsultaNotaResponse(sucesso=True, nota=db_notas[chave])


    def CalcularMedia(self, request, context):
        soma = 0
        qtd_notas = 0
        for nota in db_notas.values():
            if nota.ra == request.ra:
                qtd_notas += 1
                soma += nota.nota
        if qtd_notas > 0:
            media = soma / qtd_notas
            return notas_pb2.MediaResponse(sucesso=True, media=media)
        else:
            return notas_pb2.MediaResponse(sucesso=False, msg_erro="Aluno sem notas")
    

    # --- IMPLEMENTAÇÃO DO DESAFIO ---
    def ListarNotasAluno(self, request, context):
        print(f"Listando notas para o RA {request.ra}")
        encontrou = False
        for nota in db_notas.values():
            if nota.ra == request.ra:
                yield nota
                encontrou = True
        
        if not encontrou:
            print(f"Nenhuma nota encontrada para {request.ra}")


def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=10))
    notas_pb2_grpc.add_GerenciadorNotasServicer_to_server(GerenciadorNotasServicer(), server)
    server.add_insecure_port('[::]:50051')
    server.start()
    print("Servidor gRPC rodando na porta 50051.")
    server.wait_for_termination()

if __name__ == '__main__':
    serve()