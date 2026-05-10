// Miguel Piñeiro - 10427085 | Thais Ferreira - 10403283

#include <iostream>
#include <string>
#include <map>
#include <memory>
#include <grpcpp/grpcpp.h>
#include "notas.grpc.pb.h"

std::map<std::string, gerencia_notas::Nota> db_notas_cpp;

class GerenciadorNotasImpl final : public gerencia_notas::GerenciadorNotas::Service {
public:
    grpc::Status AdicionarNota(grpc::ServerContext* context,
                               const gerencia_notas::AdicionaNotaRequest* request,
                               gerencia_notas::StatusResponse* response) override {
        std::string chave = request->ra() + "_" + request->cod_disciplina();
        if (db_notas_cpp.count(chave)) {
            response->set_sucesso(false);
            response->set_msg("Nota já existe. Use AlterarNota.");
            return grpc::Status::OK;
        }
        gerencia_notas::Nota nova_nota;
        nova_nota.set_ra(request->ra());
        nova_nota.set_cod_disciplina(request->cod_disciplina());
        nova_nota.set_ano(request->ano());
        nova_nota.set_semestre(request->semestre());
        nova_nota.set_nota(request->nota());
        db_notas_cpp[chave] = nova_nota;
        response->set_sucesso(true);
        response->set_msg("Nota adicionada com sucesso.");
        return grpc::Status::OK;
    }

    grpc::Status AlterarNota(grpc::ServerContext* context,
                              const gerencia_notas::AdicionaNotaRequest* request,
                              gerencia_notas::StatusResponse* response) override {
        std::string chave = request->ra() + "_" + request->cod_disciplina();
        if (!db_notas_cpp.count(chave)) {
            response->set_sucesso(false);
            response->set_msg("Nota não existe no banco de dados.");
            return grpc::Status::OK;
        }
        gerencia_notas::Nota nota_atualizada;
        nota_atualizada.set_ra(request->ra());
        nota_atualizada.set_cod_disciplina(request->cod_disciplina());
        nota_atualizada.set_ano(request->ano());
        nota_atualizada.set_semestre(request->semestre());
        nota_atualizada.set_nota(request->nota());
        db_notas_cpp[chave] = nota_atualizada;
        response->set_sucesso(true);
        response->set_msg("Nota alterada com sucesso.");
        return grpc::Status::OK;
    }

    grpc::Status ConsultarNota(grpc::ServerContext* context,
                                const gerencia_notas::AlunoDisciplinaRequest* request,
                                gerencia_notas::ConsultaNotaResponse* response) override {
        std::string chave = request->ra() + "_" + request->cod_disciplina();
        if (!db_notas_cpp.count(chave)) {
            response->set_sucesso(false);
            response->set_msg_erro("Nota não encontrada.");
            return grpc::Status::OK;
        }
        response->set_sucesso(true);
        *response->mutable_nota() = db_notas_cpp[chave];
        return grpc::Status::OK;
    }

    grpc::Status CalcularMedia(grpc::ServerContext* context,
                                const gerencia_notas::AlunoRequest* request,
                                gerencia_notas::MediaResponse* response) override {
        float soma = 0.0f;
        int qtd = 0;
        for (const auto& par : db_notas_cpp) {
            if (par.second.ra() == request->ra()) {
                soma += par.second.nota();
                qtd++;
            }
        }
        if (qtd > 0) {
            response->set_sucesso(true);
            response->set_media(soma / qtd);
        } else {
            response->set_sucesso(false);
            response->set_msg_erro("Aluno sem notas.");
        }
        return grpc::Status::OK;
    }

    grpc::Status ListarNotasAluno(grpc::ServerContext* context,
                                   const gerencia_notas::AlunoRequest* request,
                                   grpc::ServerWriter<gerencia_notas::Nota>* writer) override {
        bool encontrou = false;
        for (const auto& par : db_notas_cpp) {
            if (par.second.ra() == request->ra()) {
                writer->Write(par.second);
                encontrou = true;
            }
        }
        if (!encontrou) {
            std::cout << "Nenhuma nota encontrada para RA " << request->ra() << std::endl;
        }
        return grpc::Status::OK;
    }
};

void RunServer() {
    std::string server_address("0.0.0.0:50052");
    GerenciadorNotasImpl service;
    grpc::ServerBuilder builder;
    builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
    builder.RegisterService(&service);
    std::unique_ptr<grpc::Server> server(builder.BuildAndStart());
    std::cout << "Servidor C++ escutando em " << server_address << std::endl;
    server->Wait();
}

int main(int argc, char** argv) {
    RunServer();
    return 0;
}
