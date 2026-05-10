#include <fstream>
#include <iostream>
#include <memory>
#include <sstream>
#include <string>
#include <chrono>
#include <ctime>
#include <grpcpp/grpcpp.h>
#include "file_processor.grpc.pb.h"

using grpc::Server;
using grpc::ServerBuilder;
using grpc::ServerContext;
using grpc::ServerReaderWriter;
using grpc::Status;
using file_processor::FileChunk;
using file_processor::FileProcessorService;
using file_processor::FileRequest;
using file_processor::FileResponse;

void LogError(const std::string& service_name, const std::string& file_name, const std::string& message) {
    auto now = std::chrono::system_clock::now();
    std::time_t now_c = std::chrono::system_clock::to_time_t(now);
    std::tm now_tm;
    localtime_r(&now_c, &now_tm);
    char timestamp[26];
    std::strftime(timestamp, sizeof(timestamp), "%Y-%m-%d %H:%M:%S", &now_tm);
    
    std::ofstream log_file("server.log", std::ios::app);
    if (log_file.is_open()) {
        log_file << "[" << timestamp << "] ERROR Service: " << service_name 
                 << ", File: " << file_name << ", Message: " << message << std::endl;
        log_file.close();
    }
    std::cerr << "[" << timestamp << "] ERROR Service: " << service_name 
              << ", File: " << file_name << ", Message: " << message << std::endl;
}

void LogSuccess(const std::string& service_name, const std::string& file_name, const std::string& message) {
    auto now = std::chrono::system_clock::now();
    std::time_t now_c = std::chrono::system_clock::to_time_t(now);
    std::tm now_tm;
    localtime_r(&now_c, &now_tm);
    char timestamp[26];
    std::strftime(timestamp, sizeof(timestamp), "%Y-%m-%d %H:%M:%S", &now_tm);
    
    std::ofstream log_file("server.log", std::ios::app);
    if (log_file.is_open()) {
        log_file << "[" << timestamp << "] SUCCESS Service: " << service_name 
                 << ", File: " << file_name << ", Message: " << message << std::endl;
        log_file.close();
    }
    std::cout << "[" << timestamp << "] SUCCESS Service: " << service_name 
              << ", File: " << file_name << ", Message: " << message << std::endl;
}

class FileProcessorServiceImpl final : public FileProcessorService::Service {
private:
    Status SendFileResponse(const std::string& output_file_path, const std::string& file_name, ServerReaderWriter<FileResponse, FileRequest>* stream) {
        FileResponse response;
        response.set_success(true);
        response.set_file_name(file_name);

        std::ifstream output_file_stream(output_file_path, std::ios::binary);
        if (output_file_stream) {
            char buffer[1024];
            while (output_file_stream.read(buffer, sizeof(buffer)) || output_file_stream.gcount() > 0) {
                response.mutable_file_content()->set_content(buffer, output_file_stream.gcount());
                stream->Write(response);
            }
            output_file_stream.close();
            return Status::OK;
        } else {
            response.set_success(false);
            response.set_status_message("Erro interno ao ler arquivo gerado.");
            stream->Write(response);
            return Status(grpc::StatusCode::INTERNAL, "Falha ao ler arquivo de saída");
        }
    }

public:
    Status CompressPDF(ServerContext* context, ServerReaderWriter<FileResponse, FileRequest>* stream) override {
        FileRequest request;
        std::string file_name = "unknown.pdf", input_file_path;
        std::ofstream input_file_stream;
        bool first_chunk = true;

        while (stream->Read(&request)) {
            if (first_chunk) {
                file_name = request.file_name();
                input_file_path = "/tmp/input_" + file_name;
                input_file_stream.open(input_file_path, std::ios::binary);
                first_chunk = false;
            }
            input_file_stream.write(request.file_content().content().c_str(), request.file_content().content().size());
        }
        input_file_stream.close();

        std::string output_file_path = "/tmp/output_" + file_name;
        std::string command = "gs -sDEVICE=pdfwrite -dCompatibilityLevel=1.4 -dPDFSETTINGS=/ebook -dNOPAUSE -dQUIET -dBATCH -sOutputFile=" + output_file_path + " " + input_file_path;
        
        if (std::system(command.c_str()) == 0) {
            LogSuccess("CompressPDF", file_name, "Sucesso.");
            SendFileResponse(output_file_path, "compressed_" + file_name, stream);
        } else {
            LogError("CompressPDF", file_name, "Falha na compressão.");
        }

        std::remove(input_file_path.c_str());
        std::remove(output_file_path.c_str());
        return Status::OK;
    }
    Status ConvertToTXT(ServerContext* context, ServerReaderWriter<FileResponse, FileRequest>* stream) override {
        FileRequest request;
        std::string file_name = "unknown.pdf", input_file_path;
        std::ofstream input_file_stream;
        bool first_chunk = true;

        while (stream->Read(&request)) {
            if (first_chunk) {
                file_name = request.file_name();
                input_file_path = "/tmp/input_txt_" + file_name;
                input_file_stream.open(input_file_path, std::ios::binary);
                first_chunk = false;
            }
            input_file_stream.write(request.file_content().content().c_str(), request.file_content().content().size());
        }
        input_file_stream.close();

        std::string output_file_path = "/tmp/output_" + file_name + ".txt";
        std::string command = "pdftotext " + input_file_path + " " + output_file_path; // Comando exigido pelo lab[cite: 1]
        
        if (std::system(command.c_str()) == 0) {
            LogSuccess("ConvertToTXT", file_name, "Conversão para TXT concluída.");
            SendFileResponse(output_file_path, file_name + ".txt", stream);
        } else {
            LogError("ConvertToTXT", file_name, "Falha na conversão para TXT.");
        }

        std::remove(input_file_path.c_str());
        std::remove(output_file_path.c_str());
        return Status::OK;
    }

    Status ConvertImageFormat(ServerContext* context, ServerReaderWriter<FileResponse, FileRequest>* stream) override {
        FileRequest request;
        std::string file_name = "unknown.img", input_file_path, format_out;
        std::ofstream input_file_stream;
        bool first_chunk = true;

        while (stream->Read(&request)) {
            if (first_chunk) {
                file_name = request.file_name();
                format_out = request.convert_image_format_params().output_format(); // Pega o formato do Protobuf[cite: 1]
                input_file_path = "/tmp/input_img_" + file_name;
                input_file_stream.open(input_file_path, std::ios::binary);
                first_chunk = false;
            }
            input_file_stream.write(request.file_content().content().c_str(), request.file_content().content().size());
        }
        input_file_stream.close();

        std::string output_file_path = "/tmp/output_img." + format_out;
        std::string command = "convert " + input_file_path + " " + output_file_path; // Comando ImageMagick[cite: 1]
        
        if (std::system(command.c_str()) == 0) {
            LogSuccess("ConvertImageFormat", file_name, "Conversão de imagem concluída.");
            SendFileResponse(output_file_path, "converted." + format_out, stream);
        } else {
            LogError("ConvertImageFormat", file_name, "Falha na conversão de imagem.");
        }

        std::remove(input_file_path.c_str());
        std::remove(output_file_path.c_str());
        return Status::OK;
    }

    Status ResizeImage(ServerContext* context, ServerReaderWriter<FileResponse, FileRequest>* stream) override {
        FileRequest request;
        std::string file_name = "unknown.img", input_file_path;
        int width = 0, height = 0;
        std::ofstream input_file_stream;
        bool first_chunk = true;

        while (stream->Read(&request)) {
            if (first_chunk) {
                file_name = request.file_name();
                width = request.resize_image_params().width(); // Pega largura do Protobuf[cite: 1]
                height = request.resize_image_params().height(); // Pega altura do Protobuf[cite: 1]
                input_file_path = "/tmp/input_resize_" + file_name;
                input_file_stream.open(input_file_path, std::ios::binary);
                first_chunk = false;
            }
            input_file_stream.write(request.file_content().content().c_str(), request.file_content().content().size());
        }
        input_file_stream.close();

        std::string output_file_path = "/tmp/output_resized_" + file_name;
        std::string command = "convert " + input_file_path + " -resize " + std::to_string(width) + "x" + std::to_string(height) + " " + output_file_path; 
        
        if (std::system(command.c_str()) == 0) {
            LogSuccess("ResizeImage", file_name, "Redimensionamento concluído.");
            SendFileResponse(output_file_path, "resized_" + file_name, stream);
        } else {
            LogError("ResizeImage", file_name, "Falha no redimensionamento.");
        }

        std::remove(input_file_path.c_str());
        std::remove(output_file_path.c_str());
        return Status::OK;
    }
};

void RunServer() {
    std::string server_address("0.0.0.0:50051");
    FileProcessorServiceImpl service;

    ServerBuilder builder;
    builder.AddListeningPort(server_address, grpc::InsecureServerCredentials());
    builder.RegisterService(&service);

    std::unique_ptr<Server> server(builder.BuildAndStart());
    std::cout << "Servidor gRPC rodando em " << server_address << std::endl;
    server->Wait();
}

int main(int argc, char** argv) {
    RunServer();
    return 0;
}