# 1. Base Image: Ubuntu
FROM ubuntu:22.04

# 2. Configuração
ENV DEBIAN_FRONTEND=noninteractive

# 3. Dependências Enxutas (Incluindo os compiladores nativos do Linux)
RUN apt-get update && apt-get install -y --no-install-recommends \
    build-essential \
    pkg-config \
    libgrpc++-dev \
    protobuf-compiler-grpc \
    protobuf-compiler \
    libprotobuf-dev \
    ghostscript \
    poppler-utils \
    imagemagick \
    ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# 4. Diretório de trabalho
WORKDIR /app

# 5. Copiar APENAS o código puro (Isso evita copiar lixo ou cache do Mac)
COPY proto/ ./proto/
COPY server_cpp/server.cc ./server_cpp/

# 6. Compilação Raiz (Gera o contrato na versão do Linux e compila direto)
WORKDIR /app/server_cpp
RUN protoc -I ../proto --grpc_out=. --plugin=protoc-gen-grpc=$(which grpc_cpp_plugin) ../proto/file_processor.proto && \
    protoc -I ../proto --cpp_out=. ../proto/file_processor.proto && \
    g++ -std=c++17 server.cc file_processor.pb.cc file_processor.grpc.pb.cc -o server \
    $(pkg-config --cflags --libs grpc++ grpc) -lgrpc++_reflection -lprotobuf -pthread

# 7. Expor a porta
EXPOSE 50051

# 8. Comando inicial
CMD ["./server"]