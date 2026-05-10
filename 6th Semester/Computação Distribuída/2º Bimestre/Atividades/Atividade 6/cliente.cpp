// Miguel Piñeiro - 10427085 | Thais Ferreira - 10403283

#include <iostream>
#include <string>
#include <memory>
#include <grpcpp/grpcpp.h>
#include "notas.grpc.pb.h"

class NotasClient {
public:
    NotasClient(std::shared_ptr<grpc::Channel> channel)
        : stub_(gerencia_notas::GerenciadorNotas::NewStub(channel)) {}

    void AdicionarNota(const std::string& ra, const std::string& disc,
                       int ano, int semestre, float nota) {
        gerencia_notas::AdicionaNotaRequest request;
        request.set_ra(ra);
        request.set_cod_disciplina(disc);
        request.set_ano(ano);
        request.set_semestre(semestre);
        request.set_nota(nota);

        gerencia_notas::StatusResponse reply;
        grpc::ClientContext context;
        grpc::Status status = stub_->AdicionarNota(&context, request, &reply);
        if (status.ok())
            std::cout << "[AdicionarNota] " << reply.msg() << std::endl;
        else
            std::cout << "[AdicionarNota] Erro RPC: " << status.error_message() << std::endl;
    }

    void AlterarNota(const std::string& ra, const std::string& disc,
                     int ano, int semestre, float nota) {
        gerencia_notas::AdicionaNotaRequest request;
        request.set_ra(ra);
        request.set_cod_disciplina(disc);
        request.set_ano(ano);
        request.set_semestre(semestre);
        request.set_nota(nota);

        gerencia_notas::StatusResponse reply;
        grpc::ClientContext context;
        grpc::Status status = stub_->AlterarNota(&context, request, &reply);
        if (status.ok())
            std::cout << "[AlterarNota] " << reply.msg() << std::endl;
        else
            std::cout << "[AlterarNota] Erro RPC: " << status.error_message() << std::endl;
    }

    void ConsultarNota(const std::string& ra, const std::string& disc) {
        gerencia_notas::AlunoDisciplinaRequest request;
        request.set_ra(ra);
        request.set_cod_disciplina(disc);

        gerencia_notas::ConsultaNotaResponse reply;
        grpc::ClientContext context;
        grpc::Status status = stub_->ConsultarNota(&context, request, &reply);
        if (status.ok()) {
            if (reply.sucesso())
                std::cout << "[ConsultarNota] RA=" << reply.nota().ra()
                          << " Disc=" << reply.nota().cod_disciplina()
                          << " Nota=" << reply.nota().nota() << std::endl;
            else
                std::cout << "[ConsultarNota] " << reply.msg_erro() << std::endl;
        } else {
            std::cout << "[ConsultarNota] Erro RPC: " << status.error_message() << std::endl;
        }
    }

    void CalcularMedia(const std::string& ra) {
        gerencia_notas::AlunoRequest request;
        request.set_ra(ra);

        gerencia_notas::MediaResponse reply;
        grpc::ClientContext context;
        grpc::Status status = stub_->CalcularMedia(&context, request, &reply);
        if (status.ok()) {
            if (reply.sucesso())
                std::cout << "[CalcularMedia] Média do aluno " << ra << ": " << reply.media() << std::endl;
            else
                std::cout << "[CalcularMedia] " << reply.msg_erro() << std::endl;
        } else {
            std::cout << "[CalcularMedia] Erro RPC: " << status.error_message() << std::endl;
        }
    }

    void ListarNotasAluno(const std::string& ra) {
        gerencia_notas::AlunoRequest request;
        request.set_ra(ra);

        grpc::ClientContext context;
        gerencia_notas::Nota nota_recebida;
        std::unique_ptr<grpc::ClientReader<gerencia_notas::Nota>> reader(
            stub_->ListarNotasAluno(&context, request));

        std::cout << "\n[ListarNotasAluno] Notas do aluno " << ra << ":" << std::endl;
        bool encontrou = false;
        while (reader->Read(&nota_recebida)) {
            std::cout << "  - Disciplina: " << nota_recebida.cod_disciplina()
                      << ", Nota: " << nota_recebida.nota() << std::endl;
            encontrou = true;
        }
        grpc::Status status = reader->Finish();
        if (!status.ok())
            std::cout << "[ListarNotasAluno] Erro RPC: " << status.error_message() << std::endl;
        else if (!encontrou)
            std::cout << "  (nenhuma nota encontrada)" << std::endl;
    }

private:
    std::unique_ptr<gerencia_notas::GerenciadorNotas::Stub> stub_;
};

int main(int argc, char** argv) {
    // Por padrão conecta ao servidor C++ (50052); passe "py" como argumento para o Python (50051)
    std::string target = (argc > 1 && std::string(argv[1]) == "py")
                         ? "localhost:50051"
                         : "localhost:50052";

    std::cout << "Conectando ao servidor em " << target << std::endl;
    NotasClient client(grpc::CreateChannel(target, grpc::InsecureChannelCredentials()));

    client.AdicionarNota("456", "CIC0002", 2025, 1, 8.0f);
    client.AdicionarNota("456", "CIC0003", 2025, 1, 7.5f);
    client.AdicionarNota("456", "CIC0004", 2025, 1, 9.0f);

    client.ConsultarNota("456", "CIC0002");
    client.ConsultarNota("456", "CIC0099"); // não existe

    client.AlterarNota("456", "CIC0002", 2025, 1, 10.0f);
    client.ConsultarNota("456", "CIC0002"); // deve mostrar 10.0

    client.CalcularMedia("456");

    client.ListarNotasAluno("456");

    return 0;
}
